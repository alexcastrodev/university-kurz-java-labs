# Lambda Expressions

## Goal

Learn to pass behavior as data using functional interfaces like `Predicate` and `Function`, and to compose small predicates and functions into bigger ones instead of writing one large conditional or loop.

## Prerequisites

- Basic Java syntax
- Familiarity with generics and interfaces

## Task

`TextPipeline` processes lists of text lines. Instead of hardcoding *what* to keep or *how* to rewrite each line, it takes that behavior as a parameter: a `Predicate<String>` decides which lines survive, a `Function<String, String>` decides how each line is rewritten.

You'll implement the two pipeline operations, then build a composed `Predicate` from two smaller ones, and count blank lines using a method reference.

## Instructions

Complete the following TODOs in `TextPipeline`:

- TODO-00: Implement `filter()`, returning only the lines that satisfy the predicate, in order.
- TODO-01: Implement `transform()`, applying the function to every line, in order.
- TODO-02: Implement `containsAndLongerThan()`, combining two small predicates with `Predicate.and()`.
- TODO-03: Implement `countBlankLines()` using the method reference `String::isBlank`.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/lambda-expressions test
```

Or from the lab directory:

```bash
cd java-concepts/lambda-expressions
mvn test
```

## Bonus (Optional)

- TODO-04 (optional): Implement `trimThenUpper()`, returning a single `Function<String, String>` built by composing the method references `String::trim` and `String::toUpperCase` with `Function.andThen()`.
