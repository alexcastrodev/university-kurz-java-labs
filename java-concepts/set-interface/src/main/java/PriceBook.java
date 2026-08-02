import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class PriceBook {

    private final NavigableSet<Integer> prices = new TreeSet<>();

    public void addPrice(int cents) {
        prices.add(cents);
    }

    public Integer cheapestAtLeast(int cents) {
        // TODO-00: Return the smallest stored price that is >= cents, or null if none exists.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public Integer mostExpensiveAtMost(int cents) {
        // TODO-01: Return the largest stored price that is <= cents, or null if none exists.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public Integer nextPriceAbove(int cents) {
        // TODO-02: Return the smallest stored price strictly greater than cents, or null if none.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public static Set<Integer> uniquePrices(Integer... cents) {
        // TODO-03: Build an unmodifiable Set from the given prices.
        // Hint: use the Set factory that rejects duplicates by throwing, rather than
        // silently ignoring them.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public NavigableSet<Integer> discountRange(int minCentsInclusive, int maxCentsExclusive) {
        // TODO-04 (optional): Return a VIEW of prices in [minCentsInclusive, maxCentsExclusive).
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
