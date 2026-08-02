# HttpClient: Sync, Async, and a Local Test Server

## Goal

Learn the builder-based `HttpClient`/`HttpRequest` API, the difference between blocking `send()` and `CompletableFuture`-returning `sendAsync()`, and why forgetting `.exceptionally()` on an async chain lets failures disappear silently.

## Prerequisites

- Basic Java syntax
- `CompletableFuture` basics (`thenApply`, `exceptionally`)
- Exception handling

## Task

Implement `ApiClient`, a small wrapper around `java.net.http.HttpClient`. The tests spin up a real local HTTP server (`com.sun.net.httpserver.HttpServer`, part of the JDK — no extra dependency, no live network access) so you exercise the real `send()`/`sendAsync()` flow without depending on any external service.

## Instructions

Complete the following TODOs in `ApiClient`:

- TODO-00: Build a GET `HttpRequest` with an `Accept: application/json` header.
- TODO-01: Send it synchronously and throw `ApiException` for a non-2xx status code.
- TODO-02: Send the same kind of request asynchronously and return just the body.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/httpclient-and-java-net-http test
```

Or from the lab directory:

```bash
cd java-concepts/httpclient-and-java-net-http
mvn test
```

## Bonus

- TODO-03 (optional): Recover from a failed async request with a fallback value, using `CompletableFuture.exceptionally()`.
