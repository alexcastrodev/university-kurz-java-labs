import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TaskRunner")
class TaskRunnerTest {

    @Test
    @DisplayName("should return the supplier result when running on a platform thread")
    void shouldReturnSupplierResultFromPlatformThread() throws InterruptedException {
        TaskRunner runner = new TaskRunner();

        assertEquals("platform-result", runner.runOnPlatformThread(() -> "platform-result"));
    }

    @Test
    @DisplayName("should return the supplier result when running on a virtual thread")
    void shouldReturnSupplierResultFromVirtualThread() throws InterruptedException {
        TaskRunner runner = new TaskRunner();

        assertEquals("virtual-result", runner.runOnVirtualThread(() -> "virtual-result"));
    }

    @Test
    @DisplayName("should actually run the task on a virtual thread")
    void shouldActuallyRunTaskOnAVirtualThread() throws InterruptedException {
        TaskRunner runner = new TaskRunner();
        boolean[] wasVirtual = new boolean[1];

        runner.runOnVirtualThread(() -> {
            wasVirtual[0] = Thread.currentThread().isVirtual();
            return "done";
        });

        assertTrue(wasVirtual[0]);
    }

    @Test
    @DisplayName("should not run the platform thread task on a virtual thread")
    void shouldNotRunPlatformTaskOnAVirtualThread() throws InterruptedException {
        TaskRunner runner = new TaskRunner();
        boolean[] wasVirtual = new boolean[1];

        runner.runOnPlatformThread(() -> {
            wasVirtual[0] = Thread.currentThread().isVirtual();
            return "done";
        });

        assertFalse(wasVirtual[0]);
    }

    @Test
    @DisplayName("should not run the task on the calling thread")
    void shouldNotRunTaskOnTheCallingThread() throws InterruptedException {
        TaskRunner runner = new TaskRunner();
        Thread callingThread = Thread.currentThread();
        Thread[] executingThread = new Thread[1];

        runner.runOnVirtualThread(() -> {
            executingThread[0] = Thread.currentThread();
            return "done";
        });

        assertNotSame(callingThread, executingThread[0]);
    }

    @Test
    @DisplayName("should return every result when running all tasks concurrently")
    void shouldReturnEveryResultWhenRunningAllTasksConcurrently() throws Exception {
        TaskRunner runner = new TaskRunner();
        List<Supplier<String>> tasks = List.of(() -> "a", () -> "b", () -> "c", () -> "d");

        assertEquals(4, runner.runAllConcurrently(tasks).size());
    }

    @Test
    @DisplayName("should return results in submission order, not completion order")
    void shouldReturnResultsInSubmissionOrder() throws Exception {
        TaskRunner runner = new TaskRunner();
        List<Supplier<String>> tasks = List.of(() -> "a", () -> "b", () -> "c");

        assertEquals(List.of("a", "b", "c"), runner.runAllConcurrently(tasks));
    }

    @Test
    @DisplayName("should return an empty list when there are no tasks to run")
    void shouldReturnEmptyListWhenThereAreNoTasks() throws Exception {
        TaskRunner runner = new TaskRunner();

        assertEquals(List.of(), runner.runAllConcurrently(List.of()));
    }

    @Test
    @DisplayName("should run every task on a virtual thread when running concurrently")
    void shouldRunEveryTaskOnAVirtualThreadWhenRunningConcurrently() throws Exception {
        TaskRunner runner = new TaskRunner();
        List<Supplier<String>> tasks = List.of(
                () -> String.valueOf(Thread.currentThread().isVirtual()),
                () -> String.valueOf(Thread.currentThread().isVirtual()));

        assertEquals(List.of("true", "true"), runner.runAllConcurrently(tasks));
    }

    @Test
    @DisplayName("should report a virtual thread as virtual")
    void shouldReportVirtualThreadAsVirtual() {
        TaskRunner runner = new TaskRunner();

        assertTrue(runner.isVirtual(Thread.ofVirtual().unstarted(() -> {})));
    }

    @Test
    @DisplayName("should report a platform thread as not virtual")
    void shouldReportPlatformThreadAsNotVirtual() {
        TaskRunner runner = new TaskRunner();

        assertFalse(runner.isVirtual(new Thread(() -> {})));
    }
}
