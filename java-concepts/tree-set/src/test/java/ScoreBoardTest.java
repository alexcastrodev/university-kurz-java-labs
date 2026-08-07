import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ScoreBoard")
class ScoreBoardTest {

    @Test
    @DisplayName("should sort scores regardless of the order they were recorded in")
    void shouldSortScoresRegardlessOfInsertionOrder() {
        ScoreBoard board = new ScoreBoard();
        board.record(50);
        board.record(90);
        board.record(10);
        board.record(70);

        assertEquals(90, board.highest());
        assertEquals(10, board.lowest());
    }

    @Test
    @DisplayName("should treat recording an existing score as a no-op")
    void shouldTreatRecordingAnExistingScoreAsNoOp() {
        ScoreBoard board = new ScoreBoard();
        board.record(50);
        board.record(50);
        board.record(60);

        assertEquals(50, board.closestAtLeast(50));
        assertEquals(60, board.closestAtLeast(51));
    }

    @Test
    @DisplayName("should throw from highest when nothing has been recorded")
    void shouldThrowFromHighestWhenNothingRecorded() {
        ScoreBoard board = new ScoreBoard();

        assertThrows(NoSuchElementException.class, board::highest);
    }

    @Test
    @DisplayName("should throw from lowest when nothing has been recorded")
    void shouldThrowFromLowestWhenNothingRecorded() {
        ScoreBoard board = new ScoreBoard();

        assertThrows(NoSuchElementException.class, board::lowest);
    }

    @Test
    @DisplayName("should return the smallest score at least as large as the target")
    void shouldReturnSmallestScoreAtLeastTarget() {
        ScoreBoard board = boardWith(10, 30, 50, 70);

        assertEquals(50, board.closestAtLeast(40));
        assertEquals(70, board.closestAtLeast(70));
        assertNull(board.closestAtLeast(100));
    }

    @Test
    @DisplayName("should return the largest score no larger than the target")
    void shouldReturnLargestScoreAtMostTarget() {
        ScoreBoard board = boardWith(10, 30, 50, 70);

        assertEquals(30, board.closestAtMost(40));
        assertEquals(10, board.closestAtMost(10));
        assertNull(board.closestAtMost(5));
    }

    @Test
    @DisplayName("should return every score inside the requested range")
    void shouldReturnEveryScoreInsideTheRequestedRange() {
        ScoreBoard board = boardWith(10, 30, 50, 70, 90);

        SortedSet<Integer> result = board.between(25, 75);

        assertEquals(new TreeSet<>(List.of(30, 50, 70)), result);
    }

    @Test
    @DisplayName("should include both endpoints of the range")
    void shouldIncludeBothEndpointsOfTheRange() {
        ScoreBoard board = boardWith(10, 30, 50, 70, 90);

        SortedSet<Integer> result = board.between(30, 70);

        assertEquals(new TreeSet<>(List.of(30, 50, 70)), result);
    }

    private static ScoreBoard boardWith(int... scores) {
        ScoreBoard board = new ScoreBoard();
        for (int score : scores) {
            board.record(score);
        }
        return board;
    }
}
