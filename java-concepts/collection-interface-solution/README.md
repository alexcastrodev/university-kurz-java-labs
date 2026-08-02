# The Collection Interface - Solution

## Overview

This is the official solution for the Collection Interface lab. It demonstrates the bulk operations declared by `Collection` and how easy it is to confuse two of them.

## Key Concepts

### retainAll = intersection, removeAll = difference

```java
Collection<String> result = new ArrayList<>(a);
result.retainAll(b); // keep only what's ALSO in b — intersection

Collection<String> result2 = new ArrayList<>(a);
result2.removeAll(b); // remove everything found in b — difference
```

They read almost identically but produce opposite results. Always copying `a` first (rather than mutating a caller's collection in place) also keeps these methods side-effect-free.

### removeIf() with a Predicate

```java
result.removeIf(String::isBlank);
```

`removeIf` is a default method on `Collection` that takes a `Predicate<E>` — no manual iteration needed.

### toArray(IntFunction<T[]>) returns the right type

```java
String[] array = source.toArray(String[]::new);
```

Unlike plain `toArray()` (always `Object[]`) or the raw `toArray(T[])` overload (which accepts a mismatched array type at compile time), the generator form ties the array's component type to the method reference itself.

## Summary

- `retainAll` keeps the intersection; `removeAll` keeps the difference — memorize by which one is a *subtraction*.
- Always copy before mutating in a "pure" utility method.
- `toArray(String[]::new)` is the safest way to get a correctly-typed array.
