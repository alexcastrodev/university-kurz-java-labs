# Pattern Matching - Solution

## Overview

This is the official solution for the Pattern Matching lab. It shows sealed-type exhaustiveness, null handling, and guarded patterns working together in real code.

## Key Concepts

### Sealed types make switch exhaustive without default

```java
sealed interface Shape permits Circle, Rectangle, Triangle {}

static double area(Shape shape) {
    return switch (shape) {
        case Circle c -> Math.PI * c.radius() * c.radius();
        case Rectangle r -> r.width() * r.height();
        case Triangle t -> 0.5 * t.base() * t.height();
    };
}
```

Because `Shape` permits exactly these three types, the compiler proves the switch covers every case — no `default` needed, and no `default` allowed to silently swallow a case you forgot.

### case null as its own branch

```java
case null -> "nothing to describe";
```

Without this, a `null` selector would throw `NullPointerException` before the switch even runs its cases.

### Record patterns deconstruct in the case label itself

```java
case Circle(double radius) -> "circle with radius " + radius;
```

No manual cast, no calling `.radius()` separately — the component is bound directly as a local variable.

### Guarded patterns add a condition without nesting

```java
case String s when s.length() == 1 -> "short string: " + s;
case String s -> "string: " + s;
```

The guard only applies to its own label. Because guards don't participate in dominance checking, the shorter, more specific `String` case can sit before the general one.

## Summary

- Sealed hierarchies let `switch` skip `default` and still be checked at compile time.
- `case null` replaces a defensive null-check before the switch.
- Record patterns bind components directly; guarded patterns add conditions per case.
