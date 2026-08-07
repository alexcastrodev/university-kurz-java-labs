# The ArrayDeque Class

## Goal

Use `ArrayDeque` as a stack — `push` and `pop` — to solve classic stack-shaped problems like bracket matching and string reversal, then use *both* ends of the same deque at once for a palindrome check.

## Prerequisites

- Basic Java syntax
- Arrays and `char[]` basics

## Task

`DequeToolkit` is a small collection of string utilities, each of which is naturally solved with a deque. You'll implement a bracket matcher that pushes opening brackets and pops them on the matching close, a palindrome check that compares characters by consuming the deque from the front and the back simultaneously, and a reversal that relies on a stack's last-in, first-out order.

## Instructions

Complete the following TODOs in `DequeToolkit`:

- TODO-00: Implement `isBalanced()` using an `ArrayDeque<Character>` as a stack.
- TODO-01: Implement `isPalindrome()` using `pollFirst()` and `pollLast()`.
- TODO-02: Implement `reverse()` using `push()` and `pop()`.

Run the tests until they all pass.

## Running the Lab

From the project root:

```bash
mvn -pl java-concepts/array-deque test
```

Or from the lab directory:

```bash
cd java-concepts/array-deque
mvn test
```

## Bonus (Optional)

- TODO-03 (optional): Implement `rotateLeft()`, rotating the given deque's contents left by `positions` in place — the first `positions` elements move to the end, keeping their relative order.
