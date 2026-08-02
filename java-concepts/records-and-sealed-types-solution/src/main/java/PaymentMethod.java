public sealed interface PaymentMethod permits CreditCard, BankTransfer, DigitalWallet {
}

record CreditCard(String number, int expiryMonth, int expiryYear) implements PaymentMethod {

    CreditCard {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException("number must not be blank");
        }
        if (expiryMonth < 1 || expiryMonth > 12) {
            throw new IllegalArgumentException("expiryMonth must be between 1 and 12");
        }
    }
}

record BankTransfer(String iban) implements PaymentMethod {

    BankTransfer {
        if (iban == null || iban.isBlank()) {
            throw new IllegalArgumentException("iban must not be blank");
        }
    }
}

record DigitalWallet(String provider, String accountId) implements PaymentMethod {
}

class PaymentMethods {

    static String describe(PaymentMethod method) {
        return switch (method) {
            case CreditCard c -> "Credit card ending in " + c.number().substring(c.number().length() - 4);
            case BankTransfer b -> "Bank transfer to " + b.iban();
            case DigitalWallet w -> "Digital wallet via " + w.provider();
        };
    }

    static double feePercentage(PaymentMethod method) {
        return switch (method) {
            case CreditCard c -> 0.029;
            case BankTransfer b -> 0.0;
            case DigitalWallet w -> 0.015;
        };
    }

    static boolean isFeeWaived(PaymentMethod method) {
        return switch (method) {
            case BankTransfer b -> true;
            case CreditCard c -> false;
            case DigitalWallet w -> false;
        };
    }
}
