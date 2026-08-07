# The PriorityQueue Class - Solution

## Overview

This is the official solution for the PriorityQueue lab, showing how a `Comparator` — not insertion order — decides what comes out next, and why only repeated `poll()` reads the queue in that order.

## Key Concepts

### The Comparator defines the order

```java
private final PriorityQueue<Task> queue = new PriorityQueue<>(Comparator.comparingInt(Task::priority));

public void submit(String name, int priority) {
    queue.offer(new Task(name, priority));
}
```

`Comparator.comparingInt(Task::priority)` orders tasks by their priority number ascending, so the smallest number sits at the head — a min-heap by priority. Submission order is irrelevant: a task submitted last with priority `1` jumps ahead of one submitted first with priority `5`. Without a comparator, `PriorityQueue` would fall back to natural ordering and `Task` would have to implement `Comparable`.

### poll() reports an empty queue with null

```java
public Task takeNext() {
    return queue.poll();
}

public Task peekNext() {
    return queue.peek();
}
```

`poll()` removes and returns the head; `peek()` returns it without removing. Both belong to `Queue`'s *reporting* family, so they answer `null` on an empty queue instead of throwing — which is exactly what these two methods expose to callers. (The throwing counterparts, `remove()` and `element()`, are covered in the Queue Interface lab.)

### Iterating a PriorityQueue does NOT give priority order

```java
public List<Task> drainInPriorityOrder() {
    List<Task> result = new ArrayList<>();
    Task task;
    while ((task = queue.poll()) != null) {
        result.add(task);
    }
    return result;
}
```

`PriorityQueue` is a binary heap stored in an array, and its iterator walks that array's internal layout — not sorted order. The heap only guarantees that each element is smaller than its children, so after submitting priorities `9, 2, 5, 1, 7` the backing array may read `1, 2, 5, 9, 7`: the head is correct, the tail is not. A for-each loop, `new ArrayList<>(queue)`, `queue.stream()`, and `toArray()` all inherit that layout and will happily hand you `7` before `9`.

Polling in a loop is the guaranteed way: every `poll()` removes the current minimum and re-heapifies, so the queue's head is correct again for the next call. The loop condition doubles as the emptiness check, since `poll()` returns `null` once nothing remains — and the scheduler is empty when the method returns.

## Summary

- A `PriorityQueue`'s `Comparator` decides what comes out next; insertion order is not preserved.
- `Comparator.comparingInt(Task::priority)` makes the lowest priority number the head — a min-heap.
- `poll()` and `peek()` return `null` on an empty queue rather than throwing.
- Iterating (for-each, `stream()`, `new ArrayList<>(queue)`, `toArray()`) exposes the raw heap array and does *not* yield priority order — only repeated `poll()` does.
- Ties are allowed but their relative order is unspecified: `comparingInt` reports equal elements as equal and the heap makes no stability promise.
