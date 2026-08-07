import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DequeToolkit")
class DequeToolkitTest {

    private final DequeToolkit toolkit = new DequeToolkit();

    @Test
    @DisplayName("should report balanced when every bracket is properly nested")
    void shouldReportBalancedWhenEveryBracketIsProperlyNested() {
        assertTrue(toolkit.isBalanced("([{}])"));
    }

    @Test
    @DisplayName("should report unbalanced when brackets close in the wrong order")
    void shouldReportUnbalancedWhenBracketsCloseInTheWrongOrder() {
        assertFalse(toolkit.isBalanced("([)]"));
    }

    @Test
    @DisplayName("should report unbalanced when a bracket is never closed")
    void shouldReportUnbalancedWhenABracketIsNeverClosed() {
        assertFalse(toolkit.isBalanced("("));
    }

    @Test
    @DisplayName("should report unbalanced when closing with nothing open")
    void shouldReportUnbalancedWhenClosingWithNothingOpen() {
        assertFalse(toolkit.isBalanced(")"));
    }

    @Test
    @DisplayName("should report balanced for an empty expression")
    void shouldReportBalancedForAnEmptyExpression() {
        assertTrue(toolkit.isBalanced(""));
    }

    @Test
    @DisplayName("should ignore characters that are not brackets")
    void shouldIgnoreCharactersThatAreNotBrackets() {
        assertTrue(toolkit.isBalanced("(a + b) * [c - d]"));
    }

    @Test
    @DisplayName("should report a palindrome when the text reads the same both ways")
    void shouldReportAPalindromeWhenTheTextReadsTheSameBothWays() {
        assertTrue(toolkit.isPalindrome("racecar"));
    }

    @Test
    @DisplayName("should reject text that reads differently backwards")
    void shouldRejectTextThatReadsDifferentlyBackwards() {
        assertFalse(toolkit.isPalindrome("hello"));
    }

    @Test
    @DisplayName("should treat an empty text as a palindrome")
    void shouldTreatAnEmptyTextAsAPalindrome() {
        assertTrue(toolkit.isPalindrome(""));
    }

    @Test
    @DisplayName("should treat a single character as a palindrome")
    void shouldTreatASingleCharacterAsAPalindrome() {
        assertTrue(toolkit.isPalindrome("a"));
    }

    @Test
    @DisplayName("should reverse the characters of the text")
    void shouldReverseTheCharactersOfTheText() {
        assertEquals(List.of('c', 'b', 'a'), toolkit.reverse("abc"));
    }

    @Test
    @DisplayName("should return an empty list when reversing an empty text")
    void shouldReturnAnEmptyListWhenReversingAnEmptyText() {
        assertEquals(List.of(), toolkit.reverse(""));
    }

    @Test
    @DisplayName("should rotate the deque left in place")
    void shouldRotateTheDequeLeftInPlace() {
        Deque<Integer> deque = new ArrayDeque<>(List.of(1, 2, 3, 4, 5));

        toolkit.rotateLeft(deque, 2);

        assertEquals(List.of(3, 4, 5, 1, 2), new ArrayList<>(deque));
    }

    @Test
    @DisplayName("should leave the deque unchanged when rotating by zero")
    void shouldLeaveTheDequeUnchangedWhenRotatingByZero() {
        Deque<Integer> deque = new ArrayDeque<>(List.of(1, 2, 3, 4, 5));

        toolkit.rotateLeft(deque, 0);

        assertEquals(List.of(1, 2, 3, 4, 5), new ArrayList<>(deque));
    }
}
