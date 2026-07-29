# Array Equality

## Goal

Learn the difference between comparing arrays by reference vs. by content, and know when to use `Arrays.equals()` vs. `Arrays.deepEquals()`.

## Prerequisites

- Basic Java syntax
- Understanding of arrays
- Familiarity with object references

## Task

Use Java's built-in array comparison methods to compare arrays correctly. You'll explore:

- Why `array1.equals(array2)` doesn't work for comparing content
- When to use `Arrays.equals()` for single-dimensional arrays
- When to use `Arrays.deepEquals()` for multi-dimensional arrays

## Instructions

Complete the following TODOs in `ArrayComparator`:

- TODO-00: Use `Arrays.equals()` to compare single-dimensional arrays
- TODO-01: Use `Arrays.deepEquals()` to compare multi-dimensional arrays

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-minute/array-equality test
```

Or from the lab directory:

```bash
cd java-minute/array-equality
mvn test
```

## Bonus

- BONUS-00: Explore how these methods handle null elements
- BONUS-01: Try comparing arrays using `==` operator and observe the difference