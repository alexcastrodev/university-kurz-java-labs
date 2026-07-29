# Java Labs Guidelines

This document defines the conventions for every lab in the Kurz University.

The objective is to provide a consistent learning experience while keeping the repository easy to maintain and extend.

---

# Repository Structure

All labs live in a single repository.

Labs are grouped by learning track.

```
java-labs/
├── pom.xml
│
├── java-minute/
│   ├── array-equality/
│   ├── array-equality-solution/
│   ├── string-length/
│   └── ...
│
├── java-concepts/
│   ├── pattern-matching/
│   ├── pattern-matching-solution/
│   └── ...
│
├── spring-minute/
├── spring-concepts/
├── system-design/
└── ...
```

Each lab is an independent Maven module.

The starter project and solution project must compile independently.

---

# Naming Convention

Each lab uses the article slug.

Examples:

```
array-equality
string-length
pattern-matching
records
switch-expressions
```

Official solutions append `-solution`.

```
array-equality-solution
string-length-solution
```

---

# Project Structure

Every lab follows the same layout.

```
array-equality/
├── README.md
├── pom.xml
├── src
│   ├── main
│   └── test
└── .gitignore
```

---

# Learning Philosophy

Labs should encourage active learning.

Avoid asking students to copy code from the article.

Instead, learners should:

- discover behavior
- investigate failures
- implement missing logic
- debug existing code
- validate assumptions using tests

The goal is to understand the concept before looking at the official solution.

---

# TODO Convention

Every starter project guides the learner through numbered TODOs.

Use sequential identifiers with two digits.

```java
// TODO-00: Validate the input.
// TODO-01: Handle edge cases.
// TODO-02: Implement the algorithm.
// TODO-03: Return the expected result.
```

Guidelines:

- Always start at `TODO-00`
- Increment sequentially
- Use two digits (`00`, `01`, `02`, ...)
- Keep each TODO focused on a single task
- Write TODOs as actionable instructions

Avoid:

```java
// TODO
```

Prefer:

```java
// TODO-02: Compare every element in both arrays.
```

---

# Optional Tasks Convention

Optional challenges use the `TODO` prefix with `(optional)` marker.

```java
// TODO-03 (optional): Solve using Arrays.equals().
// TODO-04 (optional): Compare the performance of both implementations.
```

Guidelines:

- Continue numbering sequentially from required TODOs
- Mark with `(optional)` to indicate they are not required
- Optional tasks should never be required for the lab to pass
- Implement optional tasks in the solution project for students to review

In the README, mark them clearly:

```markdown
## Bonus (Optional)

- TODO-03 (optional): Task description
- TODO-04 (optional): Task description
```

---

# Tests

Every lab must include automated tests.

Tests are part of the learning experience.

The recommended workflow is:

1. Run the tests.
2. Observe the failing tests.
3. Complete the TODOs.
4. Run the tests again.
5. Repeat until all tests pass.

Some labs may also demonstrate multiple valid implementations in the solution project.

Tests should:

- focus on behavior
- remain readable
- provide clear failure messages

Example:

```java
shouldReturnFalseWhenEitherArrayIsNull()

shouldReturnFalseWhenLengthsDiffer()

shouldReturnTrueWhenArraysContainTheSameValues()
```

---

# Running a Lab

Each lab is an independent Maven module.

Run all tests for a specific lab:

```bash
./mvnw -pl java-minute/array-equality test
```

Or navigate into the lab directory and run:

```bash
cd java-minute/array-equality
./mvnw test
```

---

# README Structure

Every lab README follows the same template.

```markdown
# Array Equality

## Goal

Explain what the learner will accomplish.

## Prerequisites

Required knowledge.

## Task

Describe the exercise.

## Instructions

Complete the following TODOs:

- TODO-00
- TODO-01
- TODO-02

Run the tests until they all pass.

## Bonus (Optional)

- TODO-03 (optional): Optional challenge
- TODO-04 (optional): Optional challenge
```

---

# Starter Project

The starter project contains:

- incomplete implementation
- numbered TODOs
- automated tests
- enough context to solve the exercise

Do not include the final implementation.

Example:

```java
public static boolean equals(int[] first, int[] second) {
    // TODO-00: Validate the arguments.

    // TODO-01: Compare both arrays.

    throw new UnsupportedOperationException("Not implemented yet.");
}
```

---

# Solution Project

The solution project contains the official implementation.

Requirements:

- production-quality code
- idiomatic Java
- readable implementation
- minimal comments
- explanation in the README
- all optional tasks (TODO-xx (optional)) fully implemented for student review

When appropriate, demonstrate multiple valid solutions, highlighting the most idiomatic approach.

Students can compare their optional implementations with the solution to learn alternative approaches.

---

# Code Style

Prefer modern Java.

Use:

- descriptive names
- immutable variables whenever possible
- standard library APIs
- readable code

Avoid:

- unnecessary comments
- premature optimization
- magic numbers
- clever but difficult-to-read code

---

# Dependencies

Prefer only:

- JUnit 5
- AssertJ (optional)

Avoid unnecessary libraries.

The objective is to learn Java—not frameworks.

---

# Quality Checklist

Before publishing a lab, verify:

- [ ] Project builds successfully
- [ ] Tests execute correctly
- [ ] README follows the template
- [ ] TODO numbering is sequential
- [ ] Starter contains no implementation
- [ ] Solution compiles independently
- [ ] Edge cases are covered
- [ ] Code follows modern Java practices
