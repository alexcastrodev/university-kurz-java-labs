import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TaskScheduler {

    private final PriorityQueue<Task> queue = new PriorityQueue<>(Comparator.comparingInt(Task::priority));

    public void submit(String name, int priority) {
        queue.offer(new Task(name, priority));
    }

    public Task takeNext() {
        return queue.poll();
    }

    public Task peekNext() {
        return queue.peek();
    }

    public List<Task> drainInPriorityOrder() {
        List<Task> result = new ArrayList<>();
        Task task;
        while ((task = queue.poll()) != null) {
            result.add(task);
        }
        return result;
    }
}
