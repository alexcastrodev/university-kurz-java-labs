import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ConnectionUtils")
class ConnectionUtilsTest {

    private final ConnectionUtils utils = new ConnectionUtils();

    @Test
    @DisplayName("should return the monies sorted ascending by amount")
    void shouldReturnMoniesSortedAscendingByAmount() {
        List<Money> monies = List.of(
                new Money("USD", 500),
                new Money("USD", 100),
                new Money("USD", 300));

        List<Money> sorted = utils.sortedByAmount(monies);

        assertEquals(List.of(
                new Money("USD", 100),
                new Money("USD", 300),
                new Money("USD", 500)), sorted);
    }

    @Test
    @DisplayName("should not mutate the original list")
    void shouldNotMutateTheOriginalList() {
        List<Money> monies = new ArrayList<>(List.of(
                new Money("USD", 500),
                new Money("USD", 100),
                new Money("USD", 300)));

        utils.sortedByAmount(monies);

        assertEquals(List.of(
                new Money("USD", 500),
                new Money("USD", 100),
                new Money("USD", 300)), monies);
    }

    @Test
    @DisplayName("should close the connection after the action runs normally")
    void shouldCloseTheConnectionAfterTheActionRunsNormally() {
        AtomicReference<PooledConnection> captured = new AtomicReference<>();

        utils.openAndUse(() -> new PooledConnection("c1"), captured::set);

        assertNotNull(captured.get());
        assertTrue(captured.get().isClosed());
    }

    @Test
    @DisplayName("should close the connection even when the action throws")
    void shouldCloseTheConnectionEvenWhenTheActionThrows() {
        AtomicReference<PooledConnection> captured = new AtomicReference<>();

        assertThrows(RuntimeException.class, () -> utils.openAndUse(
                () -> new PooledConnection("c2"),
                connection -> {
                    captured.set(connection);
                    throw new RuntimeException("boom");
                }));

        assertNotNull(captured.get());
        assertTrue(captured.get().isClosed());
    }
}
