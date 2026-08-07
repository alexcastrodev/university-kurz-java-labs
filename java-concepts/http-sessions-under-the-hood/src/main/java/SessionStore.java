import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

public class SessionStore {

    private static class Session {
        final Map<String, Object> attributes = new HashMap<>();
        Instant lastAccessed;

        Session(Instant createdAt) {
            this.lastAccessed = createdAt;
        }
    }

    private final Map<String, Session> sessions = new HashMap<>();

    public String createSession(Instant now) {
        // TODO-00: Generate a new, unique session id, store a fresh Session for it
        // recording `now` as its last-accessed time, and return the id.
        // Hint: UUID.randomUUID().toString() makes a good session id.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void touch(String sessionId, Instant now) {
        // TODO-01: Update the session's last-accessed time to `now`.
        // Throw new NoSuchElementException(sessionId) if the id is unknown.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void put(String sessionId, String key, Object value) {
        // TODO-02: Store `value` under `key` in the session's attributes.
        // Throw new NoSuchElementException(sessionId) if the id is unknown.
        // This does NOT update last-accessed time (only touch() and createSession() do,
        // to keep the "what counts as activity" decision explicit for this exercise).
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public Object get(String sessionId, String key) {
        // TODO-03: Return the value stored under `key`, or null if the session has no
        // such key. Throw new NoSuchElementException(sessionId) if the id is unknown.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean isExpired(String sessionId, Instant now, Duration timeout) {
        // TODO-04: Return true if the time elapsed since the session's last-accessed
        // time (relative to `now`) is greater than `timeout`.
        // Throw new NoSuchElementException(sessionId) if the id is unknown.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public String setCookieHeader(String sessionId) {
        // TODO-05 (optional): Build the raw Set-Cookie response header value for this
        // session id, e.g. "JSESSIONID=<id>; Path=/; HttpOnly" (no leading "Set-Cookie: ").
        // Throw new NoSuchElementException(sessionId) if the id is unknown.
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
