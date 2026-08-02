# The Queue Interface - Solution

## Overview

This is the official solution for the Queue Interface lab, showing the throwing vs. reporting method pairs side by side.

## Key Concepts

### offer() vs. add(): reporting a full queue

```java
public boolean tryEnqueue(String customer) {
    return line.offer(customer);
}
```

`add()` (inherited from `Collection`) would throw `IllegalStateException` on a full bounded queue. `offer()` does the same job but returns `false` instead — the right choice when "the line is full" is an expected outcome the caller will branch on.

### peek() vs. element(): reporting an empty queue

```java
public String nextInLine() {
    return line.peek();
}
```

`element()` throws `NoSuchElementException` on empty; `peek()` returns `null`. Since checking whether anyone is in line is a normal thing to do, `peek()` fits here.

### remove() vs. poll(): choosing the failure mode on purpose

```java
public String serveNext() {
    return line.remove();       // throws if empty — an empty-line call here is a bug
}

public String serveNextOrNull() {
    return line.poll();         // null if empty — an expected, checkable outcome
}
```

Both remove and return the head of the queue; they only differ in how "nothing to serve" is reported. Exposing both lets each caller pick the one that matches whether an empty line, at that call site, is a bug or a normal case.

## Summary

- `offer`/`peek`/`poll` report failure through their return value; `add`/`element`/`remove` throw.
- Use the throwing family when an empty/full queue signals a caller bug; use the reporting family when it's an expected condition to branch on.
- `ArrayBlockingQueue` is a real bounded `Queue` — `offer()`'s `false` only shows up once capacity is actually limited.
