# Classpath Scanning via Reflection

## Goal

Understand the reflection primitives that component-scanning frameworks like Spring build on: `Class.isAssignableFrom()` to match a type against an interface, `Class.isAnnotationPresent()` to match it against an annotation, and `Class.getDeclaredConstructor().newInstance()` to turn a surviving `Class` object into a live instance.

## Prerequisites

- Basic Java syntax
- Interfaces
- Annotations

## Task

`ComponentScanner` receives a list of candidate `Class<?>` objects and has to answer the questions a container asks at startup: which of these implement a given interface, which of these carry a given annotation, and how do I instantiate the ones that survive?

The lab ships with the fixtures the scanner works against:

- `Notifier` — a marker interface with a single `notify(String)` method.
- `Component` — a runtime-retained marker annotation.
- `EmailNotifier` — implements `Notifier` **and** is annotated `@Component`.
- `SmsNotifier` — implements `Notifier` but is **not** annotated.
- `LegacyHelper` — is annotated `@Component` but does **not** implement `Notifier`.

Those three candidates cover every combination on purpose: matching an interface and matching an annotation are independent checks, and the tests will catch a scanner that conflates them.

Note that the candidate list is handed to the scanner as a plain `List<Class<?>>`. A real framework would discover that list itself by walking directories and jars, but that plumbing is not what this lab is about — the filtering and instantiating mechanics are identical either way.

## Instructions

Complete the following TODOs in `ComponentScanner`:

- TODO-00: Implement `findImplementations()` using `Class.isAssignableFrom()`.
- TODO-01: Implement `findAnnotated()` using `Class.isAnnotationPresent()`.
- TODO-02: Implement `instantiateAll()` using `Class.getDeclaredConstructor().newInstance()`.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/classpath-scanning-via-reflection test
```

Or from the lab directory:

```bash
cd java-concepts/classpath-scanning-via-reflection
mvn test
```

## Bonus (Optional)

- TODO-03 (optional): Implement `findAnnotatedImplementations()`, returning only the candidates that satisfy both conditions — implementing the target interface *and* carrying the annotation. Try building it on top of the two methods you already wrote.
