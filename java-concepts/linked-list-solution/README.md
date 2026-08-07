# The LinkedList Class - Solution

## Overview

This is the official solution for the LinkedList lab, showing `LinkedList` used as a `Deque` and a `List` at the same time — front/back operations for the playback semantics, positional insertion for the bonus.

## Key Concepts

### addLast(): waiting your turn

```java
public void enqueue(String track) {
    queue.addLast(track);
}
```

`LinkedList` keeps a reference to its last node, so appending is a matter of linking one new node — O(1), no matter how many tracks are already queued. This is the plain "get in line" case: the track plays after everything currently ahead of it.

### addFirst(): jumping the line

```java
public void playNext(String track) {
    queue.addFirst(track);
}
```

Because the list is doubly linked with a head reference, prepending is just as cheap as appending — also O(1). An `ArrayList` can express the same intent with `add(0, track)`, but it has to shift every existing element one slot to the right, which costs O(n) on each call. That asymmetry is the reason to reach for `LinkedList` when both ends are hot.

### getFirst() and removeFirst(): throwing on an empty queue

```java
public String nowPlaying() {
    return queue.getFirst();
}

public String skip() {
    return queue.removeFirst();
}
```

Both throw `NoSuchElementException` when the queue is empty, and that is left untouched on purpose. `LinkedList` also offers the reporting variants `peekFirst()` and `pollFirst()`, which return `null` instead — but here an empty "now playing" means the caller asked for a track when nothing was queued, which is a bug, not a state to branch on. Letting the natural exception escape keeps that contract honest instead of hiding it behind a `null`.

### add(index, element): positional insertion without an array

```java
public void insertAt(int index, String track) {
    queue.add(index, track);
}
```

`LinkedList` implements `List`, so index-based insertion works even though there is no backing array to shift. It walks the chain to reach `index` — starting from whichever end is closer, so an index near either edge is found quickly — and then relinks two pointers. The insertion itself is O(1); only the walk to the position costs O(n).

## Summary

- `LinkedList` is the one collection that is both a `List` and a `Deque`, so positional and end-based APIs are available on the same object.
- `addFirst`/`addLast` are O(1) at either end; `ArrayList`'s `add(0, ...)` shifts every element and is O(n).
- The `getFirst`/`removeFirst` family throws on empty, while `peekFirst`/`pollFirst` returns `null` — pick based on whether an empty queue is a bug or an expected case.
- Indexed access on a `LinkedList` works but has to traverse; it is a convenience, not the reason to choose the class.
