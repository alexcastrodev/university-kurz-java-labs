import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class NumberBox<T extends Number & Comparable<T>> {

    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        // TODO-00: Add the item to the internal list.

        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Sums every item's numeric value.
     */
    public double sum() {
        // TODO-01: Add up every item's numeric value.
        // Hint: T is bounded by Number, so every item has a doubleValue().

        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Returns the largest item, using its natural ordering.
     *
     * @throws NoSuchElementException if the box is empty
     */
    public T max() {
        // TODO-02: Find the largest item using compareTo(). Throw
        // new NoSuchElementException("box is empty") if there are no items.
        // Hint: T is bounded by Comparable<T>, so items can be compared directly.

        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Copies every element of {@code source} into {@code destination}.
     * <p>
     * {@code source} only needs to be read from (a "producer" of {@code T} or
     * a subtype), and {@code destination} only needs to be written to (a
     * "consumer" that can hold a {@code T} or any supertype of it).
     */
    public static <T> void copyAll(List<? extends T> source, List<? super T> destination) {
        // TODO-03: Copy every element from source into destination, in order.
        // Hint: this is the PECS pattern — Producer Extends, Consumer Super.

        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Returns true if `x` is equal (via compareTo) to any element of `values`.
     */
    public static <T extends Comparable<T>> boolean isIn(T x, T[] values) {
        // TODO-04 (optional): Search the array for a value equal to x.

        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
