# Lambda Expressions - Solution

## Overview

This is the official solution for the Lambda Expressions lab. It shows behavior being passed as data through `Predicate` and `Function`, and small pieces of behavior being composed with `and()` and `andThen()` instead of being hand-written as one big expression.

## Key Concepts

### Accepting a Predicate instead of hardcoding the condition

```java
public List<String> filter(List<String> lines, Predicate<String> predicate) {
    return lines.stream().filter(predicate).toList();
}
```

`filter()` never knows which lines matter — the caller supplies that decision as a `Predicate<String>`. Because `Stream.filter` already takes a `Predicate`, the parameter can be handed straight through with no wrapping lambda. `toList()` returns an unmodifiable list and preserves encounter order.

### Accepting a Function instead of hardcoding the rewrite

```java
public List<String> transform(List<String> lines, Function<String, String> transformer) {
    return lines.stream().map(transformer).toList();
}
```

Same idea for transformation: `map` applies the caller's `Function` to every element, in order. A caller passes `String::toUpperCase`, `String::strip`, or any lambda they like, and the method body never changes.

### Composing predicates with and()

```java
public Predicate<String> containsAndLongerThan(String needle, int minLength) {
    Predicate<String> containsNeedle = line -> line.contains(needle);
    Predicate<String> longEnough = line -> line.length() >= minLength;

    return containsNeedle.and(longEnough);
}
```

Writing `line -> line.contains(needle) && line.length() >= minLength` works, but it fuses two independent rules into one expression that can only be used as a whole. Naming each rule and joining them with `and()` keeps them reusable and separately testable, and the composed predicate reads as the sentence it represents. `and()` is a `default` method on `Predicate` and short-circuits exactly like `&&`.

### Method references for simple delegations

```java
public long countBlankLines(List<String> lines) {
    return lines.stream().filter(String::isBlank).count();
}
```

`String::isBlank` and `line -> line.isBlank()` compile to the same behavior, but the method reference drops the ceremony of naming a parameter only to immediately call one method on it. Prefer a method reference whenever the lambda body is a bare delegation; keep an explicit lambda when there is any real logic. Note `isBlank()` also treats whitespace-only lines as blank, unlike `isEmpty()`.

### Composing functions with andThen()

```java
public Function<String, String> trimThenUpper() {
    Function<String, String> trim = String::trim;

    return trim.andThen(String::toUpperCase);
}
```

`andThen()` chains functions left to right: trim first, then uppercase the trimmed result. The local variable exists only to give the compiler a target type for the first method reference — once it has one, `andThen` can accept a bare `String::toUpperCase`. Use `compose()` when you want the other order.

## Summary

- Functional interfaces let a method accept *behavior* as a parameter, so one implementation serves many callers.
- `Predicate.and()` / `or()` / `negate()` build compound conditions out of small named rules that stay reusable and testable on their own.
- `Function.andThen()` (and `compose()`) chains transformations without nesting calls.
- Method references are the clearest form when a lambda does nothing but delegate to an existing method.
