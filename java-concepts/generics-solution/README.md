# Generics: Bounded Types and PECS Wildcards - Solution

## Overview

This is the official solution for the Generics lab. It demonstrates a bounded generic class and a wildcard-based static method side by side.

## Key Concepts

### Bounded type parameters

```java
public class NumberBox<T extends Number & Comparable<T>> {
```

Combining a class bound (`Number`) with an interface bound (`Comparable<T>`) via `&` gives every `T` inside the class both `doubleValue()` and `compareTo()` — without either bound, neither method would be callable on a plain `T`.

### PECS: Producer Extends, Consumer Super

```java
public static <T> void copyAll(List<? extends T> source, List<? super T> destination) {
    for (T item : source) {
        destination.add(item);
    }
}
```

`source` is only ever read from — it's a producer, so `? extends T` lets it be a `List<Integer>`, `List<Double>`, or any other `List` of a `T` subtype. `destination` is only ever written to — it's a consumer, so `? super T` lets it be a `List<Number>` or `List<Object>`, anything that can legally hold a `T`.

### Erasure, briefly

At runtime there is exactly one `NumberBox` class file regardless of which `T` was used to create an instance — the compiler erases `T` to its bound (`Number`) and inserts the casts needed to make `max()`/`sum()` behave correctly. This is why the bound has to be declared up front: erasure means the runtime has no other way to know `T` supports `doubleValue()`/`compareTo()`.

## Summary

- A bound (`extends`) trades flexibility for compiler guarantees — `NumberBox<String>` simply doesn't compile.
- PECS lets a single method accept the widest reasonable range of related types without needing overloads.
- Wildcards don't loosen how a type can be constructed — only how broadly a method can accept it as a parameter.
