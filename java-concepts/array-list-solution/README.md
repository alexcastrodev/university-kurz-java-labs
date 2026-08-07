# The ArrayList Class - Solution

## Overview

This is the official solution for the ArrayList lab. `Playlist` leans on the positional half of the `List` API — `add(index, E)`, `remove(index)` — plus `toArray(T[])` and `ArrayList`'s own `ensureCapacity()`, which is not part of `List` at all.

## Key Concepts

### add(E): appending to the end

```java
public void addTrack(String track) {
    tracks.add(track);
}
```

The single-argument `add` always appends. It is amortized O(1): most calls just write into the next free slot of the backing array, and only occasionally does the list have to grow and copy.

### add(index, E): inserting shifts everything after

```java
public void insertAt(int index, String track) {
    tracks.add(index, track);
}
```

An `ArrayList` is a plain array underneath, so making room at `index` means copying every element from `index` onward one slot to the right. That makes positional insert O(n) — cheap near the end, expensive near the front.

### remove(index): removing shifts everything back

```java
public void removeAt(int index) {
    tracks.remove(index);
}
```

Removal is the mirror image: everything after `index` moves one slot left to close the gap, so it is also O(n). Note the overload trap — `remove(int)` removes by position, while `remove(Object)` removes by value; for an `ArrayList<String>` the two are unambiguous, but for an `ArrayList<Integer>` they are easy to confuse.

### toArray(T[]): getting back the real element type

```java
public String[] toArray() {
    return tracks.toArray(new String[0]);
}
```

The no-argument `toArray()` returns `Object[]`, which cannot be cast to `String[]` — the runtime array type really is `Object[]`. Passing a typed array tells the list what component type to allocate, so you get an actual `String[]` back. `new String[0]` is the idiomatic argument: the list allocates a correctly sized array itself, and the empty prototype costs essentially nothing.

### ensureCapacity(): sizing the backing array up front

```java
public void prepareForBulkLoad(int expectedTotal) {
    tracks.ensureCapacity(expectedTotal);
}
```

Growing an `ArrayList` means allocating a larger array and copying the old contents into it, repeatedly, as it fills. When you already know roughly how many elements are coming, `ensureCapacity()` does that growth once. It is declared on `ArrayList`, not on `List`, which is why the field here is typed as `ArrayList<String>` rather than `List<String>`.

## Summary

- `add(index, E)` and `remove(index)` are O(n): every following element shifts to make or close a gap.
- Appending with `add(E)` is amortized O(1) and should be preferred whenever order allows it.
- `toArray(new String[0])` gives back a genuine `String[]`; the raw `toArray()` only ever yields an `Object[]`.
- `ensureCapacity()` trades one upfront allocation for many reallocations, but it lives on `ArrayList`, so declaring the field as `List` would hide it.
