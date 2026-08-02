import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("NumberBox")
class NumberBoxTest {

    @Test
    @DisplayName("should sum all items")
    void shouldSumAllItems() {
        NumberBox<Integer> box = new NumberBox<>();
        box.add(1);
        box.add(2);
        box.add(3);

        assertEquals(6.0, box.sum(), 0.0001);
    }

    @Test
    @DisplayName("should return zero sum when empty")
    void shouldReturnZeroSumWhenEmpty() {
        NumberBox<Integer> box = new NumberBox<>();

        assertEquals(0.0, box.sum(), 0.0001);
    }

    @Test
    @DisplayName("should find the max item by natural ordering")
    void shouldFindMaxItem() {
        NumberBox<Integer> box = new NumberBox<>();
        box.add(3);
        box.add(9);
        box.add(1);

        assertEquals(9, box.max());
    }

    @Test
    @DisplayName("should throw when finding max of an empty box")
    void shouldThrowWhenFindingMaxOfEmptyBox() {
        NumberBox<Integer> box = new NumberBox<>();

        assertThrows(NoSuchElementException.class, box::max);
    }

    @Test
    @DisplayName("should copy elements from a narrower source into a wider destination")
    void shouldCopyFromNarrowerSourceToWiderDestination() {
        List<Integer> source = List.of(1, 2, 3);
        List<Number> destination = new ArrayList<>();

        NumberBox.copyAll(source, destination);

        assertEquals(List.of(1, 2, 3), destination);
    }

    @Test
    @DisplayName("should copy elements into an Object destination")
    void shouldCopyIntoObjectDestination() {
        List<Integer> source = List.of(1, 2);
        List<Object> destination = new ArrayList<>();

        NumberBox.copyAll(source, destination);

        assertEquals(List.of(1, 2), destination);
    }

    @Test
    @DisplayName("should do nothing when copying an empty source")
    void shouldCopyEmptySourceWithoutError() {
        List<Integer> source = List.of();
        List<Number> destination = new ArrayList<>();

        NumberBox.copyAll(source, destination);

        assertTrue(destination.isEmpty());
    }

    @Test
    @DisplayName("should find a value present in the array (bonus)")
    void shouldFindValueInArray() {
        Integer[] values = {1, 2, 3};

        assertTrue(NumberBox.isIn(2, values));
    }

    @Test
    @DisplayName("should not find a value missing from the array (bonus)")
    void shouldNotFindMissingValue() {
        Integer[] values = {1, 2, 3};

        assertFalse(NumberBox.isIn(5, values));
    }
}
