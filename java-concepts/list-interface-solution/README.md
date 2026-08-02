# The List Interface - Solution

## Overview

This is the official solution for the List Interface lab, including the `subList`-as-a-view behavior.

## Key Concepts

### subList returns a view, not a copy

```java
public List<String> nextUp(int fromIndex, int toIndex) {
    return songs.subList(fromIndex, toIndex);
}
```

The returned `List` shares storage with `songs` over that index range. Calling `.set()` or `.remove()` on the view mutates `songs` directly — there is no independent copy to keep in sync.

### CASE_INSENSITIVE_ORDER

```java
songs.sort(String.CASE_INSENSITIVE_ORDER);
```

`String.CASE_INSENSITIVE_ORDER` is a ready-made `Comparator<String>` — no need to write `(a, b) -> a.compareToIgnoreCase(b)` by hand.

### replaceAll applies a function to every element in place

```java
songs.replaceAll(String::toUpperCase);
```

Unlike `stream().map(...)`, which produces a new stream, `replaceAll` mutates the list itself.

## Summary

- `subList` is a live window into the original list — mutate the view, mutate the source.
- `String.CASE_INSENSITIVE_ORDER` avoids hand-writing a case-insensitive comparator.
- `replaceAll` transforms a list in place; it's declared on `List`, not `Collection`.
