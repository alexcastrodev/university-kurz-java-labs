import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SalesReport")
class SalesReportTest {

    private final SalesReport report = new SalesReport();

    private static List<Sale> sales() {
        return List.of(
                new Sale("Widget", 10.0),
                new Sale("Gadget", 15.0),
                new Sale("Widget", 20.0)
        );
    }

    @Test
    @DisplayName("should sum the amount of every sale")
    void shouldSumTheAmountOfEverySale() {
        assertEquals(45.0, report.totalAmount(sales()));
    }

    @Test
    @DisplayName("should return zero total for an empty list")
    void shouldReturnZeroTotalForAnEmptyList() {
        assertEquals(0.0, report.totalAmount(List.of()));
    }

    @Test
    @DisplayName("should return distinct product names sorted alphabetically")
    void shouldReturnDistinctProductNamesSortedAlphabetically() {
        assertEquals(List.of("Gadget", "Widget"), report.productNamesSorted(sales()));
    }

    @Test
    @DisplayName("should return the sale with the highest amount")
    void shouldReturnTheSaleWithTheHighestAmount() {
        assertEquals(new Sale("Widget", 20.0), report.topSaleByAmount(sales()));
    }

    @Test
    @DisplayName("should throw from topSaleByAmount when there are no sales")
    void shouldThrowFromTopSaleByAmountWhenThereAreNoSales() {
        assertThrows(NoSuchElementException.class, () -> report.topSaleByAmount(List.of()));
    }

    @Test
    @DisplayName("should sum the amounts grouped by product")
    void shouldSumTheAmountsGroupedByProduct() {
        Map<String, Double> totals = report.totalsByProduct(sales());

        assertEquals(2, totals.size());
        assertEquals(30.0, totals.get("Widget"));
        assertEquals(15.0, totals.get("Gadget"));
    }

    @Test
    @DisplayName("should average the amount of every sale")
    void shouldAverageTheAmountOfEverySale() {
        assertEquals(15.0, report.averageAmount(sales()));
    }

    @Test
    @DisplayName("should return zero average for an empty list")
    void shouldReturnZeroAverageForAnEmptyList() {
        assertEquals(0.0, report.averageAmount(List.of()));
    }
}
