# The HashSet Class

## Goal

Perform set algebra — intersection, difference and union — on top of `HashSet` using `retainAll()`, `removeAll()` and `addAll()` on defensive copies, so that asking a question about two sets never mutates either of them.

## Prerequisites

- Basic Java syntax
- Familiarity with the `Set` interface

## Task

`TagIndex` keeps a `HashSet<String>` of tags for a single item (an article, a photo, a bookmark — whatever you like). Tagging is case-insensitive, so `"Java"` and `"java"` are the same tag. You'll implement adding a tag while reporting whether it was actually new, a case-insensitive membership check, and two comparisons against another index: the tags they share and the tags only this index has.

## Instructions

Complete the following TODOs in `TagIndex`:

- TODO-00: Implement `addTag()`, lowercasing the tag and returning whether it was newly added.
- TODO-01: Implement `hasTag()`, checking membership case-insensitively.
- TODO-02: Implement `commonTagsWith()` (the intersection) using `retainAll()` on a copy.
- TODO-03: Implement `tagsOnlyInThis()` (the difference) using `removeAll()` on a copy.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/hash-set test
```

Or from the lab directory:

```bash
cd java-concepts/hash-set
mvn test
```

## Bonus (Optional)

- TODO-04 (optional): Implement `allTagsCombined()` (the union) using `addAll()` on a copy, again without mutating either index.
