# Iterator vs Iterable

## Goal

Understand why implementing `Iterable` forces you to supply an `Iterator`, and implement one yourself — including the tricky part: removing an element mid-traversal without skipping the next one.

## Prerequisites

- Basic Java syntax
- Familiarity with `List` and `ArrayList`
- Basic understanding of interfaces

## Task

`NameRoster` implements `Iterable<String>` over an internal `List<String>`. You'll build its private `RosterIterator` inner class from scratch: `hasNext()`, `next()`, and — the interesting part — `remove()`, which must delete the last element `next()` returned and rewind the cursor so nothing gets skipped.

## Instructions

Complete the following TODOs in `NameRoster`:

- TODO-00: Return a new `RosterIterator` from `iterator()`.
- TODO-01: Implement `hasNext()`.
- TODO-02: Implement `next()`, throwing `NoSuchElementException` when there's nothing left.
- TODO-03: Implement `remove()`, deleting the last returned name and adjusting the cursor.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/iterator-vs-iterable test
```

Or from the lab directory:

```bash
cd java-concepts/iterator-vs-iterable
mvn test
```

## Bonus

- BONUS-00: Make `remove()` throw `IllegalStateException` if it's called before `next()`, or twice in a row without a `next()` call in between — this is what the real `Iterator` contract requires.
