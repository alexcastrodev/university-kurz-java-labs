/**
 * A hand-written stub: fixed behavior, written once outside any single test,
 * reused unmodified by every test that needs a gateway which always
 * approves. Unlike a Mockito mock, its return value isn't scripted per test.
 */
class StubPaymentGateway implements PaymentGateway {

    @Override
    public PaymentResult charge(String customerId, int amountCents) {
        return new PaymentResult(true, "TXN-STUB");
    }
}
