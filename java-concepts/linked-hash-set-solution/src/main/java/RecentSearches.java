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
        if (searches.contains(query)) {
            return;
        }

        if (searches.size() >= capacity) {
            Iterator<String> oldestFirst = searches.iterator();
            if (oldestFirst.hasNext()) {
                oldestFirst.next();
                oldestFirst.remove();
            }
        }

        searches.add(query);
    }

    public List<String> history() {
        return new ArrayList<>(searches);
    }

    public boolean hasSearched(String query) {
        return searches.contains(query);
    }

    public String mostRecent() {
        String last = null;
        for (String search : searches) {
            last = search;
        }
        return last;
    }
}
