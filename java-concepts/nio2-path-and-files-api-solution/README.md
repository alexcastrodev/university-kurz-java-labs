# NIO.2: The Path and Files API - Solution

## Overview

This is the official solution for the NIO.2 Path and Files API lab. Every method is a one-liner (or close to it) because `Files` already ships the operation — the lesson is knowing which static method to reach for, and which of them carry a hidden contract you have to opt into.

## Key Concepts

### TODO-00: Writing text in one call

```java
public void writeText(Path file, String content) throws IOException {
    Files.writeString(file, content);
}
```

`Files.writeString()` opens the file, writes, and closes it — no `Writer`, no `try`-with-resources, no manual flushing. Its default options are `CREATE`, `TRUNCATE_EXISTING`, and `WRITE`, so the file is created if missing and fully replaced if it already exists. Text is encoded as UTF-8 unless you pass a `Charset`.

### TODO-01: Reading text in one call

```java
public String readText(Path file) throws IOException {
    return Files.readString(file);
}
```

The mirror image of `writeString`: it reads the whole file into a single `String` and closes it for you. Because it loads everything into memory at once, it's the right tool for config files and fixtures — for large files, stream them with `Files.lines()` instead.

### TODO-02: Listing a directory with a closeable Stream

```java
public List<String> listFileNames(Path directory) throws IOException {
    try (Stream<Path> entries = Files.list(directory)) {
        return entries.filter(Files::isRegularFile)
                .map(Path::getFileName)
                .map(Path::toString)
                .sorted()
                .toList();
    }
}
```

`Files.list()` returns a *lazily populated* `Stream<Path>` backed by an open `DirectoryStream` — the OS directory handle stays open while you consume it. That makes it the rare `Stream` that must be closed, so it's always wrapped in try-with-resources; forgetting it leaks a file descriptor. `Files.list()` is also shallow by design (use `Files.walk()` to recurse), and `filter(Files::isRegularFile)` is what drops the subdirectory from the result. `Path::getFileName` returns the last element of the path as a `Path`, so `toString()` turns it into a plain name.

### TODO-03: Copying, and opting into overwriting

```java
public void copy(Path source, Path target) throws IOException {
    Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
}
```

`Files.copy()` refuses to clobber data by default: without options it throws `FileAlreadyExistsException` when the target exists. `REPLACE_EXISTING` is how you say "yes, overwrite it" explicitly, which keeps accidental data loss from being the default behavior. Related options in the same family are `COPY_ATTRIBUTES` and `ATOMIC_MOVE` (for `Files.move()`).

### TODO-04 (optional): Deleting without checking first

```java
public void deleteIfPresent(Path file) throws IOException {
    Files.deleteIfExists(file);
}
```

`Files.delete()` throws `NoSuchFileException` when the file is already gone; `Files.deleteIfExists()` returns `false` instead. It also avoids the check-then-act race of calling `Files.exists()` first, since the existence check and the delete happen as one operation.

## Summary

- `Files` holds the verbs (`writeString`, `readString`, `list`, `copy`, `deleteIfExists`); `Path` is just the location they operate on.
- `Files.list()` and `Files.walk()` return lazily populated streams tied to an open directory handle — always close them with try-with-resources.
- The `Files` API defaults to *safe*: `copy` won't overwrite and `delete` won't tolerate a missing file until you opt in with `REPLACE_EXISTING` or `deleteIfExists`.
- Pick the `throws`-vs-reports variant deliberately, the same way you would with `Queue`'s `remove()` and `poll()`.
