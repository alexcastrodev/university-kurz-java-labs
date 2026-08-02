import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class NumberBox<T extends Number & Comparable<T>> {

    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public double sum() {
        double total = 0.0;
        for (T item : items) {
            total += item.doubleValue();
        }
        return total;
    }

    public T max() {
        if (items.isEmpty()) {
            throw new NoSuchElementException("box is empty");
        }
        T largest = items.get(0);
        for (T item : items) {
            if (item.compareTo(largest) > 0) {
                largest = item;
            }
        }
        return largest;
    }

    public static <T> void copyAll(List<? extends T> source, List<? super T> destination) {
        for (T item : source) {
            destination.add(item);
        }
    }

    public static <T extends Comparable<T>> boolean isIn(T x, T[] values) {
        for (T value : values) {
            if (value.compareTo(x) == 0) {
                return true;
            }
        }
        return false;
    }
}
