public interface PaymentGateway {

    PaymentResult charge(String customerId, int amountCents);
}
