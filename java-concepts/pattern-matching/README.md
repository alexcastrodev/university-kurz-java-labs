# Pattern Matching

## Goal

Combine sealed-type switch exhaustiveness, an explicit `null` case, and a guarded (`when`) pattern in a single method — the features the article covers individually, working together.

## Prerequisites

- Basic `instanceof` and `switch`
- Records
- Sealed interfaces

## Task

`Shape` is a `sealed interface` permitting exactly `Circle`, `Rectangle`, and `Triangle`. You'll write `area(Shape)` as an exhaustive pattern-matching `switch` with no `default`, then write `describe(Object)`, which must handle `null`, a `Circle` via a record pattern, a `String` via a guarded pattern, and everything else via a fallback.

## Instructions

Complete the following TODOs in `ShapeCalculator`:

- TODO-00: Implement `area()` as an exhaustive switch over the sealed `Shape` hierarchy.
- TODO-01: Handle `null` in `describe()`.
- TODO-02: Handle `Circle` in `describe()` using a record pattern.
- TODO-03: Handle `String` in `describe()` using a guarded (`when`) pattern.
- TODO-04: Add the fallback branch in `describe()`.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/pattern-matching test
```

Or from the lab directory:

```bash
cd java-concepts/pattern-matching
mvn test
```

## Bonus

- BONUS-00: Add `Rectangle` and `Triangle` cases to `describe()`, using record patterns to include their dimensions in the output (e.g. `"rectangle 3.0x4.0"`).
