/**
 * Charges a customer through a {@link PaymentGateway} and notifies them via
 * a {@link ReceiptSender}. Both collaborators are injected through the
 * constructor, which is what lets tests substitute doubles for either one.
 */
public class CheckoutService {

    private final PaymentGateway paymentGateway;
    private final ReceiptSender receiptSender;

    public CheckoutService(PaymentGateway paymentGateway, ReceiptSender receiptSender) {
        this.paymentGateway = paymentGateway;
        this.receiptSender = receiptSender;
    }

    public String checkout(String customerId, int amountCents) {
        if (amountCents <= 0) {
            throw new IllegalArgumentException("amountCents must be positive");
        }

        PaymentResult result = paymentGateway.charge(customerId, amountCents);

        if (!result.approved()) {
            receiptSender.send(customerId, "Payment declined");
            throw new IllegalStateException("payment declined");
        }

        receiptSender.send(customerId, "Receipt for transaction " + result.transactionId());
        return result.transactionId();
    }
}
