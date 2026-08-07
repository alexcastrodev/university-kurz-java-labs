# The ArrayDeque Class - Solution

## Overview

This is the official solution for the ArrayDeque lab. Three of the four methods use `ArrayDeque` as a plain stack; the palindrome check is the one that earns the "deque" name by consuming both ends at once.

## Key Concepts

### push()/pop(): the stack-oriented aliases

```java
Deque<Character> stack = new ArrayDeque<>();
for (char c : expression.toCharArray()) {
    if (c == '(' || c == '[' || c == '{') {
        stack.push(c);
    } else if (c == ')' || c == ']' || c == '}') {
        if (stack.isEmpty()) {
            return false;
        }
        char open = stack.pop();
        if ((c == ')' && open != '(') || (c == ']' && open != '[') || (c == '}' && open != '{')) {
            return false;
        }
    }
}
return stack.isEmpty();
```

`push()` and `pop()` are `Deque`'s stack-oriented aliases for `addFirst()` and `removeFirst()` — both ends work identically, but the names document that this code is thinking in last-in, first-out terms. Bracket matching is the textbook fit: the most recently opened bracket is always the one that must close next, which is exactly what the head of the deque holds. The final `stack.isEmpty()` catches the leftover case (`"("`), while the `isEmpty()` check inside the loop catches the opposite one (`")"`).

### pollFirst() and pollLast(): using both ends at once

```java
Deque<Character> deque = new ArrayDeque<>();
for (char c : text.toCharArray()) {
    deque.addLast(c);
}
while (deque.size() > 1) {
    if (!deque.pollFirst().equals(deque.pollLast())) {
        return false;
    }
}
return true;
```

A palindrome check needs to compare the first character against the last, then the second against the second-to-last, and so on — that's two moving cursors, not one. A plain stack only exposes one end, so this is where a deque does something a stack cannot: `pollFirst()` and `pollLast()` remove from opposite ends in the same expression. The loop stops at `size() > 1` because a lone middle character has nothing to be compared against. Note the `.equals()` — `pollFirst()` returns a boxed `Character`, so `==` would compare references outside the cache range.

### A stack's reversal for free

```java
Deque<Character> stack = new ArrayDeque<>();
for (char c : text.toCharArray()) {
    stack.push(c);
}
List<Character> result = new ArrayList<>();
while (!stack.isEmpty()) {
    result.add(stack.pop());
}
return result;
```

Reversal is just last-in, first-out applied to every element: push in reading order, pop in the opposite one. `ArrayDeque` is the preferred vehicle for this over the legacy `Stack` class — `Stack` extends `Vector`, so every operation is synchronized (a cost you rarely need) and it inherits index-based `Vector` methods that make no sense on a stack, including an iteration order that runs bottom-to-top rather than pop order. `ArrayDeque` is unsynchronized, backed by a resizable circular array, and generally faster at both ends.

### rotateLeft(): mutating in place (optional)

```java
for (int i = 0; i < positions; i++) {
    deque.offerLast(deque.pollFirst());
}
```

Because both ends are O(1), moving the head to the tail is cheap, and repeating it `positions` times rotates the whole deque. The method returns `void` and mutates the argument, so the caller's deque is the one that changes — with `positions == 0` the loop never runs and the deque is untouched.

## Summary

- `push`/`pop` are just `addFirst`/`removeFirst` under nicer names; use them when the problem is genuinely LIFO.
- Bracket matching and reversal need one end; palindrome checking needs both, which is what makes it a deque problem rather than a stack problem.
- Prefer `ArrayDeque` over the legacy `Stack` — no synchronization overhead, no `Vector` inheritance, and correct pop-order iteration.
- `ArrayDeque` gives O(1) insertion and removal at both ends, so rotations and front-loaded work stay cheap; note that it rejects `null` elements.
