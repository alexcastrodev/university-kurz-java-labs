import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TicketCounter")
class TicketCounterTest {

    @Test
    @DisplayName("should report success when enqueueing within capacity")
    void shouldReportSuccessWhenEnqueueingWithinCapacity() {
        TicketCounter counter = new TicketCounter(2);

        assertTrue(counter.tryEnqueue("Ann"));
        assertEquals(1, counter.waiting());
    }

    @Test
    @DisplayName("should report failure when enqueueing beyond capacity")
    void shouldReportFailureWhenEnqueueingBeyondCapacity() {
        TicketCounter counter = new TicketCounter(1);
        counter.tryEnqueue("Ann");

        assertFalse(counter.tryEnqueue("Bob"));
        assertEquals(1, counter.waiting());
    }

    @Test
    @DisplayName("should peek at the head customer without removing them")
    void shouldPeekAtHeadWithoutRemoving() {
        TicketCounter counter = new TicketCounter(2);
        counter.tryEnqueue("Ann");

        assertEquals("Ann", counter.nextInLine());
        assertEquals("Ann", counter.nextInLine());
        assertEquals(1, counter.waiting());
    }

    @Test
    @DisplayName("should return null from nextInLine when the line is empty")
    void shouldReturnNullFromNextInLineWhenEmpty() {
        TicketCounter counter = new TicketCounter(2);

        assertNull(counter.nextInLine());
    }

    @Test
    @DisplayName("should serve and remove the head customer")
    void shouldServeAndRemoveHeadCustomer() {
        TicketCounter counter = new TicketCounter(2);
        counter.tryEnqueue("Ann");
        counter.tryEnqueue("Bob");

        assertEquals("Ann", counter.serveNext());
        assertEquals(1, counter.waiting());
    }

    @Test
    @DisplayName("should throw from serveNext when the line is empty")
    void shouldThrowFromServeNextWhenLineIsEmpty() {
        TicketCounter counter = new TicketCounter(2);

        assertThrows(NoSuchElementException.class, counter::serveNext);
    }

    @Test
    @DisplayName("should return null from serveNextOrNull when the line is empty")
    void shouldReturnNullFromServeNextOrNullWhenEmpty() {
        TicketCounter counter = new TicketCounter(2);

        assertNull(counter.serveNextOrNull());
    }

    @Test
    @DisplayName("should serve customers in first-in, first-out order")
    void shouldServeInFifoOrder() {
        TicketCounter counter = new TicketCounter(3);
        counter.tryEnqueue("Ann");
        counter.tryEnqueue("Bob");
        counter.tryEnqueue("Cid");

        assertEquals("Ann", counter.serveNext());
        assertEquals("Bob", counter.serveNext());
        assertEquals("Cid", counter.serveNext());
    }
}
