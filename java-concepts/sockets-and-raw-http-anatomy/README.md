# Sockets and the Raw Anatomy of an HTTP Request

## Goal

See exactly what bytes an HTTP request is made of — the request line, the header lines, the blank-line separator, and the body — by building and parsing that text directly instead of going through an HTTP client library.

## Prerequisites

- Basic Java syntax
- Familiarity with `Map`

## Task

`RawHttpMessage` treats an HTTP request as what it really is on the wire: a plain string of text with a very strict shape. You'll assemble a request line, append headers, separate them from the body with a blank line, and then parse that same text back apart — the exact work an HTTP client hides from you.

## Instructions

Complete the following TODOs in `RawHttpMessage`:

- TODO-00: Implement `requestLine()`, returning `"METHOD path HTTP/1.1\r\n"`.
- TODO-01: Implement `buildRequest()`, emitting the request line, one `Name: value\r\n` line per header, a blank line, then the body — adding `Content-Length` automatically when a body is present.
- TODO-02: Implement `parseHeaders()`, turning a block of header lines into an order-preserving `Map`.
- TODO-03: Implement `parseMethod()`, extracting just the method token from a raw request.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/sockets-and-raw-http-anatomy test
```

Or from the lab directory:

```bash
cd java-concepts/sockets-and-raw-http-anatomy
mvn test
```

## Bonus (Optional)

- TODO-04 (optional): Implement `sendOverSocket()`, opening a real `java.net.Socket`, writing the raw request bytes, and reading back everything the other end sends. The test for it stands up a throwaway server with `java.net.ServerSocket` on an ephemeral port, so it is a genuine round-trip over the network stack — no HTTP library involved.
