import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;

import java.util.ArrayList;
import java.util.List;

public class SnippetEvaluator {

    public String evaluate(String expression) {
        // TODO-00: Evaluate `expression` as a single JShell snippet and return the
        // String value it produced.
        // Hint: JShell.builder().build() creates an engine; it's AutoCloseable, so use
        // try-with-resources. jshell.eval(expression) returns a List<SnippetEvent> — for
        // a single expression like "2 + 2" there will be exactly one event; its value()
        // is the result, as a String (e.g. "4"), or null if the snippet produced no value
        // (e.g. a statement rather than an expression).
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public List<String> evaluateAll(List<String> expressions) {
        // TODO-01: Evaluate each expression in `expressions`, in order, using the SAME
        // JShell instance for all of them (so state persists between snippets — e.g. a
        // variable declared in one snippet is visible to the next). Return their values,
        // in the same order.
        // Hint: open exactly one JShell instance for the whole call, evaluate every
        // expression against it, then close it.
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
