# JUnit 5 Fundamentals

## Goal

`LoyaltyAccount` is already fully implemented — your job is to write the
tests that prove it works. You'll practice the core JUnit 5 API: the
`@BeforeEach` lifecycle, `assertAll`/`assertThrows`, `@ParameterizedTest`,
and `@Nested` test classes.

## Prerequisites

- Basic Java syntax
- Reading `LoyaltyAccount` in `src/main/java` before writing any test

## Task

Open `LoyaltyAccountTest` in `src/test/java`. Every method already has a
TODO comment describing exactly what the test should check — you write the
JUnit code that checks it.

This is not about copying the article. `LoyaltyAccount.earn()`,
`redeem()`, and `tier()` are new methods with their own rules (validation,
tier boundaries) that you have to read and understand before you can test
them correctly.

## Instructions

Complete the following TODOs in `LoyaltyAccountTest`:

- TODO-00: Give every test a fresh `LoyaltyAccount` in `@BeforeEach` —
  think about why this must run before *each* test, not once for the
  whole class.
- TODO-01: Test that `earn()` increases the balance.
- TODO-02: Test that `earn()` rejects non-positive amounts, checking both
  invalid inputs in one `assertAll()`.
- TODO-03: Add a `@CsvSource` that pins down the exact `tier()`
  boundaries — get the edge cases wrong and this test will let a bug
  through.
- TODO-04: Fill in the `@Nested` `WhenRedeemingPoints` class with its own
  setup and three tests covering `redeem()`.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl testing-concepts/junit-5-fundamentals test
```

Or from the lab directory:

```bash
cd testing-concepts/junit-5-fundamentals
mvn test
```

## Bonus (Optional)

- TODO-05 (optional): Write a `@TestFactory` method that generates
  `tier()` checks as `DynamicTest`s instead of separate `@Test` methods.
