# The TreeSet Class

## Goal

Use `TreeSet`'s always-sorted storage and its `NavigableSet` closest-match lookups (`ceiling`/`floor`) directly, instead of sorting a list after the fact every time you need order.

## Prerequisites

- Basic Java syntax
- Familiarity with the `Set` interface

## Task

`ScoreBoard` keeps every score a game has recorded in a `TreeSet<Integer>`. Because a `TreeSet` stores its elements in sorted order at all times, the board never has to sort anything: you'll implement recording a score, reading the highest and lowest ones, and answering "what's the nearest score at or above / at or below this number?" — questions a plain `Set` can't answer.

## Instructions

Complete the following TODOs in `ScoreBoard`:

- TODO-00: Implement `record()` so a score is stored, and re-recording an existing score changes nothing.
- TODO-01: Implement `highest()`, letting it throw naturally when nothing has been recorded.
- TODO-02: Implement `lowest()`, letting it throw naturally when nothing has been recorded.
- TODO-03: Implement `closestAtLeast()`, returning the smallest recorded score `>= target`, or `null`.
- TODO-04: Implement `closestAtMost()`, returning the largest recorded score `<= target`, or `null`.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/tree-set test
```

Or from the lab directory:

```bash
cd java-concepts/tree-set
mvn test
```

## Bonus (Optional)

- TODO-05 (optional): Implement `between()`, returning every recorded score in the inclusive range `[low, high]` as a `SortedSet<Integer>`.
