import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.TreeSet;

public class ScoreBoard {

    private final TreeSet<Integer> scores = new TreeSet<>();

    public void record(int score) {
        // TODO-00: Record `score`. If it's already present, this is a no-op (Set semantics).
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public int highest() {
        // TODO-01: Return the highest recorded score.
        // Let it throw its natural exception if no scores have been recorded (don't catch/rethrow).
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public int lowest() {
        // TODO-02: Return the lowest recorded score.
        // Let it throw its natural exception if no scores have been recorded (don't catch/rethrow).
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public Integer closestAtLeast(int target) {
        // TODO-03: Return the smallest recorded score that is >= target, or null if
        // no recorded score qualifies.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public Integer closestAtMost(int target) {
        // TODO-04: Return the largest recorded score that is <= target, or null if
        // no recorded score qualifies.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public SortedSet<Integer> between(int low, int high) {
        // TODO-05 (optional): Return every recorded score in the inclusive range [low, high].
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
