import java.util.ArrayList;
import java.util.Collection;

public class CollectionOps {

    public static Collection<String> intersection(Collection<String> a, Collection<String> b) {
        Collection<String> result = new ArrayList<>(a);
        result.retainAll(b);
        return result;
    }

    public static Collection<String> difference(Collection<String> a, Collection<String> b) {
        Collection<String> result = new ArrayList<>(a);
        result.removeAll(b);
        return result;
    }

    public static Collection<String> removeBlank(Collection<String> source) {
        Collection<String> result = new ArrayList<>(source);
        result.removeIf(String::isBlank);
        return result;
    }

    public static String[] toStringArray(Collection<String> source) {
        return source.toArray(String[]::new);
    }
}
