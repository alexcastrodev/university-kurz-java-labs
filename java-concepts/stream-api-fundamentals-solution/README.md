# Stream API Fundamentals - Solution

## Overview

This is the official solution for the Stream API Fundamentals lab. Each method is a single pipeline: a source (`sales.stream()`), zero or more intermediate operations that describe a transformation, and one terminal operation that produces the answer. Nothing runs until the terminal operation asks for it.

## Key Concepts

### TODO-00 — Primitive streams avoid boxing

```java
public double totalAmount(List<Sale> sales) {
    return sales.stream()
            .mapToDouble(Sale::amount)
            .sum();
}
```

`mapToDouble` leaves the object world and produces a `DoubleStream`, which carries raw `double` values. The alternative, `map(Sale::amount).reduce(0.0, Double::sum)`, boxes every amount into a `Double` object and unboxes it again to add — allocation on every element for no benefit. The primitive stream also hands you ready-made reductions such as `sum()`, `average()`, and `summaryStatistics()` that the object stream simply doesn't have.

### TODO-01 — Chaining intermediate operations

```java
public List<String> productNamesSorted(List<Sale> sales) {
    return sales.stream()
            .map(Sale::product)
            .distinct()
            .sorted()
            .toList();
}
```

`map` changes the element type (`Sale` to `String`), `distinct` drops duplicates using `equals`/`hashCode`, and `sorted` uses the natural ordering of `String`. Order matters for cost: deduplicating before sorting means fewer elements to sort. `toList()` (Java 16+) collects into an unmodifiable list and is the shorter equivalent of `collect(Collectors.toList())`.

### TODO-02 — max() returns an Optional on purpose

```java
public Sale topSaleByAmount(List<Sale> sales) {
    return sales.stream()
            .max(Comparator.comparingDouble(Sale::amount))
            .orElseThrow(() -> new NoSuchElementException("no sales"));
}
```

There is no "largest" element in an empty stream, so `max` returns `Optional<Sale>` rather than `null`. `Comparator.comparingDouble` builds the comparator from a key extractor without boxing the key. `orElseThrow` with a supplier lets you choose the failure exactly — here a `NoSuchElementException` with a message, instead of the bare one the no-arg `orElseThrow()` would throw.

### TODO-03 — groupingBy with a downstream collector

```java
public Map<String, Double> totalsByProduct(List<Sale> sales) {
    return sales.stream()
            .collect(Collectors.groupingBy(Sale::product, Collectors.summingDouble(Sale::amount)));
}
```

`groupingBy(classifier)` alone produces `Map<String, List<Sale>>` — the elements of each group, unreduced. The two-argument form adds a *downstream* collector that is applied to each group instead of collecting it into a list, so `summingDouble` turns every group directly into its total and the result is `Map<String, Double>`. Downstream collectors compose freely: swap in `counting()`, `averagingDouble(...)`, or `mapping(Sale::product, toList())` and the grouping code stays the same.

### TODO-04 (optional) — average() and OptionalDouble

```java
public double averageAmount(List<Sale> sales) {
    return sales.stream()
            .mapToDouble(Sale::amount)
            .average()
            .orElse(0.0);
}
```

`sum()` can return `0.0` for an empty stream because that's the identity of addition, but an average of nothing is undefined — so `average()` returns an `OptionalDouble`. `orElse(0.0)` is where this method decides that "no sales" means zero, in contrast to `topSaleByAmount`, which treats the same input as an error.

## Summary

- A pipeline is source, intermediate operations, one terminal operation; nothing is computed until the terminal operation runs.
- Use `mapToDouble`/`mapToInt` when reducing numbers — you skip boxing and unlock `sum`, `average`, and `summaryStatistics`.
- Reductions that can have no answer (`max`, `min`, `average`, `findFirst`) return an `Optional`, forcing the empty case to be handled explicitly rather than surfacing as `null`.
- `Collectors.groupingBy` takes a downstream collector, which is what turns a map of lists into a map of aggregates in one pass.
