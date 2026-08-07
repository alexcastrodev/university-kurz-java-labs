import java.util.SortedSet;
import java.util.TreeSet;

public class ScoreBoard {

    private final TreeSet<Integer> scores = new TreeSet<>();

    public void record(int score) {
        scores.add(score);
    }

    public int highest() {
        return scores.last();
    }

    public int lowest() {
        return scores.first();
    }

    public Integer closestAtLeast(int target) {
        return scores.ceiling(target);
    }

    public Integer closestAtMost(int target) {
        return scores.floor(target);
    }

    public SortedSet<Integer> between(int low, int high) {
        return scores.subSet(low, true, high, true);
    }
}
