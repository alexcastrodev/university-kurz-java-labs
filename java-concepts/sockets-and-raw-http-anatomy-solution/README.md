# Sockets and the Raw Anatomy of an HTTP Request - Solution

## Overview

This is the official solution for the Sockets and the Raw Anatomy of an HTTP Request lab. It shows an HTTP/1.1 request as the byte-exact text it actually is, built and parsed by hand, and then sent over a real `Socket`.

## Key Concepts

### The request line, terminated by CRLF

```java
public String requestLine(String method, String path) {
    return method + " " + path + " HTTP/1.1\r\n";
}
```

Every HTTP message starts with a single line of three space-separated tokens: method, request target, and protocol version. The line terminator is `\r\n` (carriage return + line feed), not `\n` — HTTP inherited CRLF from the older internet text protocols, and a parser on the other side is entitled to reject a message that uses bare `\n`. Building this string by hand is the whole reason `System.lineSeparator()` is the wrong tool here: the terminator is fixed by the protocol, not by the platform.

### Headers, and computing Content-Length in bytes

```java
Map<String, String> allHeaders = new LinkedHashMap<>(headers);
if (body != null && !body.isEmpty()) {
    allHeaders.put("Content-Length", String.valueOf(body.getBytes(StandardCharsets.UTF_8).length));
}
for (Map.Entry<String, String> header : allHeaders.entrySet()) {
    sb.append(header.getKey()).append(": ").append(header.getValue()).append("\r\n");
}
sb.append("\r\n");
```

`Content-Length` counts **bytes**, not characters, which is why it comes from `body.getBytes(StandardCharsets.UTF_8).length` and never from `body.length()`. For pure ASCII the two agree, so the bug hides; the moment the body contains `é` or an emoji, `String.length()` under-reports and the receiver either truncates the body or blocks waiting for bytes that will never arrive. Copying into a fresh `LinkedHashMap` also keeps the caller's map untouched while preserving their header order.

### The blank line is the structural separator

```java
sb.append("\r\n");
if (body != null && !body.isEmpty()) {
    sb.append(body);
}
```

The empty line — a `\r\n` immediately after the last header's own `\r\n`, producing `\r\n\r\n` — is what tells a parser "headers are over." Everything after it is body bytes, read according to `Content-Length` (or chunked encoding). The blank line is emitted even when there is no body, because its absence means the parser is still waiting for more headers and the request never completes.

### Parsing headers back out

```java
Map<String, String> result = new LinkedHashMap<>();
for (String line : rawHeaderBlock.split("\r\n")) {
    if (line.isBlank()) continue;
    int colon = line.indexOf(':');
    result.put(line.substring(0, colon), line.substring(colon + 1).trim());
}
return result;
```

Splitting on the *first* colon matters: values legitimately contain colons (`Host: example.com:8080`, timestamps in `Date`), so `split(":")` would corrupt them. The space after the colon is optional whitespace in the grammar, hence the `trim()`, and `LinkedHashMap` keeps the wire order so a round-trip through `buildRequest`/`parseHeaders` is stable.

### Extracting the method

```java
public String parseMethod(String rawRequest) {
    return rawRequest.substring(0, rawRequest.indexOf(' '));
}
```

The method is simply everything before the first space of the first line. No regex, no tokenizer — the request line's grammar is rigid enough that a single index lookup is the honest implementation.

### Writing it to a real socket

```java
try (Socket socket = new Socket(host, port)) {
    OutputStream out = socket.getOutputStream();
    out.write(rawRequest.getBytes(StandardCharsets.UTF_8));
    out.flush();

    InputStream in = socket.getInputStream();
    return new String(in.readAllBytes(), StandardCharsets.UTF_8);
}
```

A `Socket` knows nothing about HTTP — it moves bytes. `readAllBytes()` blocks until the peer closes its side of the connection, which is why the test's throwaway server writes its canned response and then closes: with HTTP/1.1 keep-alive, a real server would leave the connection open and the correct read would instead be bounded by `Content-Length`. That gap is exactly the work `java.net.http.HttpClient` does for you.

## Summary

- An HTTP request is text with a fixed shape: request line, header lines, a blank line, then the body — all terminated by `\r\n`, never bare `\n`.
- `Content-Length` must be the UTF-8 **byte** count of the body; using `String.length()` works only until the body stops being ASCII.
- The `\r\n\r\n` blank line is the signal that headers have ended and body bytes begin.
- A `Socket` transports bytes and nothing more; framing, keep-alive, and redirects are the HTTP client's job, not the socket's.
