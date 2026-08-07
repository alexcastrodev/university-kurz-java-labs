# The ArrayList Class

## Goal

Use `ArrayList`'s positional operations directly — inserting and removing at an index, and watching the surrounding elements shift — and convert the list back into a plain array.

## Prerequisites

- Basic Java syntax
- Generics
- Familiarity with the `List` interface

## Task

`Playlist` wraps an `ArrayList<String>` holding track names in play order. You'll implement appending a track, inserting one at a given position (pushing the rest later), removing one by position (pulling the rest earlier), and handing the whole playlist back as a `String[]`.

## Instructions

Complete the following TODOs in `Playlist`:

- TODO-00: Append a track to the end of the playlist.
- TODO-01: Insert a track at an index, shifting everything at and after it one position later.
- TODO-02: Remove the track at an index, shifting everything after it one position earlier.
- TODO-03: Return the tracks as a `String[]` (not an `Object[]`).

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/array-list test
```

Or from the lab directory:

```bash
cd java-concepts/array-list
mvn test
```

## Bonus (Optional)

- TODO-04 (optional): Implement `prepareForBulkLoad()` so it hints to the underlying `ArrayList` that roughly `expectedTotal` tracks are coming, avoiding repeated reallocation of the backing array.
