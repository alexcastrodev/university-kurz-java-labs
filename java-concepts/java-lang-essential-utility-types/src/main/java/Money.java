import java.util.Objects;

public class Money implements Comparable<Money> {

    private final String currency;
    private final long amountCents;

    public Money(String currency, long amountCents) {
        this.currency = currency;
        this.amountCents = amountCents;
    }

    public String currency() {
        return currency;
    }

    public long amountCents() {
        return amountCents;
    }

    @Override
    public int compareTo(Money other) {
        // TODO-00: Compare by amountCents only (ignore currency for this exercise).
        // Return a negative number, zero, or a positive number the way Comparable requires.
        // Hint: Long.compare(a, b) does exactly this without overflow risk.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Money other
                && currency.equals(other.currency)
                && amountCents == other.amountCents;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amountCents);
    }

    @Override
    public String toString() {
        return currency + " " + amountCents;
    }
}
