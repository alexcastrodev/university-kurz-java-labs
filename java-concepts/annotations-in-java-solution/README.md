# Annotations: Retention, Meta-Annotations, and Reflection - Solution

## Overview

This is the official solution for the Annotations lab. `AuditScanner` is a miniature version of what a test runner or a DI container does at startup: take a class, walk its declared methods, and pick out the ones carrying a marker annotation. The whole thing rests on one decision made in `Important` itself — the retention policy.

## Key Concepts

### The meta-annotations that make this possible

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Important {
    String value() default "";
}
```

`@Retention` decides how long the annotation survives. `SOURCE` discards it after compilation (that's `@Override`), `CLASS` — the default — writes it into the class file but hides it from the runtime, and only `RUNTIME` keeps it readable through reflection. With anything but `RUNTIME`, every method in this lab would return an empty result: the scanner would see zero annotated methods and never know why. `@Target(ElementType.METHOD)` restricts where the annotation is legal, so putting `@Important` on a field or a class becomes a compile error rather than a silently ignored marker.

### TODO-00: Discovering annotated methods

```java
public List<String> findImportantMethods(Class<?> type) {
    List<String> names = new ArrayList<>();
    for (Method method : type.getDeclaredMethods()) {
        if (method.isAnnotationPresent(Important.class)) {
            names.add(method.getName());
        }
    }
    return names;
}
```

`getDeclaredMethods()` returns every method declared directly on the class — public, private, and everything between — but nothing inherited; `getMethods()` would do the opposite (public only, inherited included). `isAnnotationPresent` is the cheap check when you only care *whether* the marker is there. Note that the returned array is in no specified order, which is why the test asserts on the set of names rather than an exact sequence.

### TODO-01: Reading the annotation's value

```java
public String reasonFor(Class<?> type, String methodName) {
    for (Method method : type.getDeclaredMethods()) {
        if (method.getName().equals(methodName) && method.isAnnotationPresent(Important.class)) {
            return method.getAnnotation(Important.class).value();
        }
    }
    throw new NoSuchElementException(methodName);
}
```

`getAnnotation` returns the annotation *instance*, and its elements are read by calling them like methods — `value()` here. Because `value()` declares `default ""`, `@Important` used bare (as on `validateState`) still yields a valid annotation whose value is the empty string, not `null`. Collapsing "not annotated" and "no such method" into the same `NoSuchElementException` is deliberate: from the caller's point of view there is simply no reason to report.

### TODO-02: Counting

```java
public int countImportant(Class<?> type) {
    return findImportantMethods(type).size();
}
```

Reusing TODO-00 keeps the definition of "important" in exactly one place. A stream is equally idiomatic when you don't want the intermediate list:

```java
return (int) Arrays.stream(type.getDeclaredMethods())
        .filter(method -> method.isAnnotationPresent(Important.class))
        .count();
```

### TODO-03 (optional): Sorting the result

```java
public List<String> findImportantMethodsSorted(Class<?> type) {
    return findImportantMethods(type).stream().sorted().toList();
}
```

Since `getDeclaredMethods()` gives no ordering guarantee, sorting is the way to get a stable, assertable result. `toList()` returns an unmodifiable list, which is the right shape for a derived view no caller should mutate.

## Summary

- `RetentionPolicy.RUNTIME` is the switch that makes an annotation visible to reflection at all; `SOURCE` and `CLASS` retention would leave this scanner seeing nothing.
- `@Target` constrains where an annotation may legally appear, turning misuse into a compile error.
- `getDeclaredMethods()` sees all methods declared on the class but no inherited ones, and returns them in no guaranteed order — sort if you need determinism.
- `isAnnotationPresent` answers "is it marked?"; `getAnnotation` hands you the instance so you can read its elements, with declared defaults filled in automatically.
