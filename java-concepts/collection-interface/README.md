# The Collection Interface

## Goal

Learn the difference between `retainAll` (intersection) and `removeAll` (difference) by implementing both — and see why the two are so easy to swap by mistake.

## Prerequisites

- Basic Java syntax
- Familiarity with `ArrayList`

## Task

Implement four static utility methods on `CollectionOps`, each backed by a specific `Collection` bulk operation: `intersection`/`difference` (using `retainAll`/`removeAll` without mutating the inputs), `removeBlank` (using `removeIf`), and `toStringArray` (using the generator-based `toArray` overload).

## Instructions

Complete the following TODOs in `CollectionOps`:

- TODO-00: Implement `intersection()` using `retainAll`.
- TODO-01: Implement `difference()` using `removeAll`.
- TODO-02: Implement `removeBlank()` using `removeIf`.
- TODO-03: Implement `toStringArray()` using the `IntFunction<T[]>` `toArray` overload.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/collection-interface test
```

Or from the lab directory:

```bash
cd java-concepts/collection-interface
mvn test
```

## Bonus

- BONUS-00: Add an `overlaps(Collection<String> a, Collection<String> b)` method that returns `true` if the two collections share at least one element, without building a full intersection collection.
