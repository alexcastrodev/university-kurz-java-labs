import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class LoyaltyAccountTest {

    private LoyaltyAccount account;

    @BeforeEach
    void setUp() {
        account = new LoyaltyAccount("M-1", 100);
    }

    @Test
    @DisplayName("earn() increases the point balance")
    void earnIncreasesPoints() {
        account.earn(150);

        assertEquals(250, account.getPoints());
    }

    @Test
    @DisplayName("earn() rejects non-positive amounts")
    void earnRejectsNonPositiveAmounts() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> account.earn(0)),
                () -> assertThrows(IllegalArgumentException.class, () -> account.earn(-5))
        );
    }

    @ParameterizedTest
    @DisplayName("tier() reflects the point boundaries")
    @CsvSource({
            "0, BRONZE",
            "499, BRONZE",
            "500, SILVER",
            "999, SILVER",
            "1000, GOLD",
            "1500, GOLD"
    })
    void tierReflectsPointBoundaries(int points, String expectedTier) {
        assertEquals(expectedTier, new LoyaltyAccount("M-3", points).tier());
    }

    @Nested
    @DisplayName("when redeeming points")
    class WhenRedeemingPoints {

        @BeforeEach
        void earnExtraPoints() {
            account.earn(200);
        }

        @Test
        @DisplayName("redeem() decreases the point balance")
        void redeemDecreasesPoints() {
            account.redeem(100);

            assertEquals(200, account.getPoints());
        }

        @Test
        @DisplayName("redeem() rejects amounts above the balance")
        void redeemThrowsWhenExceedingBalance() {
            assertThrows(IllegalStateException.class, () -> account.redeem(1000));
        }

        @Test
        @DisplayName("redeem() rejects non-positive amounts")
        void redeemRejectsNonPositiveAmounts() {
            assertThrows(IllegalArgumentException.class, () -> account.redeem(0));
        }
    }

    @TestFactory
    @DisplayName("tier() dynamic boundary checks")
    Stream<DynamicTest> dynamicTierTests() {
        record Case(int points, String expectedTier) {
        }

        List<Case> cases = List.of(
                new Case(0, "BRONZE"),
                new Case(500, "SILVER"),
                new Case(1000, "GOLD")
        );

        return cases.stream().map(c -> dynamicTest(
                c.points() + " points is " + c.expectedTier(),
                () -> assertEquals(c.expectedTier(), new LoyaltyAccount("M-4", c.points()).tier())
        ));
    }
}
