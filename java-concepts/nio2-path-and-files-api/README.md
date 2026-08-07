# NIO.2: The Path and Files API

## Goal

Use `Path` and `Files` for everyday file I/O — reading, writing, listing, and copying — without the ceremony of the older `java.io.File` API.

## Prerequisites

- Basic Java syntax
- Checked exceptions (`IOException`)

## Task

`FileVault` is a small helper around the filesystem. A `Path` describes *where* something is; the `Files` utility class does the actual *work*. You'll implement writing text to a file, reading it back, listing the file names directly inside a directory, and copying a file over an existing one.

## Instructions

Complete the following TODOs in `FileVault`:

- TODO-00: Implement `writeText()` — write the given content to the file, creating it if necessary.
- TODO-01: Implement `readText()` — read and return the file's full text content.
- TODO-02: Implement `listFileNames()` — return the names (not full paths) of the regular files directly inside the directory, sorted alphabetically, without recursing.
- TODO-03: Implement `copy()` — copy the source to the target, overwriting the target if it already exists.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/nio2-path-and-files-api test
```

Or from the lab directory:

```bash
cd java-concepts/nio2-path-and-files-api
mvn test
```

## Bonus (Optional)

- TODO-04 (optional): Implement `deleteIfPresent()` — delete the file if it exists, and do nothing (no exception) if it doesn't.
