import java.util.HashSet;
import java.util.Set;

public class TagIndex {

    private final Set<String> tags = new HashSet<>();

    public boolean addTag(String tag) {
        // TODO-00: Add `tag` (lowercased, so tagging is case-insensitive) to this index.
        // Return true if it was newly added, false if it was already present.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean hasTag(String tag) {
        // TODO-01: Return whether `tag` (lowercased) is present in this index.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public int size() {
        return tags.size();
    }

    public Set<String> commonTagsWith(TagIndex other) {
        // TODO-02: Return a NEW Set containing only the tags present in BOTH this
        // index and `other` (the intersection). Must not mutate either index's
        // internal tags.
        // Hint: copy this index's tags into a new HashSet, then retainAll(other's tags).
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public Set<String> tagsOnlyInThis(TagIndex other) {
        // TODO-03: Return a NEW Set containing the tags present in this index but
        // NOT in `other` (the difference). Must not mutate either index's internal tags.
        // Hint: copy this index's tags into a new HashSet, then removeAll(other's tags).
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public Set<String> allTagsCombined(TagIndex other) {
        // TODO-04 (optional): Return a NEW Set containing every tag present in
        // EITHER index (the union). Must not mutate either index's internal tags.
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
