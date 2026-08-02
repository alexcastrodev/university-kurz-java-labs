# The Set Interface - Solution

## Overview

This is the official solution for the Set Interface lab, covering `NavigableSet`'s closest-match lookups and `Set.of()`'s duplicate handling.

## Key Concepts

### Closest-match lookups

```java
prices.ceiling(cents);  // smallest price >= cents, or null
prices.floor(cents);    // largest price <= cents, or null
prices.higher(cents);   // smallest price > cents, or null
```

Each returns `null` rather than throwing when there's no match — a normal, expected outcome for a price lookup, not an error.

### Set.of() rejects duplicates instead of deduplicating

```java
Set.of(100, 100); // IllegalArgumentException: duplicate element
```

This is different from adding the same value twice to a `HashSet`, which just silently returns `false` from the second `add()`. `Set.of()` treats a duplicate argument as a caller mistake worth failing loudly for.

### Range views with explicit inclusive/exclusive bounds

```java
prices.subSet(minCentsInclusive, true, maxCentsExclusive, false);
```

`NavigableSet.subSet` takes a `boolean` per bound so a half-open range `[min, max)` can be expressed exactly, unlike `SortedSet.subSet`, which is always `[min, max)` with no choice.

## Summary

- `ceiling`/`floor`/`higher`/`lower` find the closest element without an exact match, returning `null` on failure.
- `Set.of()` throws on duplicate arguments rather than silently deduplicating them.
- `NavigableSet.subSet` lets you control each bound's inclusivity explicitly.
