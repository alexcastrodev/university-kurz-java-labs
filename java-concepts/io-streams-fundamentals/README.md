# java.io: Streams, Closing Resources, and Serialization

## Goal

Work with the classic `java.io` stream types — byte streams (`InputStream`/`OutputStream`) and character streams (`Reader`/`Writer`) — and understand who owns closing a stream: the code that passes one into a method, or the method itself.

## Prerequisites

- Basic Java syntax
- Checked exceptions (`IOException`)

## Task

`StreamTools` is a small toolbox of stream helpers. You'll implement four of them: counting the lines a `Reader` can produce, copying every byte from one stream to another, reading a whole `InputStream` as UTF-8 text, and writing a list of lines to a `Writer` so they can be read back line by line.

Every one of these methods receives an already-open stream from the caller. None of them close it — the caller opened it, the caller owns it, and closing it early would break code that still needs it. Only a resource a method opens itself is that method's to close.

## Instructions

Complete the following TODOs in `StreamTools`:

- TODO-00: Implement `countLines()` by wrapping the `Reader` in a `BufferedReader` and reading until `readLine()` returns `null`.
- TODO-01: Implement `copy()` so every byte of the `InputStream` ends up in the `OutputStream`.
- TODO-02: Implement `readAllText()` by reading all bytes and decoding them as UTF-8.
- TODO-03: Implement `writeLines()` so each line is written followed by a line separator.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/io-streams-fundamentals test
```

Or from the lab directory:

```bash
cd java-concepts/io-streams-fundamentals
mvn test
```
