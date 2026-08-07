/**
 * A customer loyalty account: earns and redeems points, and exposes a tier
 * derived from the current balance.
 */
public class LoyaltyAccount {

    private final String memberId;
    private int points;

    public LoyaltyAccount(String memberId, int startingPoints) {
        if (memberId == null || memberId.isBlank()) {
            throw new IllegalArgumentException("memberId must not be blank");
        }
        if (startingPoints < 0) {
            throw new IllegalArgumentException("startingPoints must not be negative");
        }
        this.memberId = memberId;
        this.points = startingPoints;
    }

    public String getMemberId() {
        return memberId;
    }

    public int getPoints() {
        return points;
    }

    public void earn(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be positive");
        }
        points += amount;
    }

    public void redeem(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be positive");
        }
        if (amount > points) {
            throw new IllegalStateException("insufficient points");
        }
        points -= amount;
    }

    /**
     * BRONZE below 500, SILVER from 500 to 999, GOLD from 1000 up.
     */
    public String tier() {
        if (points >= 1000) {
            return "GOLD";
        }
        if (points >= 500) {
            return "SILVER";
        }
        return "BRONZE";
    }
}
