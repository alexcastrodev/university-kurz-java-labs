# Comparators and Collection Algorithms: An Employee Directory

## Goal

Build multi-key sorting with `Comparator.comparing()`/`thenComparing()`, and learn the difference between a defensive copy and a live view returned by `Collections.unmodifiableList()`.

## Prerequisites

- Basic Java syntax
- Lambdas and method references
- Basic `List` usage

## Task

Implement `EmployeeDirectory`, backed by an internal `List<Employee>`. You'll build comparators for two different sort orders, expose a read-only view of the internal list, and use `Collections`' static algorithms to find the highest- and lowest-paid employee.

## Instructions

Complete the following TODOs in `EmployeeDirectory`:

- TODO-00: Sort by last name, then first name on ties, without mutating the internal list.
- TODO-01: Sort by salary, highest first, without mutating the internal list.
- TODO-02: Return a live, read-only view of the internal list.
- TODO-03: Find the highest-paid employee using a `Collections` static algorithm.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/comparators-and-collection-algorithms test
```

Or from the lab directory:

```bash
cd java-concepts/comparators-and-collection-algorithms
mvn test
```

## Bonus

- TODO-04 (optional): Find the lowest-paid employee.
