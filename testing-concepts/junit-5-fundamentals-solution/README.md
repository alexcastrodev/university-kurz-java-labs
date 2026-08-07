# JUnit 5 Fundamentals — Solution

## Overview

`LoyaltyAccountTest` is a full test suite for the (already-implemented)
`LoyaltyAccount` class, written to exercise the JUnit 5 features covered
in the article: lifecycle callbacks, grouped/combined assertions,
parameterized tests, nested test classes, and dynamic tests.

## Key Concepts

- **`@BeforeEach` creates a fresh instance per test.** `LoyaltyAccount` is
  mutable — `earn()`/`redeem()` change its internal `points` field. If
  `account` were built once in `@BeforeAll` instead, `earnIncreasesPoints`
  would leave the balance at 250, and every test that ran after it would
  start from that leaked value instead of the intended 100.
- **`assertAll` reports every failure, not just the first.** Wrapping both
  `assertThrows` calls in `earnRejectsNonPositiveAmounts` in a single
  `assertAll` means a regression in *either* validation check shows up in
  the failure output, instead of `earn(-5)` silently going unchecked
  because `earn(0)` already failed and stopped the method.
- **The `@CsvSource` rows target the tier boundaries directly, not just
  "any" value per tier.** `tier()` uses `>=`, so the bug that matters is
  an off-by-one at 500 or 1000 — testing 499/500 and 999/1000 as adjacent
  pairs is what actually catches that class of bug; testing 200, 700, and
  1200 would not.
- **`@Nested` classes compose `@BeforeEach` methods.** `WhenRedeemingPoints`
  runs the outer `setUp()` (100 points) and then its own `earnExtraPoints()`
  (+200), so every test inside starts from 300 — that composition is why
  the nested class doesn't need to repeat the account construction.
- **`@TestFactory` builds `DynamicTest`s at runtime.** Unlike the
  `@ParameterizedTest` above, these cases aren't declared in source as
  separate methods or annotation values — they come from a `List` built in
  the method body, which is the trade-off called out in the article: an
  IDE can't list them ahead of running the class.

## Summary

The point of this lab isn't memorizing annotation names — it's noticing
that a mutable object under test forces you to think about test isolation
(`@BeforeEach`), and that boundary logic (`>=` thresholds) forces you to
choose test inputs deliberately instead of picking arbitrary ones.
