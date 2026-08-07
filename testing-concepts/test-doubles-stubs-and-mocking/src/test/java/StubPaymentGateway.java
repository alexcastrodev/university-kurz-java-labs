/**
 * A hand-written stub: fixed behavior, written once outside any single test,
 * reused unmodified by every test that needs a gateway which always
 * approves. Unlike a Mockito mock, its return value isn't scripted per test.
 */
class StubPaymentGateway implements PaymentGateway {

    // TODO-00: Always approve, returning a PaymentResult with
    // transactionId "TXN-STUB" - regardless of customerId or amountCents.
    @Override
    public PaymentResult charge(String customerId, int amountCents) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
