# HttpClient: Sync, Async, and a Local Test Server - Solution

## Overview

This is the official solution for the HttpClient lab. It shows the builder-based request/response flow, and how to translate a non-2xx status code and an async failure into explicit, handled outcomes instead of silent ones.

## Key Concepts

### Building requests with the fluent builder

```java
HttpRequest request = HttpRequest.newBuilder()
    .uri(uri)
    .header("Accept", "application/json")
    .GET()
    .build();
```

Like `HttpClient` itself, an `HttpRequest` is immutable once built — headers, method, and URI are all fixed at build time, which is what makes a single built request safe to send more than once if needed.

### Synchronous vs. asynchronous sending

```java
HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
```

```java
client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
    .thenApply(HttpResponse::body);
```

`send()` blocks the calling thread until the response arrives. `sendAsync()` returns immediately with a `CompletableFuture`, and the actual response is only available once that future completes.

### Async failures don't throw — they surface through the future

```java
.exceptionally(ex -> fallback);
```

A synchronous `send()` throws `IOException` directly on a connection failure. An async chain instead wraps that failure inside the `CompletableFuture` itself — without an `.exceptionally()`/`.handle()` stage, a failed request produces no visible error until something calls `.get()`/`.join()` on the future and the exception resurfaces there, often far from where the request was made.

## Summary

- Both `HttpClient` and `HttpRequest` are immutable once built, and safe to reuse.
- `send()` blocks; `sendAsync()` composes with `CompletableFuture` but changes how failures propagate.
- Always add an `.exceptionally()`/`.handle()` stage to an async chain — a failure with no recovery stage is easy to lose track of.
