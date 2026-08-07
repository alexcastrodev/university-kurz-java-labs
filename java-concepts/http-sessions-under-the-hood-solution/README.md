# HTTP Sessions Under the Hood - Solution

## Overview

This is the official solution for the HTTP Sessions Under the Hood lab. It implements the four pieces a servlet container needs to make `HttpSession` work: an id generator, a server-side attribute table keyed by that id, an activity timestamp used to decide expiration, and the `Set-Cookie` header that lets the client present its id on the next request.

## Key Concepts

### TODO-00: The session id is the only thing the client ever holds

```java
public String createSession(Instant now) {
    String id = UUID.randomUUID().toString();
    sessions.put(id, new Session(now));
    return id;
}
```

All the real state — the attributes — stays on the server, indexed by this id. That is why the id must be unpredictable: anyone who guesses a valid id gets the session behind it. `UUID.randomUUID()` is backed by a cryptographically strong generator, which is why it is a reasonable stand-in for a container's id generator.

### TODO-01: `touch()` records activity, and nothing else does

```java
public void touch(String sessionId, Instant now) {
    Session session = sessions.get(sessionId);
    if (session == null) {
        throw new NoSuchElementException(sessionId);
    }
    session.lastAccessed = now;
}
```

Keeping `touch()` separate from `put()` makes "what counts as activity" an explicit decision instead of an accident. A real container refreshes the timestamp on every request that resolves the session — but that is a policy it chose, not a law. If every read silently extended the session, a background poll from an idle browser tab would keep a logged-in session alive forever.

### TODO-02 and TODO-03: attributes are a plain server-side map

```java
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
```

Two failure modes are deliberately different here. An unknown *session* is a broken or expired id and throws; a missing *key* inside a valid session is ordinary and returns `null` — exactly the contract of `HttpSession.getAttribute()`. Note that this map lives in one JVM's heap, which is precisely why sessions do not survive a restart and why clustered deployments need sticky sessions or an external session store.

### TODO-04: compare elapsed time against a timeout, don't store an expiry instant

```java
public boolean isExpired(String sessionId, Instant now, Duration timeout) {
    Session session = sessions.get(sessionId);
    if (session == null) {
        throw new NoSuchElementException(sessionId);
    }
    return Duration.between(session.lastAccessed, now).compareTo(timeout) > 0;
}
```

Storing *when the session was last used* and comparing against a timeout supplied at check time keeps the policy out of the data. Change the timeout from 30 minutes to 15 and every existing session immediately obeys the new rule; had each session cached an absolute expiry instant, you would have to rewrite every stored entry. `Duration.compareTo` also expresses "strictly greater than the timeout" precisely, without any millisecond arithmetic.

### TODO-05 (optional): the header that ties a client back to its session

```java
public String setCookieHeader(String sessionId) {
    if (!sessions.containsKey(sessionId)) {
        throw new NoSuchElementException(sessionId);
    }
    return "JSESSIONID=" + sessionId + "; Path=/; HttpOnly";
}
```

`HttpOnly` keeps JavaScript from reading the cookie, which blunts session theft via XSS; `Path=/` scopes it to the whole application. A real container does more than build this string: it writes the header onto the response only when the session is new, parses the incoming `Cookie` header to resolve the id on later requests, falls back to URL rewriting when cookies are disabled, adds `Secure` and `SameSite`, and runs a background reaper that evicts expired sessions instead of waiting for someone to ask.

## Summary

- A session is an unguessable id on the client plus a map of attributes on the server; the cookie carries only the id.
- Expiration is derived from a last-accessed timestamp compared against a timeout, so the timeout policy can change without touching stored sessions.
- Making "what counts as activity" an explicit call (`touch()`) rather than a side effect of every read keeps session lifetime under your control.
- Taking `Instant` as a parameter instead of reading the system clock is what makes time-based logic testable without sleeping.
