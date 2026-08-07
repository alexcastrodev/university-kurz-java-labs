import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CheckoutServiceTest {

    @Mock
    private PaymentGateway mockGateway;

    @Mock
    private ReceiptSender mockReceiptSender;

    @Test
    @DisplayName("coarse-grained: checkout succeeds against the stub gateway")
    void coarseGrainedCheckoutSucceedsWithStubGateway() {
        CheckoutService service = new CheckoutService(new StubPaymentGateway(), (customerId, message) -> {
        });

        String transactionId = service.checkout("cust-1", 500);

        assertEquals("TXN-STUB", transactionId);
    }

    @Test
    @DisplayName("fine-grained: an approved charge returns its transaction id")
    void mockedGatewayReturnsTransactionIdOnSuccess() {
        when(mockGateway.charge("cust-1", 500)).thenReturn(new PaymentResult(true, "TXN-1"));
        CheckoutService service = new CheckoutService(mockGateway, mockReceiptSender);

        String transactionId = service.checkout("cust-1", 500);

        assertEquals("TXN-1", transactionId);
    }

    @Test
    @DisplayName("fine-grained: a declined charge throws and notifies the customer")
    void declinedPaymentThrowsAndNotifiesDecline() {
        when(mockGateway.charge("cust-1", 500)).thenReturn(new PaymentResult(false, null));
        CheckoutService service = new CheckoutService(mockGateway, mockReceiptSender);

        assertThrows(IllegalStateException.class, () -> service.checkout("cust-1", 500));
        verify(mockReceiptSender).send("cust-1", "Payment declined");
    }

    @Test
    @DisplayName("an invalid amount never reaches the collaborators")
    void invalidAmountNeverTouchesCollaborators() {
        CheckoutService service = new CheckoutService(mockGateway, mockReceiptSender);

        assertThrows(IllegalArgumentException.class, () -> service.checkout("cust-1", 0));
        verifyNoInteractions(mockGateway, mockReceiptSender);
    }

    @Test
    @DisplayName("bonus: the gateway is charged with the exact amount requested")
    void gatewayIsChargedWithExactAmount() {
        when(mockGateway.charge("cust-1", 750)).thenReturn(new PaymentResult(true, "TXN-2"));
        CheckoutService service = new CheckoutService(mockGateway, mockReceiptSender);

        service.checkout("cust-1", 750);

        verify(mockGateway).charge("cust-1", 750);
        verify(mockGateway, never()).charge("cust-1", 500);
    }
}
