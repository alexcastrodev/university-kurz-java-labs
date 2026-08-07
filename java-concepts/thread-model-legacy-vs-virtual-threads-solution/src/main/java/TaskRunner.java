import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class TaskRunner {

    public String runOnPlatformThread(Supplier<String> task) throws InterruptedException {
        AtomicReference<String> result = new AtomicReference<>();

        Thread thread = new Thread(() -> result.set(task.get()));
        thread.start();
        thread.join();

        return result.get();
    }

    public String runOnVirtualThread(Supplier<String> task) throws InterruptedException {
        AtomicReference<String> result = new AtomicReference<>();

        Thread thread = Thread.ofVirtual().start(() -> result.set(task.get()));
        thread.join();

        return result.get();
    }

    public List<String> runAllConcurrently(List<Supplier<String>> tasks) throws Exception {
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
    }

    public boolean isVirtual(Thread thread) {
        return thread.isVirtual();
    }
}
