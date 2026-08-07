import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TaskScheduler")
class TaskSchedulerTest {

    @Test
    @DisplayName("should take the lowest priority number first, not the first submitted")
    void shouldTakeLowestPriorityNumberFirst() {
        TaskScheduler scheduler = new TaskScheduler();
        scheduler.submit("report", 5);
        scheduler.submit("outage", 1);
        scheduler.submit("backup", 3);

        Task next = scheduler.takeNext();

        assertEquals("outage", next.name());
        assertEquals(1, next.priority());
    }

    @Test
    @DisplayName("should return null from takeNext when no tasks remain")
    void shouldReturnNullFromTakeNextWhenEmpty() {
        TaskScheduler scheduler = new TaskScheduler();

        assertNull(scheduler.takeNext());
    }

    @Test
    @DisplayName("should peek at the next task without removing it")
    void shouldPeekWithoutRemoving() {
        TaskScheduler scheduler = new TaskScheduler();
        scheduler.submit("outage", 1);

        assertEquals(new Task("outage", 1), scheduler.peekNext());
        assertEquals(new Task("outage", 1), scheduler.peekNext());
        assertEquals(new Task("outage", 1), scheduler.takeNext());
    }

    @Test
    @DisplayName("should return null from peekNext when no tasks remain")
    void shouldReturnNullFromPeekNextWhenEmpty() {
        TaskScheduler scheduler = new TaskScheduler();

        assertNull(scheduler.peekNext());
    }

    @Test
    @DisplayName("should drain every task in ascending priority order")
    void shouldDrainInAscendingPriorityOrder() {
        TaskScheduler scheduler = new TaskScheduler();
        scheduler.submit("archive", 9);
        scheduler.submit("deploy", 2);
        scheduler.submit("backup", 5);
        scheduler.submit("outage", 1);
        scheduler.submit("cleanup", 7);

        List<Task> drained = scheduler.drainInPriorityOrder();

        assertEquals(List.of(1, 2, 5, 7, 9), drained.stream().map(Task::priority).toList());
    }

    @Test
    @DisplayName("should leave the scheduler empty after draining")
    void shouldLeaveSchedulerEmptyAfterDraining() {
        TaskScheduler scheduler = new TaskScheduler();
        scheduler.submit("deploy", 2);
        scheduler.submit("outage", 1);

        scheduler.drainInPriorityOrder();

        assertNull(scheduler.takeNext());
        assertNull(scheduler.peekNext());
    }

    @Test
    @DisplayName("should return an empty list when draining an empty scheduler")
    void shouldReturnEmptyListWhenDrainingEmptyScheduler() {
        TaskScheduler scheduler = new TaskScheduler();

        List<Task> drained = scheduler.drainInPriorityOrder();

        assertNotNull(drained);
        assertTrue(drained.isEmpty());
    }

    @Test
    @DisplayName("should keep both tasks that share the same priority")
    void shouldKeepBothTasksSharingTheSamePriority() {
        TaskScheduler scheduler = new TaskScheduler();
        scheduler.submit("a", 4);
        scheduler.submit("b", 4);

        List<Task> drained = scheduler.drainInPriorityOrder();

        assertEquals(2, drained.size());
        assertTrue(drained.stream().anyMatch(task -> task.name().equals("a")));
        assertTrue(drained.stream().anyMatch(task -> task.name().equals("b")));
    }
}
