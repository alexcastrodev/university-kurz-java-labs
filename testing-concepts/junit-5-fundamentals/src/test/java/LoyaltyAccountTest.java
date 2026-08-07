import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * LoyaltyAccount is already fully implemented in src/main/java — your job is
 * to write the tests that prove it behaves correctly. Follow the TODOs in
 * order; each one exercises a different piece of the JUnit 5 API.
 */
class LoyaltyAccountTest {

    private LoyaltyAccount account;

    // TODO-00: Assign a fresh LoyaltyAccount("M-1", 100) to `account` before
    // every test. This must run before EACH test method, not once for the
    // whole class - LoyaltyAccount is mutable, so a shared instance would let
    // one test's earn()/redeem() calls leak into the next test's balance.
    @BeforeEach
    void setUp() {
    }

    // TODO-01: Call account.earn(150) and assert that getPoints() is now 250.
    @Test
    @DisplayName("earn() increases the point balance")
    void earnIncreasesPoints() {
        fail("TODO-01: not implemented yet");
    }

    // TODO-02: earn(0) and earn(-5) must both throw IllegalArgumentException.
    // Check both in a single test using assertAll(), so a failure in one
    // doesn't hide a failure in the other.
    @Test
    @DisplayName("earn() rejects non-positive amounts")
    void earnRejectsNonPositiveAmounts() {
        fail("TODO-02: not implemented yet");
    }

    // TODO-03: Add a @CsvSource above this method with "points, expectedTier"
    // rows that pin down the exact tier boundaries: BRONZE below 500, SILVER
    // from 500 to 999, GOLD from 1000 up. Cover at least the values
    // immediately below AND at each boundary (e.g. 499 vs 500, 999 vs 1000).
    @ParameterizedTest
    @DisplayName("tier() reflects the point boundaries")
    void tierReflectsPointBoundaries(int points, String expectedTier) {
        assertEquals(expectedTier, new LoyaltyAccount("M-3", points).tier());
    }

    // TODO-04: Inside this @Nested class, add:
    //   - a @BeforeEach that calls account.earn(200) (account now has 300
    //     points, since the outer @BeforeEach already gave it 100)
    //   - a test asserting redeem(100) leaves the account with 200 points
    //   - a test asserting redeem(1000) throws IllegalStateException
    //   - a test asserting redeem(0) throws IllegalArgumentException
    @Nested
    @DisplayName("when redeeming points")
    class WhenRedeemingPoints {
    }

    // TODO-05 (optional): Write a @TestFactory method returning a
    // Stream<DynamicTest> that checks tier() for a list of (points, tier)
    // pairs of your choosing, generated at runtime instead of declared as
    // separate @Test methods.
}
