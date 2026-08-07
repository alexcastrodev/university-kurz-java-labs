import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Money")
class MoneyTest {

    @Test
    @DisplayName("should return a negative number when this amount is smaller")
    void shouldReturnNegativeWhenAmountIsSmaller() {
        Money smaller = new Money("USD", 100);
        Money larger = new Money("USD", 500);

        assertTrue(smaller.compareTo(larger) < 0);
    }

    @Test
    @DisplayName("should return a positive number when this amount is larger")
    void shouldReturnPositiveWhenAmountIsLarger() {
        Money larger = new Money("USD", 500);
        Money smaller = new Money("USD", 100);

        assertTrue(larger.compareTo(smaller) > 0);
    }

    @Test
    @DisplayName("should return zero when both amounts are equal")
    void shouldReturnZeroWhenAmountsAreEqual() {
        Money left = new Money("USD", 250);
        Money right = new Money("USD", 250);

        assertEquals(0, left.compareTo(right));
    }

    @Test
    @DisplayName("should sort a list ascending by amount using the natural ordering")
    void shouldSortAscendingUsingNaturalOrdering() {
        List<Money> monies = new ArrayList<>(List.of(
                new Money("USD", 500),
                new Money("USD", 100),
                new Money("USD", 300)));

        Collections.sort(monies);

        assertEquals(List.of(
                new Money("USD", 100),
                new Money("USD", 300),
                new Money("USD", 500)), monies);
    }
}
