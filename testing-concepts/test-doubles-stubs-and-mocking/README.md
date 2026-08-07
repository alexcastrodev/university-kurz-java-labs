# Test Doubles: Stubs and Mocking

## Goal

`CheckoutService` is already fully implemented — your job is to test it
using both kinds of test double the article distinguishes: a hand-written
**stub** with fixed behavior, and a **Mockito mock** with behavior scripted
per test.

## Prerequisites

- JUnit 5 fundamentals (`@Test`, `assertThrows`)
- Reading `CheckoutService`, `PaymentGateway`, `ReceiptSender`, and
  `PaymentResult` in `src/main/java` before writing any test

## Task

`CheckoutService` depends on two collaborators, `PaymentGateway` and
`ReceiptSender`, injected through its constructor. That's what makes it
possible to substitute doubles for either one instead of hitting a real
payment processor in a test.

You'll write one coarse-grained test against a stub you build yourself,
then several fine-grained tests against Mockito mocks — each one scripting
a different scenario the real gateway could return.

## Instructions

Complete the following TODOs:

- TODO-00 (in `StubPaymentGateway`): implement the stub so it always
  approves with a fixed transaction id — same behavior no matter which
  test uses it, no matter how many times.
- TODO-01 (in `CheckoutServiceTest`): a coarse-grained test built with
  `new StubPaymentGateway()` — no Mockito involved.
- TODO-02: a fine-grained test that scripts `mockGateway` to approve, and
  checks the transaction id `checkout()` returns.
- TODO-03: a fine-grained test that scripts `mockGateway` to decline, and
  verifies the customer was notified of the decline.
- TODO-04: a test proving that an invalid amount is rejected *before*
  either collaborator is touched, using `Mockito.verifyNoInteractions`.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl testing-concepts/test-doubles-stubs-and-mocking test
```

Or from the lab directory:

```bash
cd testing-concepts/test-doubles-stubs-and-mocking
mvn test
```

## Bonus (Optional)

- TODO-05 (optional): write a test that verifies the exact arguments
  `mockGateway.charge(...)` was called with, not just what `checkout()`
  returned.
