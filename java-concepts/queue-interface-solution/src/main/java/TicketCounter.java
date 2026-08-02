import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class TicketCounter {

    private final Queue<String> line;

    public TicketCounter(int capacity) {
        this.line = new ArrayBlockingQueue<>(capacity);
    }

    public boolean tryEnqueue(String customer) {
        return line.offer(customer);
    }

    public String nextInLine() {
        return line.peek();
    }

    public String serveNext() {
        return line.remove();
    }

    public String serveNextOrNull() {
        return line.poll();
    }

    public int waiting() {
        return line.size();
    }
}
