import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TextPipeline")
class TextPipelineTest {

    private final TextPipeline pipeline = new TextPipeline();

    @Test
    @DisplayName("should keep only the lines matching the predicate, in original order")
    void shouldKeepOnlyMatchingLinesInOriginalOrder() {
        List<String> lines = List.of("Alpha", "Bravo", "Anchor", "Charlie");

        List<String> result = pipeline.filter(lines, line -> line.startsWith("A"));

        assertEquals(List.of("Alpha", "Anchor"), result);
    }

    @Test
    @DisplayName("should return an empty list when no line matches the predicate")
    void shouldReturnEmptyListWhenNoLineMatches() {
        List<String> lines = List.of("Bravo", "Charlie");

        List<String> result = pipeline.filter(lines, line -> line.startsWith("A"));

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("should apply the transformer to every line, preserving order")
    void shouldApplyTransformerToEveryLine() {
        List<String> lines = List.of("alpha", "bravo", "charlie");

        List<String> result = pipeline.transform(lines, String::toUpperCase);

        assertEquals(List.of("ALPHA", "BRAVO", "CHARLIE"), result);
    }

    @Test
    @DisplayName("should match when the line contains the needle and is long enough")
    void shouldMatchWhenBothConditionsHold() {
        Predicate<String> predicate = pipeline.containsAndLongerThan("error", 10);

        assertTrue(predicate.test("fatal error occurred"));
    }

    @Test
    @DisplayName("should not match when the line contains the needle but is too short")
    void shouldNotMatchWhenLineIsTooShort() {
        Predicate<String> predicate = pipeline.containsAndLongerThan("error", 10);

        assertFalse(predicate.test("error"));
    }

    @Test
    @DisplayName("should not match when the line is long enough but lacks the needle")
    void shouldNotMatchWhenNeedleIsMissing() {
        Predicate<String> predicate = pipeline.containsAndLongerThan("error", 10);

        assertFalse(predicate.test("everything is fine"));
    }

    @Test
    @DisplayName("should count blank lines, treating whitespace-only lines as blank")
    void shouldCountBlankLines() {
        List<String> lines = List.of("alpha", "", "   ", "bravo", "\t");

        assertEquals(3, pipeline.countBlankLines(lines));
    }

    @Test
    @DisplayName("should count zero blank lines when every line has content")
    void shouldCountZeroBlankLinesWhenEveryLineHasContent() {
        List<String> lines = List.of("alpha", "bravo");

        assertEquals(0, pipeline.countBlankLines(lines));
    }

    @Test
    @DisplayName("should trim and then uppercase a string")
    void shouldTrimThenUppercase() {
        Function<String, String> trimThenUpper = pipeline.trimThenUpper();

        assertEquals("HELLO", trimThenUpper.apply("  hello  "));
    }
}
