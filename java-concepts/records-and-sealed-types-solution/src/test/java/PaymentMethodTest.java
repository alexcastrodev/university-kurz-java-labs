import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PaymentMethod")
class PaymentMethodTest {

    @Test
    @DisplayName("should create a valid credit card")
    void shouldCreateValidCreditCard() {
        CreditCard card = new CreditCard("4111111111111111", 12, 2030);

        assertEquals("4111111111111111", card.number());
    }

    @Test
    @DisplayName("should reject a credit card with a blank number")
    void shouldRejectCreditCardWithBlankNumber() {
        assertThrows(IllegalArgumentException.class, () -> new CreditCard(" ", 12, 2030));
    }

    @Test
    @DisplayName("should reject a credit card with an invalid expiry month")
    void shouldRejectCreditCardWithInvalidExpiryMonth() {
        assertThrows(IllegalArgumentException.class, () -> new CreditCard("4111111111111111", 13, 2030));
        assertThrows(IllegalArgumentException.class, () -> new CreditCard("4111111111111111", 0, 2030));
    }

    @Test
    @DisplayName("should create a valid bank transfer")
    void shouldCreateValidBankTransfer() {
        BankTransfer transfer = new BankTransfer("PT50000000000000000000000");

        assertEquals("PT50000000000000000000000", transfer.iban());
    }

    @Test
    @DisplayName("should reject a bank transfer with a blank iban")
    void shouldRejectBankTransferWithBlankIban() {
        assertThrows(IllegalArgumentException.class, () -> new BankTransfer(""));
    }

    @Test
    @DisplayName("should describe a credit card by its last 4 digits")
    void shouldDescribeCreditCard() {
        PaymentMethod method = new CreditCard("4111111111111111", 12, 2030);

        assertEquals("Credit card ending in 1111", PaymentMethods.describe(method));
    }

    @Test
    @DisplayName("should describe a bank transfer by its iban")
    void shouldDescribeBankTransfer() {
        PaymentMethod method = new BankTransfer("PT50000000000000000000000");

        assertEquals("Bank transfer to PT50000000000000000000000", PaymentMethods.describe(method));
    }

    @Test
    @DisplayName("should describe a digital wallet by its provider")
    void shouldDescribeDigitalWallet() {
        PaymentMethod method = new DigitalWallet("PayPal", "acct-123");

        assertEquals("Digital wallet via PayPal", PaymentMethods.describe(method));
    }

    @Test
    @DisplayName("should return the credit card processing fee")
    void shouldReturnFeeForCreditCard() {
        PaymentMethod method = new CreditCard("4111111111111111", 12, 2030);

        assertEquals(0.029, PaymentMethods.feePercentage(method), 0.0001);
    }

    @Test
    @DisplayName("should return zero fee for bank transfers")
    void shouldReturnZeroFeeForBankTransfer() {
        PaymentMethod method = new BankTransfer("PT50000000000000000000000");

        assertEquals(0.0, PaymentMethods.feePercentage(method), 0.0001);
    }

    @Test
    @DisplayName("should return the digital wallet processing fee")
    void shouldReturnFeeForDigitalWallet() {
        PaymentMethod method = new DigitalWallet("PayPal", "acct-123");

        assertEquals(0.015, PaymentMethods.feePercentage(method), 0.0001);
    }

    @Test
    @DisplayName("should waive the fee only for bank transfers (bonus)")
    void shouldWaiveFeeOnlyForBankTransfer() {
        assertTrue(PaymentMethods.isFeeWaived(new BankTransfer("PT50000000000000000000000")));
        assertFalse(PaymentMethods.isFeeWaived(new CreditCard("4111111111111111", 12, 2030)));
        assertFalse(PaymentMethods.isFeeWaived(new DigitalWallet("PayPal", "acct-123")));
    }
}
