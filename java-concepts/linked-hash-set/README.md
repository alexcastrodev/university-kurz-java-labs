# The LinkedHashSet Class

## Goal

Use `LinkedHashSet` to track a capped, deduplicated, insertion-ordered history — and evict the oldest entry manually through its `Iterator`, the classic technique that works regardless of the JDK version you're on.

## Prerequisites

- Basic Java syntax
- The `Set` interface
- `Iterator`

## Task

`RecentSearches` keeps the queries a user typed into a search box, capped at a fixed capacity. Every entry appears at most once, and the history reads oldest first.

Two rules drive the behavior:

- **Re-searching an already tracked query must NOT move it.** `Set.add()` on an element that's already in the set is a no-op — it does not re-insert the element and it does not reorder it. A query keeps the position it had when it was first recorded.
- **A genuinely new query at full capacity evicts the oldest entry.** `LinkedHashSet`'s iterator walks the set from oldest to newest, so the first element it yields is always the eviction candidate. Advance the iterator once and call `Iterator.remove()`.

## Instructions

Complete the following TODOs in `RecentSearches`:

- TODO-00: Implement `record()` — no-op for an already present query, evict the oldest entry when a new one exceeds capacity.
- TODO-01: Implement `history()`, returning the searches in insertion order as a new `List`.
- TODO-02: Implement `hasSearched()`, reporting whether a query is currently tracked.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/linked-hash-set test
```

Or from the lab directory:

```bash
cd java-concepts/linked-hash-set
mvn test
```

## Bonus (Optional)

- TODO-03 (optional): Implement `mostRecent()`, returning the most recently added search still present, or `null` when nothing has been recorded yet.
