import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

public class TaskRunner {

    public String runOnPlatformThread(Supplier<String> task) throws InterruptedException {
        // TODO-00: Run `task` on a new platform Thread (`new Thread(...)`), wait for it
        // to finish with join(), and return the value task.get() produced.
        // Hint: a Supplier's result has to escape the thread's Runnable somehow —
        // an array or an AtomicReference<String> declared outside the Thread works.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public String runOnVirtualThread(Supplier<String> task) throws InterruptedException {
        // TODO-01: Same as above, but the thread must be a virtual thread.
        // Hint: Thread.ofVirtual().start(Runnable) returns the started Thread; join() it.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public List<String> runAllConcurrently(List<Supplier<String>> tasks) throws Exception {
        // TODO-02: Run every task in `tasks` concurrently on virtual threads (one per task)
        // using Executors.newVirtualThreadPerTaskExecutor(), and return their results in
        // the SAME ORDER as `tasks` (not completion order).
        // Hint: submit() each task as a Callable, collect the returned Futures in order,
        // then call get() on each Future in that same order. Use try-with-resources on
        // the ExecutorService (it's AutoCloseable and close() waits for tasks to finish).
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean isVirtual(Thread thread) {
        // TODO-03 (optional): Return whether the given Thread is a virtual thread.
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
