# The LinkedList Class

## Goal

Use `LinkedList` as both a `List` and a `Deque` at once — positional insertion alongside front/back operations — by modeling a playback queue where a track can either wait its turn or jump straight to the front.

## Prerequisites

- Basic Java syntax
- Familiarity with `List` and `Deque`

## Task

`PlaybackQueue` wraps a `LinkedList<String>` holding the tracks lined up to play. The track at the front is the one playing now. You'll implement the normal wait-your-turn addition at the back, a "play this next" addition that jumps straight to the front, a read of the current track that doesn't consume it, and a skip that removes it — plus, as a bonus, an insertion at an arbitrary position.

## Instructions

Complete the following TODOs in `PlaybackQueue`:

- TODO-00: Implement `enqueue()`, adding the track to the back of the queue.
- TODO-01: Implement `playNext()`, adding the track to the front of the queue.
- TODO-02: Implement `nowPlaying()`, returning the front track without removing it.
- TODO-03: Implement `skip()`, removing and returning the front track.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/linked-list test
```

Or from the lab directory:

```bash
cd java-concepts/linked-list
mvn test
```

## Bonus (Optional)

- TODO-04 (optional): Implement `insertAt()`, inserting a track at the given position (0 = front) using `LinkedList`'s `List`-style positional insertion.
