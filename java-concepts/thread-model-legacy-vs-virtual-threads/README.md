# Thread Model: Legacy vs. Virtual Threads

## Goal

Understand the mechanical difference between starting a platform thread and starting a virtual thread, and how to run many tasks concurrently on virtual threads without managing a thread pool by hand.

## Prerequisites

- Basic Java syntax
- Familiarity with `Runnable` and `Callable`

## Task

`TaskRunner` takes work as `Supplier<String>` and runs it somewhere other than the calling thread. You'll run one task on a classic platform thread, run the same kind of task on a virtual thread, then run a whole list of tasks concurrently — one virtual thread per task — collecting their results in submission order. A last, optional method reports whether a given `Thread` is virtual.

## Instructions

Complete the following TODOs in `TaskRunner`:

- TODO-00: Implement `runOnPlatformThread()` using `new Thread(...)` and `join()`.
- TODO-01: Implement `runOnVirtualThread()` using `Thread.ofVirtual().start(...)`.
- TODO-02: Implement `runAllConcurrently()` using `Executors.newVirtualThreadPerTaskExecutor()`, returning results in submission order.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/thread-model-legacy-vs-virtual-threads test
```

Or from the lab directory:

```bash
cd java-concepts/thread-model-legacy-vs-virtual-threads
mvn test
```

## Bonus (Optional)

- TODO-03 (optional): Implement `isVirtual()`, reporting whether the given `Thread` is a virtual thread.
