import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ConnectionUtils {

    public List<Money> sortedByAmount(List<Money> monies) {
        List<Money> sorted = new ArrayList<>(monies);
        Collections.sort(sorted);
        return sorted;
    }

    public void openAndUse(Supplier<PooledConnection> factory, Consumer<PooledConnection> action) {
        try (PooledConnection connection = factory.get()) {
            action.accept(connection);
        }
    }
}
