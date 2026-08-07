import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class TextPipeline {

    public List<String> filter(List<String> lines, Predicate<String> predicate) {
        // TODO-00: Return only the lines that satisfy predicate, preserving order.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public List<String> transform(List<String> lines, Function<String, String> transformer) {
        // TODO-01: Apply transformer to every line, preserving order.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public Predicate<String> containsAndLongerThan(String needle, int minLength) {
        // TODO-02: Build and return ONE Predicate<String> that is true only when
        // the line contains `needle` AND its length is >= minLength.
        // Hint: build it from two smaller predicates combined with Predicate.and().
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public long countBlankLines(List<String> lines) {
        // TODO-03: Count how many lines are blank.
        // Hint: use the method reference String::isBlank.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public Function<String, String> trimThenUpper() {
        // TODO-04 (optional): Return ONE Function<String,String> that trims a string
        // then uppercases it, built by composing two method references
        // (String::trim, String::toUpperCase) with Function.andThen().
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
