import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class PriceBook {

    private final NavigableSet<Integer> prices = new TreeSet<>();

    public void addPrice(int cents) {
        prices.add(cents);
    }

    public Integer cheapestAtLeast(int cents) {
        return prices.ceiling(cents);
    }

    public Integer mostExpensiveAtMost(int cents) {
        return prices.floor(cents);
    }

    public Integer nextPriceAbove(int cents) {
        return prices.higher(cents);
    }

    public static Set<Integer> uniquePrices(Integer... cents) {
        return Set.of(cents);
    }

    public NavigableSet<Integer> discountRange(int minCentsInclusive, int maxCentsExclusive) {
        return prices.subSet(minCentsInclusive, true, maxCentsExclusive, false);
    }
}
