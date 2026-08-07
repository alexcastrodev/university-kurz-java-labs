import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ConnectionUtils {

    public List<Money> sortedByAmount(List<Money> monies) {
        // TODO-03: Return a NEW list containing the same Money objects, sorted using
        // their natural ordering (Comparable), ascending. Do not mutate the input list.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void openAndUse(Supplier<PooledConnection> factory, Consumer<PooledConnection> action) {
        // TODO-04 (optional): Obtain a connection from `factory`, pass it to `action`,
        // and guarantee it's closed afterward — even if `action` throws.
        // Hint: this is exactly what try-with-resources is for.
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
