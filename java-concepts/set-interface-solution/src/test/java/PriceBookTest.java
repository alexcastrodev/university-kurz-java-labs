import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PriceBook")
class PriceBookTest {

    @Test
    @DisplayName("should find the cheapest price at least the given amount")
    void shouldFindCheapestAtLeastGivenPrice() {
        PriceBook book = new PriceBook();
        book.addPrice(100);
        book.addPrice(300);
        book.addPrice(500);

        assertEquals(300, book.cheapestAtLeast(250));
    }

    @Test
    @DisplayName("should return null when no price is at least the given amount")
    void shouldReturnNullWhenNoCheapestAtLeastExists() {
        PriceBook book = new PriceBook();
        book.addPrice(100);

        assertNull(book.cheapestAtLeast(200));
    }

    @Test
    @DisplayName("should find the most expensive price at most the given amount")
    void shouldFindMostExpensiveAtMostGivenPrice() {
        PriceBook book = new PriceBook();
        book.addPrice(100);
        book.addPrice(300);
        book.addPrice(500);

        assertEquals(300, book.mostExpensiveAtMost(400));
    }

    @Test
    @DisplayName("should return null when no price is at most the given amount")
    void shouldReturnNullWhenNoMostExpensiveAtMostExists() {
        PriceBook book = new PriceBook();
        book.addPrice(500);

        assertNull(book.mostExpensiveAtMost(400));
    }

    @Test
    @DisplayName("should find the next price strictly above the given amount")
    void shouldFindNextPriceStrictlyAbove() {
        PriceBook book = new PriceBook();
        book.addPrice(100);
        book.addPrice(300);

        assertEquals(300, book.nextPriceAbove(100));
    }

    @Test
    @DisplayName("should return null when no price exists strictly above the given amount")
    void shouldReturnNullWhenNoNextPriceAboveExists() {
        PriceBook book = new PriceBook();
        book.addPrice(300);

        assertNull(book.nextPriceAbove(300));
    }

    @Test
    @DisplayName("should build an unmodifiable set of unique prices")
    void shouldBuildUnmodifiableSetOfUniquePrices() {
        Set<Integer> prices = PriceBook.uniquePrices(100, 200, 300);

        assertEquals(Set.of(100, 200, 300), prices);
        assertThrows(UnsupportedOperationException.class, () -> prices.add(400));
    }

    @Test
    @DisplayName("should reject duplicate prices when building the unique set")
    void shouldRejectDuplicatePricesWhenBuildingUniqueSet() {
        assertThrows(IllegalArgumentException.class, () -> PriceBook.uniquePrices(100, 100));
    }
}
