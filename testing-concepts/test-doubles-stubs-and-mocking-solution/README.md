# Test Doubles: Stubs and Mocking — Solution

## Overview

`CheckoutServiceTest` tests the same (already-implemented) `CheckoutService`
two ways: once against a hand-written stub, and several times against
Mockito mocks scripted per test.

## Key Concepts

- **A stub is written once, outside the test, with fixed behavior.**
  `StubPaymentGateway.charge(...)` always returns the same
  `PaymentResult(true, "TXN-STUB")` no matter which test uses it or what
  arguments it's called with — that's what makes `coarseGrainedCheckout...`
  a *stub* test rather than a mock test: nothing is scripted inside the
  test method itself.
- **A mock's behavior is scripted per test, right before use.**
  `mockedGatewayReturnsTransactionIdOnSuccess` and
  `declinedPaymentThrowsAndNotifiesDecline` use the *same* `mockGateway`
  field but make it return opposite results — that's only possible because
  each test calls its own `when(...).thenReturn(...)` before exercising
  `checkout()`.
- **`verify()` asserts on *how* a collaborator was called, not just the
  return value.** `declinedPaymentThrowsAndNotifiesDecline` couldn't check
  that the customer was notified of the decline by inspecting
  `checkout()`'s return value alone — `checkout()` throws in that path, so
  the only way to observe the notification is to verify the call Mockito
  recorded on `mockReceiptSender`.
- **`verifyNoInteractions` proves validation happens before collaboration.**
  `invalidAmountNeverTouchesCollaborators` doesn't just check that
  `checkout(customerId, 0)` throws — a service that called `charge()` first
  and validated afterward could throw the same exception while still
  hitting the payment gateway. The `verifyNoInteractions` call is what
  actually pins down the ordering.
- **Mockito needs an interface to substitute.** Both doubles work only
  because `CheckoutService` depends on `PaymentGateway`/`ReceiptSender`
  through its constructor rather than constructing a concrete gateway
  itself — the same requirement the article calls out for hand-written
  stubs.

## Summary

The stub test and the mock tests aren't interchangeable: the stub buys a
test that reads like a real (if minimal) collaborator, while each mock test
buys precise control over one specific scenario and, via `verify()`, a
failure message that points at the exact expectation that broke.
