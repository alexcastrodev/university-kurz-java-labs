# Comparators and Collection Algorithms: An Employee Directory - Solution

## Overview

This is the official solution for the Comparators and Collection Algorithms lab. It builds two independent sort orders on the same `Employee` type without touching `Employee` itself, and exposes the internal list both safely (a view) and destructively (a sorted copy is never confused with the original).

## Key Concepts

### Multi-key comparators without a hand-written compare()

```java
copy.sort(Comparator.comparing(Employee::lastName).thenComparing(Employee::firstName));
```

`thenComparing()` only runs its comparator when the first one reports a tie — no manual "if equal, compare the next field" logic needed.

### Sorting a copy, not the source

```java
List<Employee> copy = new ArrayList<>(employees);
copy.sort(...);
return copy;
```

Sorting mutates the list it's called on. Copying first means `sortedByLastThenFirst()` and `sortedBySalaryDescending()` can be called any number of times, in any order, without the directory's own insertion order ever changing.

### unmodifiableList() is a view, not a copy

```java
public List<Employee> unmodifiableView() {
    return Collections.unmodifiableList(employees);
}
```

The returned list can't be mutated directly (attempting `.add()` throws `UnsupportedOperationException`), but it's still backed by `employees` — adding to the directory after the view was obtained is visible through that same view. This is the trade-off the article calls out explicitly: "read-only" describes what you can do *through* the view, not whether the underlying data can change.

### Collections.max()/min() throw on empty collections automatically

```java
public Employee highestPaid() {
    return Collections.max(employees, Comparator.comparingDouble(Employee::salary));
}
```

No manual empty-check is needed — `Collections.max()`/`min()` already throw `NoSuchElementException` when the collection has no elements, which is exactly the behavior an empty directory should have.

## Summary

- `Comparator.comparing().thenComparing()` builds tie-breaking multi-key orderings declaratively.
- Always sort a copy when the original order needs to survive.
- `Collections.unmodifiableList()` prevents mutation through the view, but does not insulate the view from changes to the underlying collection.
