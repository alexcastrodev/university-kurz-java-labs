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
        return Long.compare(this.amountCents, other.amountCents);
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
