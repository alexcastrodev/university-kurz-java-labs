import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CollectionOps")
class CollectionOpsTest {

    @Test
    @DisplayName("should keep only common elements for intersection")
    void shouldKeepOnlyCommonElementsForIntersection() {
        Collection<String> a = new ArrayList<>(List.of("Ann", "Bob", "Cid"));
        Collection<String> b = new ArrayList<>(List.of("Bob", "Cid", "Dee"));

        Collection<String> result = CollectionOps.intersection(a, b);

        assertEquals(2, result.size());
        assertTrue(result.containsAll(List.of("Bob", "Cid")));
    }

    @Test
    @DisplayName("should return empty collection when there is no overlap for intersection")
    void shouldReturnEmptyWhenNoOverlapForIntersection() {
        Collection<String> a = new ArrayList<>(List.of("Ann"));
        Collection<String> b = new ArrayList<>(List.of("Bob"));

        assertTrue(CollectionOps.intersection(a, b).isEmpty());
    }

    @Test
    @DisplayName("should keep elements not found in the other collection for difference")
    void shouldKeepElementsNotInOtherForDifference() {
        Collection<String> a = new ArrayList<>(List.of("Ann", "Bob", "Cid"));
        Collection<String> b = new ArrayList<>(List.of("Bob"));

        Collection<String> result = CollectionOps.difference(a, b);

        assertEquals(2, result.size());
        assertTrue(result.containsAll(List.of("Ann", "Cid")));
    }

    @Test
    @DisplayName("should not mutate the original collections passed to intersection or difference")
    void shouldNotMutateOriginalCollections() {
        Collection<String> a = new ArrayList<>(List.of("Ann", "Bob"));
        Collection<String> b = new ArrayList<>(List.of("Bob"));

        CollectionOps.intersection(a, b);
        CollectionOps.difference(a, b);

        assertEquals(2, a.size());
        assertEquals(1, b.size());
    }

    @Test
    @DisplayName("should remove blank and empty strings")
    void shouldRemoveBlankAndEmptyStrings() {
        Collection<String> source = new ArrayList<>(List.of("Ann", "", "Bob", "   "));

        Collection<String> result = CollectionOps.removeBlank(source);

        assertEquals(2, result.size());
        assertTrue(result.containsAll(List.of("Ann", "Bob")));
    }

    @Test
    @DisplayName("should not mutate the source collection when removing blanks")
    void shouldNotMutateSourceWhenRemovingBlanks() {
        Collection<String> source = new ArrayList<>(List.of("Ann", ""));

        CollectionOps.removeBlank(source);

        assertEquals(2, source.size());
    }

    @Test
    @DisplayName("should return a String array, not an Object array")
    void shouldReturnStringArrayNotObjectArray() {
        Collection<String> source = List.of("Ann", "Bob");

        String[] result = CollectionOps.toStringArray(source);

        assertEquals(String[].class, result.getClass());
        assertArrayEquals(new String[] {"Ann", "Bob"}, result);
    }

    @Test
    @DisplayName("should return an empty array for an empty collection")
    void shouldReturnEmptyArrayForEmptyCollection() {
        assertEquals(0, CollectionOps.toStringArray(List.of()).length);
    }
}
