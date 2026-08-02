import java.util.ArrayList;
import java.util.Collection;

public class CollectionOps {

    public static Collection<String> intersection(Collection<String> a, Collection<String> b) {
        // TODO-00: Return a NEW collection containing only elements found in both a and b.
        // Hint: copy 'a' into a new mutable collection first, then use the Collection
        // method that keeps only what's also present in 'b'. Do not mutate a or b.
        Collection<String> intersection = new ArrayList<>(a);
        intersection.retainAll(b);

        return intersection;
    }

    public static Collection<String> difference(Collection<String> a, Collection<String> b) {
        // TODO-01: Return a NEW collection containing elements in 'a' that are NOT in 'b'.
        // Do not mutate a or b.
        Collection<String> difference = new ArrayList<>(a);
        difference.removeAll(b);

        return difference;
    }

    public static Collection<String> removeBlank(Collection<String> source) {
        // TODO-02: Return a new collection like 'source' but with blank/empty strings removed.
        // Hint: copy first, then use removeIf with a Predicate. Do not mutate source.
        Collection<String> notBlank = new ArrayList<>(source);
        notBlank.removeIf(String::isBlank);

        return notBlank;
    }

    public static String[] toStringArray(Collection<String> source) {
        // TODO-03: Convert source to a properly-typed String[] (not Object[]).
        // Hint: use the toArray overload that takes an IntFunction<T[]> generator.
        return source.toArray(String[]::new);
    }
}
