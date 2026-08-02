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

    public void record(String command) {
        commands.addLast(command);
        if (commands.size() > capacity) {
            commands.pollFirst();
        }
    }

    public String undoLast() {
        return commands.pollLast();
    }

    public String peekMostRecent() {
        return commands.peekLast();
    }

    public List<String> mostRecentFirst() {
        List<String> result = new ArrayList<>();
        Iterator<String> it = commands.descendingIterator();
        while (it.hasNext()) {
            result.add(it.next());
        }
        return result;
    }

    public List<String> oldestFirst() {
        return new ArrayList<>(commands);
    }

    public int size() {
        return commands.size();
    }
}
