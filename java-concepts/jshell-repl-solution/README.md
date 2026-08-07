# JShell: The Java REPL - Solution

## Overview

This is the official solution for the JShell lab. It embeds `jdk.jshell.JShell` — the evaluation engine behind the `jshell` command-line tool — to compile and run Java source that only exists as a `String` at runtime.

## Key Concepts

### Evaluating one snippet

```java
public String evaluate(String expression) {
    try (JShell jshell = JShell.builder().build()) {
        List<SnippetEvent> events = jshell.eval(expression);
        return events.get(events.size() - 1).value();
    }
}
```

`JShell` is `AutoCloseable`, and building one starts a separate JVM to run snippets in, so try-with-resources is the right shape — leaving the block shuts that JVM down. `eval()` returns a *list* of events because submitting one snippet can invalidate or re-resolve earlier ones; the event for the snippet you just submitted is the last entry, which is why the code reads it by index rather than assuming `get(0)`.

`SnippetEvent.value()` is the result *as the REPL would display it*, not `toString()`. That is why `"hello".toUpperCase()` comes back as `"HELLO"` with the quotes still attached, and why a snippet that produces nothing — a statement, or a snippet that failed to compile — yields `null` instead of an empty string.

### Sharing one instance so state persists

```java
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
```

A `JShell` instance *is* the session: it accumulates every variable, method and class you have fed it, exactly like a terminal REPL that remembers what you typed three lines ago. Evaluating `"int x = 5;"` and then `"x + 1"` against the same instance yields `"5"` and `"6"`; opening a fresh `JShell` for the second snippet would reject it with *cannot find symbol* instead, because that session never saw `x`.

Note the loop opens the engine once and reuses it — the persistence comes entirely from instance reuse, not from anything special about `eval()`. That is also why `evaluate()`, which builds and closes an engine per call, is deliberately amnesiac.

## Summary

- `jdk.jshell` ships with the JDK and is usable from ordinary classpath code — no dependency, no `--add-modules`.
- `eval()` returns a `List<SnippetEvent>`; take the last element for the snippet you submitted.
- `SnippetEvent.value()` is the REPL's rendering of the result (`String` values keep their quotes) and is `null` when the snippet produced no value or failed to compile.
- One `JShell` instance is one session — reuse it to keep state across snippets, close it to discard that state along with the JVM it was running in.
