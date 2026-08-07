import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class SalesReport {

    public double totalAmount(List<Sale> sales) {
        return sales.stream()
                .mapToDouble(Sale::amount)
                .sum();
    }

    public List<String> productNamesSorted(List<Sale> sales) {
        return sales.stream()
                .map(Sale::product)
                .distinct()
                .sorted()
                .toList();
    }

    public Sale topSaleByAmount(List<Sale> sales) {
        return sales.stream()
                .max(Comparator.comparingDouble(Sale::amount))
                .orElseThrow(() -> new NoSuchElementException("no sales"));
    }

    public Map<String, Double> totalsByProduct(List<Sale> sales) {
        return sales.stream()
                .collect(Collectors.groupingBy(Sale::product, Collectors.summingDouble(Sale::amount)));
    }

    public double averageAmount(List<Sale> sales) {
        return sales.stream()
                .mapToDouble(Sale::amount)
                .average()
                .orElse(0.0);
    }
}
