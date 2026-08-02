public sealed interface PaymentMethod permits CreditCard, BankTransfer, DigitalWallet {
}

record CreditCard(String number, int expiryMonth, int expiryYear) implements PaymentMethod {

    CreditCard {
        // TODO-00: Validate the arguments before they're assigned to the record's
        // fields. Throw new IllegalArgumentException("number must not be blank")
        // if `number` is null or blank, and
        // new IllegalArgumentException("expiryMonth must be between 1 and 12")
        // if `expiryMonth` is out of range.
    }
}

record BankTransfer(String iban) implements PaymentMethod {

    BankTransfer {
        // TODO-01: Throw new IllegalArgumentException("iban must not be blank")
        // if `iban` is null or blank.
    }
}

record DigitalWallet(String provider, String accountId) implements PaymentMethod {
}

class PaymentMethods {

    static String describe(PaymentMethod method) {
        // TODO-02: Return a description for each permitted type:
        //   - CreditCard    -> "Credit card ending in " + the last 4 digits of number
        //   - BankTransfer  -> "Bank transfer to " + iban
        //   - DigitalWallet -> "Digital wallet via " + provider
        // Hint: a switch over a sealed interface can be exhaustive without a
        // default branch, once every permitted type has a case.

        throw new UnsupportedOperationException("Not implemented yet.");
    }

    static double feePercentage(PaymentMethod method) {
        // TODO-03: Return the processing fee for each type:
        //   - CreditCard    -> 0.029
        //   - BankTransfer  -> 0.0
        //   - DigitalWallet -> 0.015

        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Returns true only for payment methods that waive fees entirely
     * (currently just BankTransfer).
     */
    static boolean isFeeWaived(PaymentMethod method) {
        // TODO-04 (optional): Implement using the same kind of exhaustive switch.

        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
