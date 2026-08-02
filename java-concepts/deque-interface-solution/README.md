# Deque as a Bounded, Undoable Command History - Solution

## Overview

This is the official solution for the Deque Interface lab. It shows how a bounded command history can lean entirely on `Deque`'s reporting methods to fail gracefully instead of throwing.

## Key Concepts

### Evicting instead of throwing

```java
commands.addLast(command);
if (commands.size() > capacity) {
    commands.pollFirst();
}
```

`ArrayDeque` has no built-in fixed-capacity check, so the capacity limit is enforced manually. Eviction uses `pollFirst()` — the reporting form — which is the right instinct even though it can never actually be empty here: it documents the intent that eviction is a normal, expected event, not an error.

### Safe undo with pollLast()

```java
public String undoLast() {
    return commands.pollLast();
}
```

Using `removeLast()` here would throw `NoSuchElementException` on an empty history — exactly the "two different ways to fail" trap `Deque` sets up between its throwing and reporting method families. `pollLast()` reports emptiness as `null` instead, which is what a caller of `undoLast()` on an empty history actually wants.

### Reverse iteration with descendingIterator()

```java
Iterator<String> it = commands.descendingIterator();
```

Since commands are appended with `addLast`, the deque's natural (head-to-tail) order is oldest-to-newest — `descendingIterator()` walks tail-to-head, giving newest-to-oldest without maintaining a second, reversed data structure.

## Summary

- Use the throwing methods (`addFirst`/`removeLast`, etc.) when failure indicates a real bug; use the reporting ones (`offer*`/`poll*`) when an empty or full deque is an expected outcome, not an error.
- `descendingIterator()` gives you the reverse of a deque's natural order without extra bookkeeping.
- A bounded, auto-evicting collection is a natural fit for `Deque`'s dual-ended reporting methods.
