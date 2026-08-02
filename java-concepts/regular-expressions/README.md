# Regular Expressions: Parsing and Scanning Log Lines

## Goal

Learn the difference between `matches()` (the whole input must fit) and `find()` (any subsequence, called repeatedly to scan for all occurrences), how named capturing groups make multi-field extraction readable, and why a reluctant quantifier (`.*?`) matters when a pattern needs to stop at the *first* delimiter instead of the last.

## Prerequisites

- Basic Java syntax
- Basic regex syntax (character classes, quantifiers)

## Task

Implement `LogLineParser`, which parses individual "yyyy-MM-dd LEVEL message" log lines using `Pattern`/`Matcher` with named groups, scans a multi-line block for every `ERROR`-level message, and extracts the first quoted substring from a piece of text.

## Instructions

Complete the following TODOs in `LogLineParser`:

- TODO-00: Parse a single log line with `matches()` and named groups.
- TODO-01: Find every `ERROR` message in a multi-line log with `find()`.
- TODO-02: Extract the first quoted substring using the already-reluctant `QUOTED` pattern.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/regular-expressions test
```

Or from the lab directory:

```bash
cd java-concepts/regular-expressions
mvn test
```

## Bonus

- TODO-03 (optional): Replace every digit in a string with `#`.
