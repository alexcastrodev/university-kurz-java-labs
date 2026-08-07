import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class SalesReport {

    public double totalAmount(List<Sale> sales) {
        // TODO-00: Sum the amount of every sale.
        // Hint: mapToDouble + sum().
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public List<String> productNamesSorted(List<Sale> sales) {
        // TODO-01: Return the distinct product names, sorted alphabetically.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public Sale topSaleByAmount(List<Sale> sales) {
        // TODO-02: Return the sale with the highest amount.
        // Throw new NoSuchElementException("no sales") if the list is empty.
        // Hint: Comparator.comparingDouble(Sale::amount) with a stream max().
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public Map<String, Double> totalsByProduct(List<Sale> sales) {
        // TODO-03: Group sales by product name and sum the amount per product.
        // Hint: Collectors.groupingBy + Collectors.summingDouble.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public double averageAmount(List<Sale> sales) {
        // TODO-04 (optional): Return the average sale amount, or 0.0 if the list is empty.
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
