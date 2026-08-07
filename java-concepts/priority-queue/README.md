# The PriorityQueue Class

## Goal

Learn that `PriorityQueue` orders its elements by a `Comparator` rather than by insertion order, and that the only reliable way to read them out in that order is repeated `poll()` — never iteration.

## Prerequisites

- Basic Java syntax
- Familiarity with `Comparator`
- Familiarity with `Queue`

## Task

`Task` is a record holding a task's `name` and its `priority` number, where a lower number means more urgent.

`TaskScheduler` wraps a `PriorityQueue<Task>` built with `Comparator.comparingInt(Task::priority)`, making it a min-heap by priority number. You'll implement submitting a task, taking the most urgent one, looking at the most urgent one without taking it, and draining every remaining task in priority order.

## Instructions

Complete the following TODOs in `TaskScheduler`:

- TODO-00: Implement `submit()` so a new task enters the queue.
- TODO-01: Implement `takeNext()`, removing and returning the most urgent task, or `null` when none remain.
- TODO-02: Implement `peekNext()`, returning the most urgent task without removing it, or `null` when none remain.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/priority-queue test
```

Or from the lab directory:

```bash
cd java-concepts/priority-queue
mvn test
```

## Bonus (Optional)

- TODO-03 (optional): Implement `drainInPriorityOrder()`, returning a `List<Task>` of every remaining task in ascending priority order and leaving the scheduler empty. Poll repeatedly until nothing is left — copying the queue's iterator into a list does *not* give you priority order.
