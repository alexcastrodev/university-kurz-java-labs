import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Playlist")
class PlaylistTest {

    @Test
    @DisplayName("should append tracks in insertion order")
    void shouldAppendTracksInInsertionOrder() {
        Playlist playlist = new Playlist();

        playlist.addTrack("a");
        playlist.addTrack("b");
        playlist.addTrack("c");

        assertEquals("a", playlist.trackAt(0));
        assertEquals("b", playlist.trackAt(1));
        assertEquals("c", playlist.trackAt(2));
        assertEquals(3, playlist.size());
    }

    @Test
    @DisplayName("should shift later tracks when inserting in the middle")
    void shouldShiftLaterTracksWhenInsertingInTheMiddle() {
        Playlist playlist = new Playlist();
        playlist.addTrack("a");
        playlist.addTrack("b");
        playlist.addTrack("d");

        playlist.insertAt(2, "c");

        assertEquals("a", playlist.trackAt(0));
        assertEquals("b", playlist.trackAt(1));
        assertEquals("c", playlist.trackAt(2));
        assertEquals("d", playlist.trackAt(3));
        assertEquals(4, playlist.size());
    }

    @Test
    @DisplayName("should shift later tracks back when removing by index")
    void shouldShiftLaterTracksBackWhenRemovingByIndex() {
        Playlist playlist = new Playlist();
        playlist.addTrack("a");
        playlist.addTrack("b");
        playlist.addTrack("c");
        playlist.addTrack("d");

        playlist.removeAt(1);

        assertEquals("a", playlist.trackAt(0));
        assertEquals("c", playlist.trackAt(1));
        assertEquals("d", playlist.trackAt(2));
        assertEquals(3, playlist.size());
    }

    @Test
    @DisplayName("should convert the tracks to a String array in order")
    void shouldConvertTheTracksToAStringArrayInOrder() {
        Playlist playlist = new Playlist();
        playlist.addTrack("x");
        playlist.addTrack("y");
        playlist.addTrack("z");

        assertArrayEquals(new String[]{"x", "y", "z"}, playlist.toArray());
    }

    @Test
    @DisplayName("should return an empty array when the playlist is empty")
    void shouldReturnAnEmptyArrayWhenThePlaylistIsEmpty() {
        Playlist playlist = new Playlist();

        String[] tracks = playlist.toArray();

        assertNotNull(tracks);
        assertEquals(0, tracks.length);
    }

    @Test
    @DisplayName("should keep behaving correctly after preparing for a bulk load")
    void shouldKeepBehavingCorrectlyAfterPreparingForABulkLoad() {
        Playlist playlist = new Playlist();

        assertDoesNotThrow(() -> playlist.prepareForBulkLoad(100));

        playlist.addTrack("a");
        playlist.addTrack("b");
        playlist.addTrack("c");

        assertEquals("a", playlist.trackAt(0));
        assertEquals("b", playlist.trackAt(1));
        assertEquals("c", playlist.trackAt(2));
        assertEquals(3, playlist.size());
    }
}
