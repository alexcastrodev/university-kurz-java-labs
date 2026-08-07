import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class TextPipeline {

    public List<String> filter(List<String> lines, Predicate<String> predicate) {
        return lines.stream().filter(predicate).toList();
    }

    public List<String> transform(List<String> lines, Function<String, String> transformer) {
        return lines.stream().map(transformer).toList();
    }

    public Predicate<String> containsAndLongerThan(String needle, int minLength) {
        Predicate<String> containsNeedle = line -> line.contains(needle);
        Predicate<String> longEnough = line -> line.length() >= minLength;

        return containsNeedle.and(longEnough);
    }

    public long countBlankLines(List<String> lines) {
        return lines.stream().filter(String::isBlank).count();
    }

    public Function<String, String> trimThenUpper() {
        Function<String, String> trim = String::trim;

        return trim.andThen(String::toUpperCase);
    }
}
