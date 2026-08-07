import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class DequeToolkit {

    public boolean isBalanced(String expression) {
        // TODO-00: Return whether every '(', '[', '{' in `expression` has a matching
        // closing bracket in the correct order (e.g. "([{}])" is balanced, "([)]" is not,
        // "(" is not). Use an ArrayDeque<Character> as a stack: push each opening
        // bracket; on a closing bracket, pop and check it matches (if the stack is
        // empty or the popped bracket doesn't match, the expression is unbalanced).
        // At the end, the stack must be empty for the expression to be balanced.
        // Ignore any character that isn't one of these six bracket characters.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean isPalindrome(String text) {
        // TODO-01: Return whether `text` reads the same forwards and backwards,
        // using an ArrayDeque<Character> loaded with every character, then comparing
        // by repeatedly polling from BOTH ends at once (pollFirst() and pollLast())
        // until fewer than 2 characters remain.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public List<Character> reverse(String text) {
        // TODO-02: Return the characters of `text`, reversed, as a List<Character>.
        // Use an ArrayDeque<Character> as a stack: push every character in order,
        // then pop them all into the result list (last pushed is first popped).
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void rotateLeft(Deque<Integer> deque, int positions) {
        // TODO-03 (optional): Rotate `deque`'s contents left by `positions` IN PLACE
        // (mutate the deque passed in; don't return a new one) — the first `positions`
        // elements move to the end, in the same relative order.
        // E.g. rotating [1,2,3,4,5] left by 2 produces [3,4,5,1,2].
        // Hint: repeatedly pollFirst() then offerLast() the polled value, `positions` times.
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
