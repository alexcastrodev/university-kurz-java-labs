import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * CheckoutService is already fully implemented in src/main/java. Your job is
 * to write tests for it using two different kinds of test double: a
 * hand-written stub (StubPaymentGateway, see TODO-00) for a coarse-grained
 * test, and Mockito mocks for fine-grained, per-test control.
 */
@ExtendWith(MockitoExtension.class)
class CheckoutServiceTest {

    @Mock
    private PaymentGateway mockGateway;

    @Mock
    private ReceiptSender mockReceiptSender;

    // TODO-01: Build a CheckoutService with `new StubPaymentGateway()` and a
    // ReceiptSender that does nothing (ReceiptSender has a single abstract
    // method, so a lambda works: (customerId, message) -> {}). Assert that
    // checkout("cust-1", 500) returns "TXN-STUB" - the fixed value the stub
    // always returns, regardless of the arguments it was called with.
    @Test
    @DisplayName("coarse-grained: checkout succeeds against the stub gateway")
    void coarseGrainedCheckoutSucceedsWithStubGateway() {
        fail("TODO-01: not implemented yet");
    }

    // TODO-02: Using mockGateway, script `when(...).thenReturn(...)` so
    // charge("cust-1", 500) returns an approved PaymentResult with
    // transactionId "TXN-1". Build the service with mockGateway and
    // mockReceiptSender, call checkout("cust-1", 500), and assert it
    // returns "TXN-1".
    @Test
    @DisplayName("fine-grained: an approved charge returns its transaction id")
    void mockedGatewayReturnsTransactionIdOnSuccess() {
        fail("TODO-02: not implemented yet");
    }

    // TODO-03: Script mockGateway to return a declined PaymentResult
    // (approved = false). Assert that checkout(...) throws
    // IllegalStateException, then use Mockito.verify(...) to confirm
    // mockReceiptSender.send(...) was called with the customer id and the
    // exact message "Payment declined".
    @Test
    @DisplayName("fine-grained: a declined charge throws and notifies the customer")
    void declinedPaymentThrowsAndNotifiesDecline() {
        fail("TODO-03: not implemented yet");
    }

    // TODO-04: Assert that checkout("cust-1", 0) throws
    // IllegalArgumentException, then use
    // Mockito.verifyNoInteractions(mockGateway, mockReceiptSender) to prove
    // neither collaborator was ever touched - validation must happen before
    // any collaborator is called.
    @Test
    @DisplayName("an invalid amount never reaches the collaborators")
    void invalidAmountNeverTouchesCollaborators() {
        fail("TODO-04: not implemented yet");
    }

    // TODO-05 (optional): Write a test that verifies mockGateway.charge(...)
    // was called with the exact customerId and amountCents the caller
    // passed in - i.e. assert on the *input* to a collaborator, not just
    // the output of the method under test.
}
