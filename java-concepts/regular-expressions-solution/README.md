# Regular Expressions: Parsing and Scanning Log Lines - Solution

## Overview

This is the official solution for the Regular Expressions lab. It shows `matches()` vs. `find()` used for their intended purposes, and a reluctant quantifier avoiding a classic greedy-match trap.

## Key Concepts

### matches() requires the whole input to fit

```java
Matcher matcher = LOG_LINE.matcher(line);
if (!matcher.matches()) {
    return Optional.empty();
}
```

`matches()` fails for `"prefix 2026-08-02 ERROR Payment failed"` even though the date/level/message shape appears in there — the pattern has to account for the *entire* string, start to end. That's the right choice for validating a single line's shape.

### find() in a loop scans for every occurrence

```java
Matcher matcher = LOG_LINE.matcher(log);
while (matcher.find()) {
    if ("ERROR".equals(matcher.group("level"))) {
        messages.add(matcher.group("message"));
    }
}
```

Each call to `find()` resumes searching from where the previous match ended, which is exactly how you walk through every line of a multi-line block without an explicit split — `.` in `(?<message>.+)` doesn't match the newline itself, so each match naturally stops at the end of its own line.

### Reluctant quantifiers stop at the first delimiter

```java
Pattern QUOTED = Pattern.compile("\"(.*?)\"");
```

A plain `"(.*)"` is greedy — against `"hello" and then "goodbye"` it would match from the *first* quote all the way to the *last* one, capturing `hello" and then "goodbye`. Appending `?` makes the quantifier reluctant: it matches as little as possible, so the group captures just `hello`.

## Summary

- Use `matches()` to validate a whole string's shape; use `find()` (in a loop) to scan for every occurrence within a larger text.
- Named groups (`(?<name>...)`) make extracting several related values from one match far more readable than counting group numbers.
- A greedy quantifier grabs the longest possible match — reach for a reluctant one (`?` suffix) whenever the intent is "stop at the *first* delimiter."
