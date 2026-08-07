# The HashSet Class - Solution

## Overview

This is the official solution for the HashSet lab. It shows how the bulk operations `retainAll`, `removeAll` and `addAll` turn a plain `HashSet` into a small set-algebra toolkit — and why each one is applied to a copy rather than to the index's own backing set.

## Key Concepts

### add() already tells you whether the tag was new

```java
public boolean addTag(String tag) {
    return tags.add(tag.toLowerCase());
}
```

`Set.add()` returns `true` only when the element was not already present, so "was this tag new?" is answered by the insertion itself. Calling `contains()` first and then `add()` would hash the same value twice and open a window where the two answers disagree. Lowercasing before storing is what makes the whole index case-insensitive: `"Java"` and `"java"` collapse to the same key.

### contains() must normalize the same way add() does

```java
public boolean hasTag(String tag) {
    return tags.contains(tag.toLowerCase());
}
```

`HashSet` lookups are driven by `hashCode()`/`equals()`, and `"Java"` and `"java"` are different strings by both. Membership only stays case-insensitive if every entry point normalizes identically — normalize on write but not on read and `hasTag("java")` starts missing tags that are really there.

### retainAll() on a copy: the intersection

```java
public Set<String> commonTagsWith(TagIndex other) {
    Set<String> result = new HashSet<>(this.tags);
    result.retainAll(other.tags);
    return result;
}
```

`retainAll()` drops every element that is *not* in the argument — that is exactly an intersection, but it performs it in place. Calling `this.tags.retainAll(other.tags)` would permanently delete tags from the index just because someone asked a question about it. Copying into a new `HashSet` first keeps the operation a pure query: both indexes come out untouched, and the caller owns the returned set.

### removeAll() on a copy: the difference

```java
public Set<String> tagsOnlyInThis(TagIndex other) {
    Set<String> result = new HashSet<>(this.tags);
    result.removeAll(other.tags);
    return result;
}
```

`removeAll()` is the mirror image: it drops every element that *is* in the argument. The difference is not symmetric — `a.tagsOnlyInThis(b)` and `b.tagsOnlyInThis(a)` are different sets — which is precisely why the copy matters. The receiver decides which side of the subtraction it is on, and it should not pay for that with its own data.

### addAll() on a copy: the union

```java
public Set<String> allTagsCombined(TagIndex other) {
    Set<String> result = new HashSet<>(this.tags);
    result.addAll(other.tags);
    return result;
}
```

`addAll()` merges the other set in, and `HashSet` silently swallows the duplicates — no pre-filtering needed. The same defensive-copy rule applies: without it, asking for the union would quietly grow this index by everything the other one knows about.

## Summary

- `Set.add()` returning a `boolean` makes "was this new?" a single hash lookup instead of a `contains()` + `add()` pair.
- Case-insensitivity is a normalization contract: apply the same `toLowerCase()` on both writes and reads, or lookups drift out of sync with storage.
- `retainAll`/`removeAll`/`addAll` are intersection, difference and union — but all three mutate the receiver, so run them on a `new HashSet<>(...)` copy whenever the call is meant to be a question, not a change.
- Returning a fresh set also hands the caller something safe to modify, keeping the index's internal state fully encapsulated.
