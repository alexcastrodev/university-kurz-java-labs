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
        return new RosterIterator();
    }

    private class RosterIterator implements Iterator<String> {
        private int cursor = 0;
        private int lastReturned = -1;

        @Override
        public boolean hasNext() {
            return cursor < names.size();
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = cursor;
            return names.get(cursor++);
        }

        @Override
        public void remove() {
            if (lastReturned < 0) {
                throw new IllegalStateException("next() must be called before remove()");
            }
            names.remove(lastReturned);
            cursor = lastReturned;
            lastReturned = -1;
        }
    }
}
