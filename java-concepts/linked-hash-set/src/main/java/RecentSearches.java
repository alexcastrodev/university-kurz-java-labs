import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class RecentSearches {

    private final LinkedHashSet<String> searches = new LinkedHashSet<>();
    private final int capacity;

    public RecentSearches(int capacity) {
        this.capacity = capacity;
    }

    public void record(String query) {
        // TODO-00: Record `query` as searched. If it's already present, this is a
        // no-op (do NOT move it, do NOT re-add it — Set semantics). If it's genuinely
        // new and adding it would push the size over `capacity`, evict the OLDEST
        // entry first (the least-recently-added one still present) before adding.
        // Hint: LinkedHashSet's iterator walks oldest-to-newest; getting the first
        // element from it and calling Iterator.remove() evicts the oldest entry.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public List<String> history() {
        // TODO-01: Return the searches in insertion order, oldest first, as a new List.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean hasSearched(String query) {
        // TODO-02: Return whether `query` has been recorded (and not yet evicted).
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public String mostRecent() {
        // TODO-03 (optional): Return the most recently added search still present,
        // or null if none have been recorded yet.
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
