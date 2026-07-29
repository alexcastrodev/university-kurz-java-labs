# Array Equality - Solution

## Overview

This is the official solution for the Array Equality lab. It demonstrates how to properly compare arrays in Java using the standard library APIs.

## Key Concepts

### Reference vs. Content Comparison

Arrays in Java inherit `Object.equals()`, which compares object references, not content:

```java
int[] a1 = {1, 2, 3};
int[] a2 = {1, 2, 3};
a1.equals(a2);  // false — different object references
```

### Arrays.equals()

The `Arrays.equals()` method compares single-dimensional arrays by content:

```java
Arrays.equals(a1, a2);  // true — same content
```

### Arrays.deepEquals()

For multi-dimensional arrays, use `Arrays.deepEquals()` to recursively compare nested arrays:

```java
int[][] m1 = {{1, 2}, {3, 4}};
int[][] m2 = {{1, 2}, {3, 4}};
Arrays.deepEquals(m1, m2);  // true
```

## Implementation Details

Both methods handle `null` elements correctly:
- Two `null` values are considered equal
- A `null` array compared to any non-null array returns `false`
- Both `null` arrays return `true`

## Summary

- Use `Arrays.equals()` for single-dimensional arrays
- Use `Arrays.deepEquals()` for multi-dimensional arrays
- Always handle null cases explicitly in your comparisons
