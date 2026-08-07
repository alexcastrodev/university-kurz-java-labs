# java.lang Essentials: Comparable, AutoCloseable, StackWalker, ProcessBuilder

## Goal

Give a type a natural ordering with `Comparable`, and make a resource type work with try-with-resources by implementing `AutoCloseable`.

## Prerequisites

- Basic Java syntax
- Familiarity with generics

## Task

The `java.lang` essentials concept covers `Comparable`, `AutoCloseable`, `StackWalker`, and `ProcessBuilder`. This lab focuses on `Comparable` and `AutoCloseable` — the two most directly testable pieces of the concept.

`Money` holds a currency and an amount in cents, and declares itself `Comparable<Money>` — you'll implement the comparison so a `Money` knows how it ranks against another. `PooledConnection` declares itself `AutoCloseable` — you'll implement the closed flag and the `close()` method so it behaves correctly inside try-with-resources, including when `close()` is called more than once. `ConnectionUtils` puts both to work: sorting a list of `Money` by its natural ordering, and (as a bonus) running an action against a connection that is always closed afterwards.

## Instructions

Complete the following TODOs:

- TODO-00: Implement `Money.compareTo()`, comparing by `amountCents`.
- TODO-01: Implement `PooledConnection.isClosed()`.
- TODO-02: Implement `PooledConnection.close()`, making repeated closes safe.
- TODO-03: Implement `ConnectionUtils.sortedByAmount()`, returning a new sorted list without mutating the input.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/java-lang-essential-utility-types test
```

Or from the lab directory:

```bash
cd java-concepts/java-lang-essential-utility-types
mvn test
```

## Bonus (Optional)

- TODO-04 (optional): Implement `ConnectionUtils.openAndUse()` with try-with-resources, so the connection is closed even when the action throws.
