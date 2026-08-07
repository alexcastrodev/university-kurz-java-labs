import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PlaybackQueue")
class PlaybackQueueTest {

    @Test
    @DisplayName("should add enqueued tracks to the back of the queue")
    void shouldAddEnqueuedTracksToTheBack() {
        PlaybackQueue playback = new PlaybackQueue();
        playback.enqueue("a");
        playback.enqueue("b");
        playback.enqueue("c");

        assertEquals("a", playback.nowPlaying());
        assertEquals(3, playback.size());
    }

    @Test
    @DisplayName("should let playNext jump ahead of everything already queued")
    void shouldLetPlayNextJumpTheLine() {
        PlaybackQueue playback = new PlaybackQueue();
        playback.enqueue("a");
        playback.enqueue("b");

        playback.playNext("urgent");

        assertEquals("urgent", playback.nowPlaying());
        assertEquals(3, playback.size());
    }

    @Test
    @DisplayName("should not remove the track when reading nowPlaying")
    void shouldNotRemoveTrackWhenReadingNowPlaying() {
        PlaybackQueue playback = new PlaybackQueue();
        playback.enqueue("a");

        assertEquals("a", playback.nowPlaying());
        assertEquals("a", playback.nowPlaying());
        assertEquals(1, playback.size());
    }

    @Test
    @DisplayName("should throw from nowPlaying when the queue is empty")
    void shouldThrowFromNowPlayingWhenQueueIsEmpty() {
        PlaybackQueue playback = new PlaybackQueue();

        assertThrows(NoSuchElementException.class, playback::nowPlaying);
    }

    @Test
    @DisplayName("should remove and return the front track when skipping")
    void shouldRemoveAndReturnFrontTrackWhenSkipping() {
        PlaybackQueue playback = new PlaybackQueue();
        playback.enqueue("a");
        playback.enqueue("b");
        playback.enqueue("c");

        assertEquals("a", playback.skip());
        assertEquals("b", playback.nowPlaying());
        assertEquals(2, playback.size());
    }

    @Test
    @DisplayName("should throw from skip when the queue is empty")
    void shouldThrowFromSkipWhenQueueIsEmpty() {
        PlaybackQueue playback = new PlaybackQueue();

        assertThrows(NoSuchElementException.class, playback::skip);
    }

    @Test
    @DisplayName("should play the jumped track first and the rest in first-in, first-out order")
    void shouldPlayJumpedTrackFirstThenFifoOrder() {
        PlaybackQueue playback = new PlaybackQueue();
        playback.enqueue("a");
        playback.enqueue("b");
        playback.playNext("urgent");

        assertEquals("urgent", playback.skip());
        assertEquals("a", playback.skip());
        assertEquals("b", playback.skip());
        assertEquals(0, playback.size());
    }

    @Test
    @DisplayName("should insert a track at an arbitrary position")
    void shouldInsertTrackAtArbitraryPosition() {
        PlaybackQueue playback = new PlaybackQueue();
        playback.enqueue("a");
        playback.enqueue("c");

        playback.insertAt(1, "b");

        assertEquals("a", playback.skip());
        assertEquals("b", playback.skip());
        assertEquals("c", playback.skip());
    }
}
