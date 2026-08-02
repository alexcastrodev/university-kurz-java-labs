import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class TicketCounter {

    private final Queue<String> line;

    public TicketCounter(int capacity) {
        this.line = new ArrayBlockingQueue<>(capacity);
    }

    public boolean tryEnqueue(String customer) {
        // TODO-00: Attempt to add customer to the line without throwing when it's full.
        // Hint: Queue has a method for exactly this — it reports failure via its return value.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public String nextInLine() {
        // TODO-01: Look at (without removing) the customer at the head of the line.
        // Hint: return null if the line is empty, instead of throwing.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public String serveNext() {
        // TODO-02: Remove and return the customer at the head of the line.
        // Hint: an empty line here is a caller bug — it should throw, not return null.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public String serveNextOrNull() {
        // TODO-03: Same job as serveNext(), but report an empty line via null instead of throwing.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public int waiting() {
        return line.size();
    }
}
