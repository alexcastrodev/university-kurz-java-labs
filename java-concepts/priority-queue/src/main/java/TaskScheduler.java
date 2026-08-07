import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TaskScheduler {

    private final PriorityQueue<Task> queue = new PriorityQueue<>(Comparator.comparingInt(Task::priority));

    public void submit(String name, int priority) {
        // TODO-00: Submit a new task with the given name and priority.
        // Lower priority numbers run first (this is a min-heap by priority).
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public Task takeNext() {
        // TODO-01: Remove and return the highest-priority (lowest priority number)
        // remaining task, or null if none remain.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public Task peekNext() {
        // TODO-02: Return (without removing) the highest-priority remaining task,
        // or null if none remain.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public List<Task> drainInPriorityOrder() {
        // TODO-03 (optional): Remove and return EVERY remaining task, in priority order
        // (lowest priority number first), as a new List. The scheduler must be
        // empty after this call.
        // Hint: this is the CORRECT way to get tasks in order — repeatedly poll
        // until nothing's left. Do NOT just dump the queue's iterator into a list;
        // that does not reliably preserve priority order.
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
