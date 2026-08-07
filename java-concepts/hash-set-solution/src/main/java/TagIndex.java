import java.util.HashSet;
import java.util.Set;

public class TagIndex {

    private final Set<String> tags = new HashSet<>();

    public boolean addTag(String tag) {
        return tags.add(tag.toLowerCase());
    }

    public boolean hasTag(String tag) {
        return tags.contains(tag.toLowerCase());
    }

    public int size() {
        return tags.size();
    }

    public Set<String> commonTagsWith(TagIndex other) {
        Set<String> result = new HashSet<>(this.tags);
        result.retainAll(other.tags);
        return result;
    }

    public Set<String> tagsOnlyInThis(TagIndex other) {
        Set<String> result = new HashSet<>(this.tags);
        result.removeAll(other.tags);
        return result;
    }

    public Set<String> allTagsCombined(TagIndex other) {
        Set<String> result = new HashSet<>(this.tags);
        result.addAll(other.tags);
        return result;
    }
}
