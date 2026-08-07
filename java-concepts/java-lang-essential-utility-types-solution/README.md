# java.lang Essentials: Comparable, AutoCloseable, StackWalker, ProcessBuilder - Solution

## Overview

This is the official solution for the java.lang Essentials lab, showing how a type declares its natural ordering with `Comparable` and how a resource type plugs into try-with-resources with `AutoCloseable`.

## Key Concepts

### TODO-00: the compareTo contract

```java
@Override
public int compareTo(Money other) {
    return Long.compare(this.amountCents, other.amountCents);
}
```

`compareTo` only has to report *sign*: negative when `this` sorts before `other`, zero when they tie, positive when it sorts after. The exact magnitude is irrelevant, which is why `Long.compare` is preferred over `(int) (this.amountCents - other.amountCents)` — the subtraction can overflow and flip the sign. The ordering must also be consistent: if `a.compareTo(b) < 0` then `b.compareTo(a) > 0`, and the relation has to be transitive, otherwise sorting behaves unpredictably.

### TODO-01 and TODO-02: an idempotent close()

```java
public boolean isClosed() {
    return closed;
}

@Override
public void close() {
    closed = true;
}
```

`AutoCloseable.close()` is documented as strongly encouraged to be idempotent: a second call must be harmless. Try-with-resources itself calls `close()` exactly once, but real code frequently closes explicitly inside the block *and* leaves the resource under try-with-resources, or wraps a resource that gets closed by an outer owner. A plain assignment to `true` satisfies this for free — throwing "already closed" here would turn a harmless double close into a crash.

### TODO-03: sorting by natural ordering without mutating the input

```java
public List<Money> sortedByAmount(List<Money> monies) {
    List<Money> sorted = new ArrayList<>(monies);
    Collections.sort(sorted);
    return sorted;
}
```

`Collections.sort` (like `List.sort(null)`) sorts *in place* using the elements' `Comparable` implementation, so it needs a copy to leave the caller's list alone. The copy also makes the method safe for immutable inputs such as `List.of(...)`, which would throw `UnsupportedOperationException` if sorted directly. `monies.stream().sorted().toList()` is an equally idiomatic one-liner with the same semantics.

### TODO-04 (optional): try-with-resources guarantees close()

```java
public void openAndUse(Supplier<PooledConnection> factory, Consumer<PooledConnection> action) {
    try (PooledConnection connection = factory.get()) {
        action.accept(connection);
    }
}
```

The compiler expands try-with-resources into a `try`/`finally` that calls `close()` on every resource in reverse declaration order — so the connection is released whether the body returns normally or throws. If both the body and `close()` throw, the body's exception propagates and the one from `close()` is attached as a *suppressed* exception (readable via `Throwable.getSuppressed()`), which is exactly what a hand-written `finally { close(); }` would silently lose.

## Summary

- `Comparable<T>` defines a type's single natural ordering; `compareTo` returns a sign, and `Long.compare`/`Integer.compare` produce it without overflow risk.
- Sorting helpers such as `Collections.sort` and `TreeMap`/`TreeSet` rely on that natural ordering, so a broken `compareTo` breaks them silently.
- `AutoCloseable` is the contract behind try-with-resources; make `close()` idempotent, and let the language guarantee it runs even when the body throws.
- `StackWalker` (lazy, efficient stack frame inspection) and `ProcessBuilder` (launching and wiring up OS processes) belong to this same `java.lang` essentials concept, but aren't covered hands-on here because they're hard to unit-test deterministically.
