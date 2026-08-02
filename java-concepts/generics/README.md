# Generics: Bounded Types and PECS Wildcards

## Goal

Understand how a bounded type parameter (`<T extends Number & Comparable<T>>`) gives a generic class access to specific methods at compile time, and how PECS (`? extends` for producers, `? super` for consumers) lets a static method accept the widest possible range of argument types.

## Prerequisites

- Basic generics syntax (`<T>`)
- `Comparable`/`compareTo()`
- Basic `List` usage

## Task

Implement `NumberBox<T extends Number & Comparable<T>>`, a small container that sums and finds the max of its items using the bound's guarantees, plus a static `copyAll()` method that copies between lists of different (but compatible) type parameters using wildcard bounds.

## Instructions

Complete the following TODOs in `NumberBox`:

- TODO-00: Add an item to the box.
- TODO-01: Sum every item's numeric value.
- TODO-02: Find the max item, throwing `NoSuchElementException` when the box is empty.
- TODO-03: Copy elements from a `? extends T` source into a `? super T` destination.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/generics test
```

Or from the lab directory:

```bash
cd java-concepts/generics
mvn test
```

## Bonus

- TODO-04 (optional): Implement a generic `isIn(T x, T[] values)` search using `compareTo()`.
