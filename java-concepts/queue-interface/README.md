# The Queue Interface

## Goal

Learn `Queue`'s two parallel method families — the ones that throw on failure and the ones that report it — by using each where it fits.

## Prerequisites

- Basic Java syntax
- Familiarity with `Collection`

## Task

`TicketCounter` wraps a bounded `Queue<String>` (a fixed-capacity customer line). You'll implement enqueueing that reports failure instead of throwing, a peek that returns `null` on empty, and two ways to serve the next customer — one that throws on an empty line, one that returns `null`.

## Instructions

Complete the following TODOs in `TicketCounter`:

- TODO-00: Implement `tryEnqueue()` using `offer()`.
- TODO-01: Implement `nextInLine()` using `peek()`.
- TODO-02: Implement `serveNext()` using `remove()`.
- TODO-03: Implement `serveNextOrNull()` using `poll()`.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/queue-interface test
```

Or from the lab directory:

```bash
cd java-concepts/queue-interface
mvn test
```

## Bonus

- BONUS-00: Implement `drainAll()`, returning a `List<String>` of every remaining customer (in order), removing them all from the line by repeatedly polling until it's empty.
