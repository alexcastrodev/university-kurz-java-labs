import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("NameRoster")
class NameRosterTest {

    @Test
    @DisplayName("should iterate over all added names in order")
    void shouldIterateOverAllAddedNames() {
        NameRoster roster = new NameRoster();
        roster.add("Ann");
        roster.add("Bob");
        roster.add("Cid");

        List<String> collected = new ArrayList<>();
        for (String name : roster) {
            collected.add(name);
        }

        assertEquals(List.of("Ann", "Bob", "Cid"), collected);
    }

    @Test
    @DisplayName("should return false from hasNext on an empty roster")
    void shouldReturnFalseForHasNextOnEmptyRoster() {
        NameRoster roster = new NameRoster();

        assertFalse(roster.iterator().hasNext());
    }

    @Test
    @DisplayName("should throw NoSuchElementException when next() is called past the end")
    void shouldThrowWhenNextCalledPastEnd() {
        NameRoster roster = new NameRoster();
        roster.add("Ann");

        Iterator<String> it = roster.iterator();
        it.next();

        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    @DisplayName("should support manual hasNext/next calls without for-each")
    void shouldSupportManualHasNextNextCalls() {
        NameRoster roster = new NameRoster();
        roster.add("Ann");
        roster.add("Bob");

        Iterator<String> it = roster.iterator();
        assertTrue(it.hasNext());
        assertEquals("Ann", it.next());
        assertTrue(it.hasNext());
        assertEquals("Bob", it.next());
        assertFalse(it.hasNext());
    }

    @Test
    @DisplayName("should remove the last returned element via iterator.remove()")
    void shouldRemoveElementViaIteratorRemove() {
        NameRoster roster = new NameRoster();
        roster.add("Ann");
        roster.add("Bob");
        roster.add("Cid");

        Iterator<String> it = roster.iterator();
        it.next();
        it.next();
        it.remove();

        assertEquals(2, roster.size());
        List<String> remaining = new ArrayList<>();
        for (String name : roster) remaining.add(name);
        assertEquals(List.of("Ann", "Cid"), remaining);
    }

    @Test
    @DisplayName("should not skip elements after removing from the middle")
    void shouldNotSkipElementsAfterRemove() {
        NameRoster roster = new NameRoster();
        roster.add("Ann");
        roster.add("Bob");
        roster.add("Cid");

        Iterator<String> it = roster.iterator();
        it.next();
        it.next();
        it.remove();

        assertTrue(it.hasNext());
        assertEquals("Cid", it.next());
    }

    @Test
    @DisplayName("should support two independent iterators over the same roster")
    void shouldAllowIteratingTwiceIndependently() {
        NameRoster roster = new NameRoster();
        roster.add("Ann");
        roster.add("Bob");

        Iterator<String> first = roster.iterator();
        Iterator<String> second = roster.iterator();

        assertEquals("Ann", first.next());
        assertEquals("Ann", second.next());
        assertEquals("Bob", first.next());
        assertEquals("Bob", second.next());
    }
}
