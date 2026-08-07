# Classpath Scanning via Reflection - Solution

## Overview

This is the official solution for the Classpath Scanning via Reflection lab. `ComponentScanner` performs the two jobs a container does at startup — filter candidate classes down to the ones it cares about, then instantiate them — using nothing but `java.lang.reflect` and the reflective methods on `Class` itself.

## Key Concepts

### TODO-00: matching an interface with isAssignableFrom()

```java
public <T> List<Class<? extends T>> findImplementations(List<Class<?>> candidates, Class<T> targetInterface) {
    List<Class<? extends T>> result = new ArrayList<>();
    for (Class<?> candidate : candidates) {
        if (candidate != targetInterface && targetInterface.isAssignableFrom(candidate)) {
            result.add(candidate.asSubclass(targetInterface));
        }
    }
    return result;
}
```

`targetInterface.isAssignableFrom(candidate)` reads backwards from what you might expect: it asks "can a `candidate` reference be assigned to a `targetInterface` variable?", which is exactly the "does it implement it?" question. The interface is assignable from itself, so the explicit `candidate != targetInterface` guard keeps `Notifier.class` out of its own result list.

The return is `List<Class<? extends T>>`, but `isAssignableFrom` is a runtime check the compiler knows nothing about — so a plain `(Class<? extends T>) candidate` cast would produce an unchecked-cast warning. `Class.asSubclass(Class<U>)` does the same narrowing with the cast performed and verified inside the JDK, throwing `ClassCastException` if the relationship does not actually hold. It is the clean, warning-free way to move from `Class<?>` to `Class<? extends T>` once you have proven the relationship.

### TODO-01: matching an annotation with isAnnotationPresent()

```java
public List<Class<?>> findAnnotated(List<Class<?>> candidates, Class<? extends Annotation> annotationType) {
    List<Class<?>> result = new ArrayList<>();
    for (Class<?> candidate : candidates) {
        if (candidate.isAnnotationPresent(annotationType)) {
            result.add(candidate);
        }
    }
    return result;
}
```

`isAnnotationPresent()` only sees annotations declared `@Retention(RetentionPolicy.RUNTIME)` — the default, `CLASS`, keeps them in the bytecode but discards them before they reach the reflection API, so a scanner would silently find nothing.

Notice this filter is completely independent of the interface filter. A class can satisfy one, both, or neither: `SmsNotifier` implements `Notifier` without being annotated, `LegacyHelper` is annotated without implementing `Notifier`, and only `EmailNotifier` does both. Keeping the two checks separate is what lets callers compose them however they need.

### TODO-02: instantiating with getDeclaredConstructor().newInstance()

```java
public <T> List<T> instantiateAll(List<Class<? extends T>> classes) throws ReflectiveOperationException {
    List<T> instances = new ArrayList<>();
    for (Class<? extends T> type : classes) {
        instances.add(type.getDeclaredConstructor().newInstance());
    }
    return instances;
}
```

`getDeclaredConstructor()` with no arguments looks up the no-arg constructor and throws `NoSuchMethodException` if the class does not have one — which is precisely why frameworks that instantiate by convention insist your components declare a default constructor. `newInstance()` then invokes it, wrapping anything the constructor throws in an `InvocationTargetException`.

`NoSuchMethodException`, `InvocationTargetException`, `InstantiationException` and `IllegalAccessException` all extend `ReflectiveOperationException`, so declaring that single checked supertype covers every way this call can fail.

### TODO-03 (optional): composing the two filters

```java
public <T> List<Class<? extends T>> findAnnotatedImplementations(
        List<Class<?>> candidates, Class<T> targetInterface, Class<? extends Annotation> annotationType) {
    List<Class<? extends T>> implementations = findImplementations(candidates, targetInterface);
    List<Class<?>> annotated = findAnnotated(candidates, annotationType);
    List<Class<? extends T>> result = new ArrayList<>();
    for (Class<? extends T> implementation : implementations) {
        if (annotated.contains(implementation)) {
            result.add(implementation);
        }
    }
    return result;
}
```

Because the two filters are independent and both preserve input order, intersecting their results is enough — no new reflection is needed. Starting from `implementations` also keeps the `Class<? extends T>` typing that `asSubclass()` already established, so the result needs no further casting.

## Summary

- `isAssignableFrom()` answers "does this class implement that type?" at runtime; read it as "can the argument be assigned to the receiver type?", not the other way around.
- `asSubclass()` is the idiomatic way to narrow a `Class<?>` to `Class<? extends T>` after a runtime check — it avoids the unchecked-cast warning and fails loudly if the relationship does not hold.
- `getDeclaredConstructor().newInstance()` needs a visible no-arg constructor and reports every failure mode as a `ReflectiveOperationException`.
- A real framework would discover the `candidates` list itself, walking package directories and jar entries on the classpath rather than being handed a fixed list. That discovery step is the only difference — the reflection mechanics for filtering and instantiating what it finds are exactly these.
