import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class CommandHistory {
    private final Deque<String> commands = new ArrayDeque<>();
    private final int capacity;

    public CommandHistory(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Records a new command as the most recent one. When the history is full,
     * the oldest recorded command is evicted to make room.
     */
    public void record(String command) {
        // TODO-00: Add the command to the end of the deque (it's the newest one).
        // Then, if the deque now holds more than `capacity` commands, remove the
        // oldest one. Hint: which end holds the oldest command? Which Deque method
        // removes from that end without throwing if it happens to be empty?

        if (commands.size() < capacity) {
            commands.addLast(command);
        } else {
            commands.removeFirst();
            commands.addLast(command);
        }
    }

    /**
     * Removes and returns the most recently recorded command, or null if the
     * history is empty. This must never throw, even when there's nothing to undo.
     */
    public String undoLast() {
        // TODO-01: Remove and return the command from whichever end holds the
        // most recent one. Use the version of the method that returns null on an
        // empty deque instead of throwing.

        return commands.pollLast();
    }

    /**
     * Returns the most recently recorded command without removing it, or null
     * if the history is empty.
     */
    public String peekMostRecent() {
        // TODO-02: Look, don't remove.

        return commands.peekLast();
    }

    /**
     * Returns every recorded command, most recent first.
     */
    public List<String> mostRecentFirst() {
        // TODO-03: Build a List by walking the deque from the newest command to
        // the oldest. Hint: Deque has an iterator for exactly this direction.
        Iterator<String> seqs = commands.descendingIterator();
        List<String> recents = new ArrayList<>();
        while (seqs.hasNext()) {
            recents.add(seqs.next());
        }

        return recents;
    }

    /**
     * Returns every recorded command, oldest first.
     */
    public List<String> oldestFirst() {
        // TODO-04 (optional): Return the commands in the deque's natural order.
        return new ArrayList<>(commands);
    }

    public int size() {
        return commands.size();
    }
}
