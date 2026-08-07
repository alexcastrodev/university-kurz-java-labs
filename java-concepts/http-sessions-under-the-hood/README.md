# HTTP Sessions Under the Hood

## Goal

Understand what a servlet container's `HttpSession` mechanism actually does internally — generating a session id, storing attributes server-side, tracking activity to decide expiration, and handing the id back to the client through a `Set-Cookie` header — by building a minimal version of it yourself.

## Prerequisites

- Basic Java syntax
- `java.time.Instant` and `java.time.Duration`
- Familiarity with `Map`

## Task

`SessionStore` is a tiny, in-memory stand-in for the session table a servlet container keeps. Each session is an id plus a bag of attributes plus the last time it was accessed. You'll implement creating a session, reading and writing its attributes, marking it as accessed, and deciding whether it has timed out.

There is no servlet container and no HTTP here — just the bookkeeping underneath it.

Notice that every time-sensitive method takes an `Instant` parameter instead of calling `Instant.now()`. That is deliberate: expiration logic that reads the system clock can only be tested by actually waiting. Passing the current time in makes the whole lab testable with fixed instants and no `Thread.sleep()`.

## Instructions

Complete the following TODOs in `SessionStore`:

- TODO-00: Implement `createSession()` — generate a unique id, store a fresh session recording `now`, and return the id.
- TODO-01: Implement `touch()` — move the session's last-accessed time to `now`.
- TODO-02: Implement `put()` — store an attribute value under a key.
- TODO-03: Implement `get()` — read an attribute value, returning `null` when the key was never stored.
- TODO-04: Implement `isExpired()` — report whether the time elapsed since the last access exceeds the timeout.

Unknown session ids must throw `NoSuchElementException`.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/http-sessions-under-the-hood test
```

Or from the lab directory:

```bash
cd java-concepts/http-sessions-under-the-hood
mvn test
```

## Bonus (Optional)

- TODO-05 (optional): Implement `setCookieHeader()` — build the raw `Set-Cookie` header value (`JSESSIONID=<id>; Path=/; HttpOnly`) that tells the browser which session it belongs to.
