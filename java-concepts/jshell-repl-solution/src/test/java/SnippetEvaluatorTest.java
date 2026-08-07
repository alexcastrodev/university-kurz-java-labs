import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SnippetEvaluator")
class SnippetEvaluatorTest {

    @Test
    @DisplayName("should evaluate an arithmetic expression and return its value")
    void shouldEvaluateArithmeticExpression() {
        SnippetEvaluator evaluator = new SnippetEvaluator();

        assertEquals("4", evaluator.evaluate("2 + 2"));
    }

    @Test
    @DisplayName("should return a String result the way the REPL prints it, quotes included")
    void shouldReturnStringResultWithQuotes() {
        SnippetEvaluator evaluator = new SnippetEvaluator();

        assertEquals("\"HELLO\"", evaluator.evaluate("\"hello\".toUpperCase()"));
    }

    @Test
    @DisplayName("should evaluate every expression and return the values in order")
    void shouldEvaluateEveryExpressionInOrder() {
        SnippetEvaluator evaluator = new SnippetEvaluator();

        List<String> results = evaluator.evaluateAll(List.of("1 + 1", "3 * 3"));

        assertEquals(List.of("2", "9"), results);
    }

    @Test
    @DisplayName("should keep state between snippets evaluated in the same call")
    void shouldKeepStateBetweenSnippets() {
        SnippetEvaluator evaluator = new SnippetEvaluator();

        List<String> results = evaluator.evaluateAll(List.of("int x = 5;", "x + 1"));

        assertEquals(2, results.size());
        assertEquals("5", results.get(0));
        assertEquals("6", results.get(1));
    }

    @Test
    @DisplayName("should not see a variable declared by a previous, separate evaluate call")
    void shouldNotSeeVariableFromSeparateEvaluateCall() {
        SnippetEvaluator evaluator = new SnippetEvaluator();
        evaluator.evaluate("int counter = 41;");

        assertNull(evaluator.evaluate("counter + 1"));
    }

    @Test
    @DisplayName("should return an empty list when there is nothing to evaluate")
    void shouldReturnEmptyListWhenNothingToEvaluate() {
        SnippetEvaluator evaluator = new SnippetEvaluator();

        assertEquals(List.of(), evaluator.evaluateAll(List.of()));
    }
}
