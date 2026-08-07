# JShell: The Java REPL

## Goal

Use JShell's public API (`jdk.jshell.JShell`) to evaluate Java snippets programmatically — the same engine the `jshell` command-line tool is built on.

## Prerequisites

- Basic Java syntax
- try-with-resources

## Task

`SnippetEvaluator` runs Java source code that only exists as a `String` at runtime. `evaluate()` takes a single snippet (`"2 + 2"`) and returns the value it produced (`"4"`). `evaluateAll()` takes several snippets and runs them together, so that a variable declared in one is still there for the next.

You are not driving the interactive `jshell>` prompt here. `jdk.jshell` is the *embeddable API* the interactive tool is written on top of, and it ships with the JDK — no extra dependency, no module flags. A `JShell` instance is a live evaluation session: it holds all the snippets you have fed it, and closing it throws that state away.

Two details worth knowing before you start:

- `eval()` returns a `List<SnippetEvent>` because one snippet can affect others (a redefined method, for example, updates everything that referenced it). The event describing what you just submitted is the last one in the list.
- A declaration snippet needs its terminating semicolon (`"int x = 5;"`); a bare expression (`"2 + 2"`) does not.

## Instructions

Complete the following TODOs in `SnippetEvaluator`:

- TODO-00: Implement `evaluate()` — open a `JShell`, evaluate one snippet, return its value.
- TODO-01: Implement `evaluateAll()` — evaluate every snippet against a single shared `JShell` instance so state persists between them.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/jshell-repl test
```

Or from the lab directory:

```bash
cd java-concepts/jshell-repl
mvn test
```
