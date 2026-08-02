# Iterator vs Iterable - Solution

## Overview

This is the official solution for the Iterator vs Iterable lab. It shows how a JDK-style `Iterable`/`Iterator` pair is actually implemented under the hood.

## Key Concepts

### Iterable forces you to supply an Iterator

```java
public class NameRoster implements Iterable<String> {
    @Override
    public Iterator<String> iterator() {
        return new RosterIterator();
    }
}
```

Without `iterator()`, `NameRoster` wouldn't compile — that's the whole contract of `Iterable`.

### hasNext() / next(): the cursor walk

```java
public boolean hasNext() {
    return cursor < names.size();
}

public String next() {
    if (!hasNext()) throw new NoSuchElementException();
    lastReturned = cursor;
    return names.get(cursor++);
}
```

`next()` always checks `hasNext()` itself rather than trusting the caller — the `Iterator` contract requires throwing `NoSuchElementException`, not returning `null` or crashing with an index error.

### remove(): the tricky part

```java
public void remove() {
    if (lastReturned < 0) throw new IllegalStateException();
    names.remove(lastReturned);
    cursor = lastReturned;
    lastReturned = -1;
}
```

After `names.remove(lastReturned)`, every element after that index shifts down by one. If `cursor` stayed where it was, the element that just slid into `lastReturned`'s old slot would be skipped entirely. Rewinding `cursor` back to `lastReturned` fixes that. Resetting `lastReturned` to `-1` also blocks a second `remove()` call before the next `next()` — matching the real `Iterator` contract, which requires exactly one `remove()` per `next()`.

## Summary

- Implementing `Iterable` is a one-method contract, but it's the gateway to `for-each`.
- `next()` should validate `hasNext()` itself rather than trusting callers.
- `remove()` must rewind the cursor after a structural change, or the next element gets silently skipped.
