# Records and Sealed Types: Modeling a Closed Set of Payment Methods - Solution

## Overview

This is the official solution for the Records and Sealed Types lab. It shows compact-constructor validation paired with an exhaustive switch over a sealed hierarchy.

## Key Concepts

### Compact constructors validate before assignment

```java
record BankTransfer(String iban) implements PaymentMethod {
    BankTransfer {
        if (iban == null || iban.isBlank()) {
            throw new IllegalArgumentException("iban must not be blank");
        }
    }
}
```

A compact constructor has no parameter list and no explicit field assignment — whatever it does to the implicit parameters happens before they're assigned to the record's fields at the end of the block. This is the one place validation logic runs for every construction path, including any deserialization framework that calls the canonical constructor directly.

### Exhaustive switch over a sealed hierarchy

```java
static String describe(PaymentMethod method) {
    return switch (method) {
        case CreditCard c -> "Credit card ending in " + c.number().substring(c.number().length() - 4);
        case BankTransfer b -> "Bank transfer to " + b.iban();
        case DigitalWallet w -> "Digital wallet via " + w.provider();
    };
}
```

Because `PaymentMethod` is sealed and permits exactly these three types, the compiler can prove the switch handles every case — no `default` branch is needed, or even allowed to silently swallow a future case. If a fourth record were ever added to the `permits` clause, every switch like this one across the codebase would stop compiling until a new case was added — that ripple effect is the entire point of sealing the hierarchy.

## Summary

- Compact constructors are the idiomatic place to reject bad data — they run for every construction path.
- Sealing a hierarchy trades some rigidity for a compiler-enforced guarantee that no case goes unhandled.
- Records are shallowly immutable and implicitly final — they can't be extended, and none of the fee logic here needs to worry about a payment method changing shape after construction.
