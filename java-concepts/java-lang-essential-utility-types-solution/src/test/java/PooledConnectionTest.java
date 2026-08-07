import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PooledConnection")
class PooledConnectionTest {

    @Test
    @DisplayName("should report a fresh connection as open")
    void shouldReportFreshConnectionAsOpen() {
        PooledConnection connection = new PooledConnection("c1");

        assertFalse(connection.isClosed());
    }

    @Test
    @DisplayName("should report the connection as closed after close()")
    void shouldReportConnectionAsClosedAfterClose() {
        PooledConnection connection = new PooledConnection("c1");

        connection.close();

        assertTrue(connection.isClosed());
    }

    @Test
    @DisplayName("should not throw when close() is called twice")
    void shouldNotThrowWhenClosedTwice() {
        PooledConnection connection = new PooledConnection("c1");
        connection.close();

        assertDoesNotThrow(connection::close);
        assertTrue(connection.isClosed());
    }
}
