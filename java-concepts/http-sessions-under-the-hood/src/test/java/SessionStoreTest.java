import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SessionStore")
class SessionStoreTest {

    private static final Instant START = Instant.parse("2024-01-01T10:00:00Z");
    private static final Duration TIMEOUT = Duration.ofMinutes(30);
    private static final String UNKNOWN_ID = "no-such-session";

    @Test
    @DisplayName("should return a usable id when creating a session")
    void shouldReturnUsableIdWhenCreatingSession() {
        SessionStore store = new SessionStore();

        String sessionId = store.createSession(START);

        assertNotNull(sessionId);
        assertFalse(sessionId.isBlank());
    }

    @Test
    @DisplayName("should return a different id for every created session")
    void shouldReturnDifferentIdForEveryCreatedSession() {
        SessionStore store = new SessionStore();

        String first = store.createSession(START);
        String second = store.createSession(START);

        assertNotEquals(first, second);
    }

    @Test
    @DisplayName("should round-trip an attribute stored in a session")
    void shouldRoundTripAttributeStoredInSession() {
        SessionStore store = new SessionStore();
        String sessionId = store.createSession(START);

        store.put(sessionId, "username", "ann");

        assertEquals("ann", store.get(sessionId, "username"));
    }

    @Test
    @DisplayName("should keep attributes of different sessions separate")
    void shouldKeepAttributesOfDifferentSessionsSeparate() {
        SessionStore store = new SessionStore();
        String first = store.createSession(START);
        String second = store.createSession(START);

        store.put(first, "username", "ann");
        store.put(second, "username", "bob");

        assertEquals("ann", store.get(first, "username"));
        assertEquals("bob", store.get(second, "username"));
    }

    @Test
    @DisplayName("should return null when reading a key that was never stored")
    void shouldReturnNullWhenReadingKeyThatWasNeverStored() {
        SessionStore store = new SessionStore();
        String sessionId = store.createSession(START);

        assertNull(store.get(sessionId, "username"));
    }

    @Test
    @DisplayName("should throw when putting an attribute on an unknown session")
    void shouldThrowWhenPuttingAttributeOnUnknownSession() {
        SessionStore store = new SessionStore();

        assertThrows(NoSuchElementException.class, () -> store.put(UNKNOWN_ID, "username", "ann"));
    }

    @Test
    @DisplayName("should throw when reading an attribute of an unknown session")
    void shouldThrowWhenReadingAttributeOfUnknownSession() {
        SessionStore store = new SessionStore();

        assertThrows(NoSuchElementException.class, () -> store.get(UNKNOWN_ID, "username"));
    }

    @Test
    @DisplayName("should throw when touching an unknown session")
    void shouldThrowWhenTouchingUnknownSession() {
        SessionStore store = new SessionStore();

        assertThrows(NoSuchElementException.class, () -> store.touch(UNKNOWN_ID, START));
    }

    @Test
    @DisplayName("should throw when checking expiration of an unknown session")
    void shouldThrowWhenCheckingExpirationOfUnknownSession() {
        SessionStore store = new SessionStore();

        assertThrows(NoSuchElementException.class, () -> store.isExpired(UNKNOWN_ID, START, TIMEOUT));
    }

    @Test
    @DisplayName("should throw when building the Set-Cookie header for an unknown session")
    void shouldThrowWhenBuildingSetCookieHeaderForUnknownSession() {
        SessionStore store = new SessionStore();

        assertThrows(NoSuchElementException.class, () -> store.setCookieHeader(UNKNOWN_ID));
    }

    @Test
    @DisplayName("should not expire a session still inside the timeout window")
    void shouldNotExpireSessionStillInsideTimeoutWindow() {
        SessionStore store = new SessionStore();
        String sessionId = store.createSession(START);

        Instant twentyNineMinutesLater = START.plus(Duration.ofMinutes(29));

        assertFalse(store.isExpired(sessionId, twentyNineMinutesLater, TIMEOUT));
    }

    @Test
    @DisplayName("should expire a session once the timeout window has passed")
    void shouldExpireSessionOnceTimeoutWindowHasPassed() {
        SessionStore store = new SessionStore();
        String sessionId = store.createSession(START);

        Instant thirtyOneMinutesLater = START.plus(Duration.ofMinutes(31));

        assertTrue(store.isExpired(sessionId, thirtyOneMinutesLater, TIMEOUT));
    }

    @Test
    @DisplayName("should extend the life of a session when it is touched")
    void shouldExtendLifeOfSessionWhenItIsTouched() {
        SessionStore store = new SessionStore();
        String untouched = store.createSession(START);
        String touched = store.createSession(START);

        store.touch(touched, START.plus(Duration.ofMinutes(20)));

        Instant thirtyFiveMinutesLater = START.plus(Duration.ofMinutes(35));

        assertTrue(store.isExpired(untouched, thirtyFiveMinutesLater, TIMEOUT));
        assertFalse(store.isExpired(touched, thirtyFiveMinutesLater, TIMEOUT));
    }

    @Test
    @DisplayName("should build the Set-Cookie header carrying the session id")
    void shouldBuildSetCookieHeaderCarryingSessionId() {
        SessionStore store = new SessionStore();
        String sessionId = store.createSession(START);

        assertEquals("JSESSIONID=" + sessionId + "; Path=/; HttpOnly", store.setCookieHeader(sessionId));
    }
}
