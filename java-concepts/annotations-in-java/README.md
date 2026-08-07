# Annotations: Retention, Meta-Annotations, and Reflection

## Goal

Define a custom runtime-retained annotation and read it back via reflection to drive behavior — a lightweight version of what frameworks like JUnit and Spring do internally when they discover `@Test` or `@Transactional` methods.

## Prerequisites

- Basic Java syntax
- Familiarity with `java.lang.reflect.Method`

## Task

Three types are involved:

- `Important` is the custom annotation. It is declared `@Retention(RetentionPolicy.RUNTIME)` so it survives into the class file *and* stays readable at runtime, and `@Target(ElementType.METHOD)` so it can only be placed on methods. It carries a single `value()` element with an empty-string default.
- `SampleService` is a plain fixture class. Three of its four methods are marked `@Important`, one of them without a value.
- `AuditScanner` is what you implement. Given any `Class<?>`, it walks the declared methods and reports which ones are annotated, why (the annotation's value), and how many there are.

Both `Important` and `SampleService` are given to you — you only need to fill in `AuditScanner`.

## Instructions

Complete the following TODOs in `AuditScanner`:

- TODO-00: Implement `findImportantMethods()` — return the names of every method annotated with `@Important`.
- TODO-01: Implement `reasonFor()` — return the annotation's `value()` for a named method, or throw `NoSuchElementException` when that method isn't annotated (or doesn't exist).
- TODO-02: Implement `countImportant()` — return how many declared methods are annotated with `@Important`.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/annotations-in-java test
```

Or from the lab directory:

```bash
cd java-concepts/annotations-in-java
mvn test
```

## Bonus (Optional)

- TODO-03 (optional): Implement `findImportantMethodsSorted()`, returning the same names as `findImportantMethods()` but sorted alphabetically.
