# The List Interface

## Goal

Implement positional `List` operations — indexed insert, search, an in-place sort, and a `subList` that is a live view, not a copy.

## Prerequisites

- Basic Java syntax
- Familiarity with `List`/`ArrayList`

## Task

`PlaylistManager` wraps a `List<String>` of song titles. You'll implement indexed insertion, `indexOf`-based search, an in-place case-insensitive sort, and — the interesting part — a method that returns a *view* over a range of the playlist, where mutating the view mutates the playlist itself.

## Instructions

Complete the following TODOs in `PlaylistManager`:

- TODO-00: Implement `insertAt()` using `List.add(int, E)`.
- TODO-01: Implement `firstIndexOf()` using `indexOf`.
- TODO-02: Implement `nextUp()` returning a `subList` view (not a copy).
- TODO-03: Implement `sortAlphabetically()` sorting in place, case-insensitively.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/list-interface test
```

Or from the lab directory:

```bash
cd java-concepts/list-interface
mvn test
```

## Bonus

- BONUS-00: Implement `shout()`, uppercasing every song title in place using `replaceAll`.
