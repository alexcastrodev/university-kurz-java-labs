# The TreeSet Class - Solution

## Overview

This is the official solution for the TreeSet lab, showing how always-sorted storage and the `NavigableSet` closest-match lookups replace hand-rolled sorting and scanning.

## Key Concepts

### record(): Set semantics make duplicates a silent no-op

```java
public void record(int score) {
    scores.add(score);
}
```

`add()` returns `false` when the score was already present, but it never throws — a `Set` models "which values exist", not "how many times each one was seen". Recording `50` twice leaves the board in exactly the state one `record(50)` would produce, which is why `closestAtLeast(50)` still answers `50` and `closestAtLeast(51)` moves on to the next distinct score.

### last(): the highest score, without ever sorting

```java
public int highest() {
    return scores.last();
}
```

A `TreeSet` keeps its elements ordered by their natural ordering as they're inserted, so `last()` is a walk to the rightmost node, not a sort. Insertion order is irrelevant: recording `50, 90, 10, 70` and recording `10, 30, 50, 90` produce the same iteration order and the same `last()`.

### first(): the lowest score, and the natural empty-set failure

```java
public int lowest() {
    return scores.first();
}
```

`first()` and `last()` throw `NoSuchElementException` on an empty set. Letting that exception propagate is deliberate — asking an empty board for its lowest score is a caller bug, and wrapping it in a custom exception would only hide where the mistake actually is.

### ceiling(): the smallest element at or above a target

```java
public Integer closestAtLeast(int target) {
    return scores.ceiling(target);
}
```

`ceiling(40)` returns `50` on a board of `10, 30, 50, 70`, and `ceiling(70)` returns `70` because the bound is inclusive. When nothing qualifies (`ceiling(100)`), it returns `null` rather than throwing: these are "closest match, if any" queries, not assertions that a match must exist.

### floor(): the largest element at or below a target

```java
public Integer closestAtMost(int target) {
    return scores.floor(target);
}
```

`floor` is `ceiling`'s mirror image — largest element `<= target`, inclusive at the bound, `null` when the target sits below everything recorded. Note the boxed `Integer` return type: it's what lets "no match" be expressed at all. (`higher`/`lower` are the strict versions, excluding an exact match.)

### subSet(): explicit inclusive and exclusive bounds

```java
public SortedSet<Integer> between(int low, int high) {
    return scores.subSet(low, true, high, true);
}
```

The two-argument `subSet(low, high)` is inclusive at the low end and exclusive at the high end, so it would silently drop a score equal to `high`. The four-argument form makes each bound's inclusivity explicit, which is how `between(30, 70)` keeps both `30` and `70`. The result is a live view backed by the original set, not a copy.

## Summary

- `TreeSet` keeps elements sorted on insertion, so `first()`/`last()`/iteration never depend on the order values arrived in.
- `Set` semantics mean re-recording an existing value is a no-op, not an error.
- `ceiling`/`floor` answer "nearest match at or above/below" and return `null` when nothing qualifies; `first`/`last` throw `NoSuchElementException` when the set is empty.
- `subSet(low, true, high, true)` states both bounds' inclusivity explicitly, unlike the two-argument form's inclusive-low/exclusive-high default.
