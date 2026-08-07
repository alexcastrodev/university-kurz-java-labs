import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;

import java.util.ArrayList;
import java.util.List;

public class SnippetEvaluator {

    public String evaluate(String expression) {
        try (JShell jshell = JShell.builder().build()) {
            List<SnippetEvent> events = jshell.eval(expression);
            return events.get(events.size() - 1).value();
        }
    }

    public List<String> evaluateAll(List<String> expressions) {
        List<String> results = new ArrayList<>();
        try (JShell jshell = JShell.builder().build()) {
            for (String expression : expressions) {
                List<SnippetEvent> events = jshell.eval(expression);
                results.add(events.get(events.size() - 1).value());
            }
        }
        return results;
    }
}
