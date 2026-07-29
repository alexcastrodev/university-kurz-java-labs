import java.util.Arrays;

public class ArrayComparator {

    /**
     * Compares two integer arrays for equality by content.
     *
     * @param first  the first array
     * @param second the second array
     * @return true if both arrays contain the same elements in the same order, false otherwise
     */
    public static boolean equals(int[] first, int[] second) {
        return Arrays.equals(first, second);
    }

    /**
     * Compares two multi-dimensional integer arrays for equality by content.
     *
     * @param first  the first array
     * @param second the second array
     * @return true if both arrays contain the same elements recursively, false otherwise
     */
    public static boolean deepEquals(int[][] first, int[][] second) {
        return Arrays.deepEquals(first, second);
    }
}