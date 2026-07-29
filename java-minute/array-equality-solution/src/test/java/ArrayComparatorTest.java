import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ArrayComparator")
class ArrayComparatorTest {

    @Test
    @DisplayName("should return true when arrays contain the same elements")
    void shouldReturnTrueWhenArraysAreSame() {
        int[] first = {1, 2, 3};
        int[] second = {1, 2, 3};

        assertTrue(ArrayComparator.equals(first, second));
    }

    @Test
    @DisplayName("should return false when arrays have different elements")
    void shouldReturnFalseWhenArraysHaveDifferentElements() {
        int[] first = {1, 2, 3};
        int[] second = {1, 2, 4};

        assertFalse(ArrayComparator.equals(first, second));
    }

    @Test
    @DisplayName("should return false when arrays have different lengths")
    void shouldReturnFalseWhenLengthsDiffer() {
        int[] first = {1, 2, 3};
        int[] second = {1, 2};

        assertFalse(ArrayComparator.equals(first, second));
    }

    @Test
    @DisplayName("should return true when both arrays are empty")
    void shouldReturnTrueWhenBothArraysAreEmpty() {
        int[] first = {};
        int[] second = {};

        assertTrue(ArrayComparator.equals(first, second));
    }

    @Test
    @DisplayName("should return false when first array is null")
    void shouldReturnFalseWhenFirstArrayIsNull() {
        int[] second = {1, 2, 3};

        assertFalse(ArrayComparator.equals(null, second));
    }

    @Test
    @DisplayName("should return false when second array is null")
    void shouldReturnFalseWhenSecondArrayIsNull() {
        int[] first = {1, 2, 3};

        assertFalse(ArrayComparator.equals(first, null));
    }

    @Test
    @DisplayName("should return true when both arrays are null")
    void shouldReturnTrueWhenBothArraysAreNull() {
        assertTrue(ArrayComparator.equals(null, null));
    }

    @Test
    @DisplayName("should return true when multi-dimensional arrays contain the same elements")
    void shouldReturnTrueWhenMultiDimensionalArraysAreSame() {
        int[][] first = {{1, 2}, {3, 4}};
        int[][] second = {{1, 2}, {3, 4}};

        assertTrue(ArrayComparator.deepEquals(first, second));
    }

    @Test
    @DisplayName("should return false when multi-dimensional arrays have different elements")
    void shouldReturnFalseWhenMultiDimensionalArraysHaveDifferentElements() {
        int[][] first = {{1, 2}, {3, 4}};
        int[][] second = {{1, 2}, {3, 5}};

        assertFalse(ArrayComparator.deepEquals(first, second));
    }

    @Test
    @DisplayName("should return false when multi-dimensional arrays have different lengths")
    void shouldReturnFalseWhenMultiDimensionalLengthsDiffer() {
        int[][] first = {{1, 2}, {3, 4}};
        int[][] second = {{1, 2}};

        assertFalse(ArrayComparator.deepEquals(first, second));
    }
}