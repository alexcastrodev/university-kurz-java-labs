import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class NameRoster implements Iterable<String> {

    private final List<String> names = new ArrayList<>();

    public void add(String name) {
        names.add(name);
    }

    public int size() {
        return names.size();
    }

    @Override
    public Iterator<String> iterator() {
        // TODO-00: Return a new instance of RosterIterator.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    private class RosterIterator implements Iterator<String> {
        private int cursor = 0;
        private int lastReturned = -1;

        @Override
        public boolean hasNext() {
            // TODO-01: Return true while there are still unread names left in the roster.
            throw new UnsupportedOperationException("Not implemented yet.");
        }

        @Override
        public String next() {
            // TODO-02: Return the next name and advance the cursor.
            // Hint: throw NoSuchElementException if hasNext() would be false.
            throw new UnsupportedOperationException("Not implemented yet.");
        }

        @Override
        public void remove() {
            // TODO-03: Remove the last name returned by next() from the roster.
            // Hint: use names.remove(lastReturned), then move the cursor back by one
            // so the next call to next() doesn't skip the element that slid into this slot.
            throw new UnsupportedOperationException("Not implemented yet.");
        }
    }
}
