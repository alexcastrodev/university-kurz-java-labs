import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CommandHistory")
class CommandHistoryTest {

    @Test
    @DisplayName("should start empty")
    void shouldStartEmpty() {
        CommandHistory history = new CommandHistory(3);

        assertEquals(0, history.size());
    }

    @Test
    @DisplayName("should record commands and list them most recent first")
    void shouldRecordCommandsInOrder() {
        CommandHistory history = new CommandHistory(3);
        history.record("open file");
        history.record("edit line");
        history.record("save file");

        assertEquals(List.of("save file", "edit line", "open file"), history.mostRecentFirst());
    }

    @Test
    @DisplayName("should evict the oldest command when capacity is exceeded")
    void shouldEvictOldestCommandWhenCapacityExceeded() {
        CommandHistory history = new CommandHistory(2);
        history.record("open file");
        history.record("edit line");
        history.record("save file");

        assertEquals(2, history.size());
        assertEquals(List.of("save file", "edit line"), history.mostRecentFirst());
    }

    @Test
    @DisplayName("should return null when undoing an empty history")
    void shouldReturnNullWhenUndoingEmptyHistory() {
        CommandHistory history = new CommandHistory(3);

        assertNull(history.undoLast());
    }

    @Test
    @DisplayName("should remove and return the most recent command on undo")
    void shouldRemoveMostRecentCommandOnUndo() {
        CommandHistory history = new CommandHistory(3);
        history.record("open file");
        history.record("edit line");

        assertEquals("edit line", history.undoLast());
        assertEquals(1, history.size());
    }

    @Test
    @DisplayName("should not remove anything when peeking")
    void shouldNotRemoveOnPeek() {
        CommandHistory history = new CommandHistory(3);
        history.record("open file");

        assertEquals("open file", history.peekMostRecent());
        assertEquals(1, history.size());
    }

    @Test
    @DisplayName("should return null when peeking an empty history")
    void shouldReturnNullWhenPeekingEmptyHistory() {
        CommandHistory history = new CommandHistory(3);

        assertNull(history.peekMostRecent());
    }

    @Test
    @DisplayName("should return commands oldest first (bonus)")
    void shouldReturnCommandsOldestFirst() {
        CommandHistory history = new CommandHistory(3);
        history.record("open file");
        history.record("edit line");

        assertEquals(List.of("open file", "edit line"), history.oldestFirst());
    }
}
