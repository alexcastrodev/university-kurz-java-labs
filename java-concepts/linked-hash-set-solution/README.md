# The LinkedHashSet Class - Solution

## Overview

This is the official solution for the LinkedHashSet lab. `RecentSearches` combines two guarantees that come free with `LinkedHashSet`: `Set` uniqueness (a query is tracked at most once) and insertion-order iteration (the history reads oldest first). The capacity cap is the part we implement ourselves, evicting the oldest entry through the set's own `Iterator`.

## Key Concepts

### TODO-00: no-op on re-search, evict the oldest on a new entry

```java
public void record(String query) {
    if (searches.contains(query)) {
        return;
    }

    if (searches.size() >= capacity) {
        Iterator<String> oldestFirst = searches.iterator();
        if (oldestFirst.hasNext()) {
            oldestFirst.next();
            oldestFirst.remove();
        }
    }

    searches.add(query);
}
```

`LinkedHashSet` iterates in insertion order, oldest first, so the very first element the iterator yields is always the eviction candidate — `next()` positions the iterator on it and `remove()` deletes it from the backing set. Checking `contains()` *before* the capacity check is what makes a re-search harmless: evicting on every `record()` call regardless of whether the entry is new would punish a repeat search by dropping an unrelated older query. The `hasNext()` guard keeps the code safe even for a degenerate capacity of zero or less, where the set is empty and there is nothing to evict.

This manual `Iterator.remove()` technique is deliberate: `SequencedCollection.removeFirst()` (JDK 21+) does the same job in one call, but the iterator version compiles and behaves identically on any JDK that has `LinkedHashSet` at all.

### TODO-01: exposing the history as a snapshot

```java
public List<String> history() {
    return new ArrayList<>(searches);
}
```

Copying into a new `ArrayList` preserves the set's iteration order — oldest first — and hands the caller an independent list, so nothing outside the class can mutate the tracked history. It also gives the tests a value with `List` equality semantics, where order is part of the comparison.

### TODO-02: membership lookup

```java
public boolean hasSearched(String query) {
    return searches.contains(query);
}
```

`LinkedHashSet` is a `HashSet` with a linked list threaded through its entries, so lookups stay O(1) on the hash table while iteration follows the link order. Ordering costs nothing at query time.

### TODO-03 (optional): the newest entry still present

```java
public String mostRecent() {
    String last = null;
    for (String search : searches) {
        last = search;
    }
    return last;
}
```

Insertion order means the last element the iterator yields is the newest one. Walking the whole set to reach it is O(n), but it needs no extra state and no JDK-21+ `getLast()` — and the `null` initial value naturally covers the empty case.

## Summary

- `LinkedHashSet` gives you `Set` uniqueness plus predictable insertion-order iteration, with `HashSet`-level lookup cost.
- `add()` on an element already present is a no-op — it neither re-inserts nor reorders, which is exactly the "re-searching doesn't bump it" behavior this lab wants.
- Because iteration runs oldest-to-newest, `iterator().next()` followed by `remove()` is the version-independent way to evict the oldest entry.
- Guard the eviction behind a `contains()` check so only genuinely new entries can push something out of the history.
