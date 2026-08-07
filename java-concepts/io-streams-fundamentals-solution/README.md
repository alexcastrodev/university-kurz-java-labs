# java.io: Streams, Closing Resources, and Serialization - Solution

## Overview

This is the official solution for the java.io streams lab. Each method does one small job on a stream the caller owns, and none of them close what they were handed.

## Key Concepts

### Wrapping a caller-owned Reader without try-with-resources

```java
public int countLines(Reader reader) throws IOException {
    BufferedReader buffered = new BufferedReader(reader);
    int count = 0;
    while (buffered.readLine() != null) {
        count++;
    }
    return count;
}
```

`BufferedReader` is created here, so the instinct is to try-with-resources it — but closing a wrapper closes the stream it wraps, and that stream belongs to the caller. Leaving the wrapper unclosed is deliberate: it holds no OS resource of its own, only a buffer, and the caller stays free to keep using the `Reader` afterwards.

### transferTo(): the copy loop the JDK now writes for you

```java
public void copy(InputStream in, OutputStream out) throws IOException {
    in.transferTo(out);
}
```

Before JDK 9 this was a hand-rolled `byte[] buffer = new byte[8192]` loop with a `read`/`write` pair and an off-by-one trap in the length argument. `transferTo()` replaced all of it with one call — and, like the loop it replaces, it closes neither stream.

### readAllBytes() plus an explicit charset

```java
public String readAllText(InputStream in) throws IOException {
    return new String(in.readAllBytes(), StandardCharsets.UTF_8);
}
```

`readAllBytes()` drains the stream into a single array. Turning bytes into a `String` always requires a charset; naming `StandardCharsets.UTF_8` instead of relying on the platform default keeps the result identical on every machine.

### newLine() and flushing a buffer you own

```java
public void writeLines(Writer writer, List<String> lines) throws IOException {
    BufferedWriter buffered = new BufferedWriter(writer);
    for (String line : lines) {
        buffered.write(line);
        buffered.newLine();
    }
    buffered.flush();
}
```

`newLine()` emits the platform's line separator, which is exactly what `BufferedReader.readLine()` knows how to split on, so the round trip is lossless. The `flush()` is essential: since the wrapper is never closed, nothing else would push its buffered characters down into the caller's `Writer`.

## Summary

- A method that receives an open stream does not close it — whoever opened it closes it.
- Closing a wrapper (`BufferedReader`, `BufferedWriter`) closes the underlying stream too, which is why try-with-resources is wrong for a wrapper around a caller-owned stream.
- If you buffer without closing, you must `flush()` or the data never reaches the destination.
- Prefer the standard library's one-call helpers — `transferTo()`, `readAllBytes()` — over hand-written byte-buffer loops, and always name the charset when converting bytes to text.
