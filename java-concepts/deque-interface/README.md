# Deque as a Bounded, Undoable Command History

## Goal

Understand how `Deque`'s two families of methods — the throwing ones (`addFirst`/`addLast`, `removeFirst`/`removeLast`) and the reporting ones (`offerFirst`/`offerLast`, `pollFirst`/`pollLast`) — let you build a structure that evicts silently instead of crashing, and how `descendingIterator()` gives you the reverse of a deque's natural order for free.

## Prerequisites

- Basic Java syntax
- Familiarity with `Queue`
- Understanding of iterators

## Task

Implement `CommandHistory`, a bounded command log backed by an `ArrayDeque<String>`. New commands are recorded at the tail; once the history is full, the oldest command is evicted automatically instead of throwing. `undoLast()` must be safe to call on an empty history — it should never throw, only return `null`. You'll use `addLast`/`pollFirst`/`pollLast`/`peekLast` and `descendingIterator()` to implement it.

## Instructions

Complete the following TODOs in `CommandHistory`:

- TODO-00: Record a command, evicting the oldest one if the history is over capacity.
- TODO-01: Undo the most recent command without throwing on an empty history.
- TODO-02: Peek at the most recent command without removing it.
- TODO-03: List every command, most recent first.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/deque-interface test
```

Or from the lab directory:

```bash
cd java-concepts/deque-interface
mvn test
```

## Bonus

- TODO-04 (optional): List every command, oldest first.
