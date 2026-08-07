# Thread Model: Legacy vs. Virtual Threads - Solution

## Overview

This is the official solution for the Thread Model lab, putting a platform thread, a virtual thread, and a virtual-thread-per-task executor side by side. All three do the same job — run a `Supplier<String>` off the calling thread and hand the value back — so the only thing that differs is what kind of thread carries the work and how the result is collected.

## Key Concepts

### TODO-00: a platform thread, and getting a value out of it

```java
public String runOnPlatformThread(Supplier<String> task) throws InterruptedException {
    AtomicReference<String> result = new AtomicReference<>();

    Thread thread = new Thread(() -> result.set(task.get()));
    thread.start();
    thread.join();

    return result.get();
}
```

`Thread` only accepts a `Runnable`, which returns nothing, so the result has to escape through a variable that outlives the lambda — here an `AtomicReference`. `start()` schedules the thread; `join()` blocks the caller until it finishes, which is also what makes reading `result` safe: `join()` establishes a happens-before edge, so the write inside the thread is visible afterwards. A platform thread is a thin wrapper over an OS thread, so creating one is expensive and you would normally pool them.

### TODO-01: the same thing, on a virtual thread

```java
Thread thread = Thread.ofVirtual().start(() -> result.set(task.get()));
thread.join();
```

`Thread.ofVirtual().start(Runnable)` creates *and* starts the thread in one call, so there is no separate `start()`. Everything else is identical — it is still a `Thread`, still joined the same way. What changed is the cost: a virtual thread is scheduled by the JVM onto a small pool of carrier threads, so creating one is cheap enough that "one thread per task" becomes a reasonable design instead of a mistake.

### TODO-02: one virtual thread per task, results in submission order

```java
try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
    List<Future<String>> futures = tasks.stream()
            .map(task -> executor.submit((Callable<String>) task::get))
            .toList();

    List<String> results = new ArrayList<>();
    for (Future<String> future : futures) {
        results.add(future.get());
    }
    return results;
}
```

`newVirtualThreadPerTaskExecutor()` starts a fresh virtual thread for every submitted task, which is why it takes no pool size: with a fixed platform-thread pool you have to tune the thread count against blocking I/O and core count, but virtual threads unmount from their carrier while blocked, so there is nothing to size. Submitting as a `Callable` (`task::get`) rather than a `Runnable` is what gives you a `Future<String>` carrying the value back. Order comes from the `List<Future<String>>` being built in submission order and then drained in that same order — `future.get()` blocks until *that* task is done, so the results line up with the input regardless of which task actually finished first. Collecting as futures complete (via `ExecutorCompletionService`, say) would give completion order instead. The executor is `AutoCloseable`, so try-with-resources closes it and waits for the tasks to finish.

### TODO-03 (optional): asking a thread what it is

```java
public boolean isVirtual(Thread thread) {
    return thread.isVirtual();
}
```

`Thread.isVirtual()` works on any `Thread`, started or not, and is the reliable way to confirm which model a piece of code is actually running on — including from inside the task itself via `Thread.currentThread().isVirtual()`.

## Summary

- Both thread kinds are `Thread`: same `start()`/`join()` API, so switching models does not change how you wait for work.
- A `Runnable` returns nothing — use an `AtomicReference` for a raw thread, or submit a `Callable` to an executor and read the `Future`.
- `Executors.newVirtualThreadPerTaskExecutor()` needs no pool sizing because virtual threads are cheap and unmount while blocked.
- Result order is a property of *how you collect*: futures gathered in submission order and drained in that order preserve input order; draining as tasks complete does not.
