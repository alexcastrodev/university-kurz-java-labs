# The Set Interface

## Goal

Use `NavigableSet`'s closest-match methods to find nearby price points, and see how `Set.of()` handles duplicates differently from a normal `Set.add()`.

## Prerequisites

- Basic Java syntax
- Familiarity with `Set`/`TreeSet`

## Task

`PriceBook` stores price points (in cents) in a `NavigableSet<Integer>`. You'll implement three closest-match lookups (`ceiling`, `floor`, `higher`) and a factory method that builds an unmodifiable set — one that rejects duplicate input outright instead of silently deduplicating it.

## Instructions

Complete the following TODOs in `PriceBook`:

- TODO-00: Implement `cheapestAtLeast()` using `ceiling`.
- TODO-01: Implement `mostExpensiveAtMost()` using `floor`.
- TODO-02: Implement `nextPriceAbove()` using `higher`.
- TODO-03: Implement `uniquePrices()` using `Set.of()`.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/set-interface test
```

Or from the lab directory:

```bash
cd java-concepts/set-interface
mvn test
```

## Bonus

- BONUS-00: Implement `discountRange()`, returning a `NavigableSet` view of prices in `[minCentsInclusive, maxCentsExclusive)` using `subSet` with explicit inclusive/exclusive bounds.
