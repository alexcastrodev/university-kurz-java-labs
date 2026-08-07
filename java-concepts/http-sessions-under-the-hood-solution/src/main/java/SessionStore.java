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
        String id = UUID.randomUUID().toString();
        sessions.put(id, new Session(now));
        return id;
    }

    public void touch(String sessionId, Instant now) {
        Session session = sessions.get(sessionId);
        if (session == null) {
            throw new NoSuchElementException(sessionId);
        }
        session.lastAccessed = now;
    }

    public void put(String sessionId, String key, Object value) {
        Session session = sessions.get(sessionId);
        if (session == null) {
            throw new NoSuchElementException(sessionId);
        }
        session.attributes.put(key, value);
    }

    public Object get(String sessionId, String key) {
        Session session = sessions.get(sessionId);
        if (session == null) {
            throw new NoSuchElementException(sessionId);
        }
        return session.attributes.get(key);
    }

    public boolean isExpired(String sessionId, Instant now, Duration timeout) {
        Session session = sessions.get(sessionId);
        if (session == null) {
            throw new NoSuchElementException(sessionId);
        }
        return Duration.between(session.lastAccessed, now).compareTo(timeout) > 0;
    }

    public String setCookieHeader(String sessionId) {
        if (!sessions.containsKey(sessionId)) {
            throw new NoSuchElementException(sessionId);
        }
        return "JSESSIONID=" + sessionId + "; Path=/; HttpOnly";
    }
}
