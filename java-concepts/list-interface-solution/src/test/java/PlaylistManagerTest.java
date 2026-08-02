import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PlaylistManager")
class PlaylistManagerTest {

    @Test
    @DisplayName("should insert a song at the given index and shift others")
    void shouldInsertAtGivenIndexAndShiftOthers() {
        PlaylistManager playlist = new PlaylistManager();
        playlist.add("Ann's Song");
        playlist.add("Cid's Song");

        playlist.insertAt(1, "Bob's Song");

        assertEquals(List.of("Ann's Song", "Bob's Song", "Cid's Song"), playlist.songs());
    }

    @Test
    @DisplayName("should return the first index of a song")
    void shouldReturnFirstIndexOfSong() {
        PlaylistManager playlist = new PlaylistManager();
        playlist.add("Ann's Song");
        playlist.add("Bob's Song");

        assertEquals(1, playlist.firstIndexOf("Bob's Song"));
    }

    @Test
    @DisplayName("should return -1 when the song is not found")
    void shouldReturnMinusOneWhenSongNotFound() {
        PlaylistManager playlist = new PlaylistManager();
        playlist.add("Ann's Song");

        assertEquals(-1, playlist.firstIndexOf("Missing Song"));
    }

    @Test
    @DisplayName("should return a view backed by the original list, not a copy")
    void shouldReturnViewNotCopyForNextUp() {
        PlaylistManager playlist = new PlaylistManager();
        playlist.add("Ann's Song");
        playlist.add("Bob's Song");
        playlist.add("Cid's Song");

        List<String> view = playlist.nextUp(1, 3);
        view.set(0, "Replaced Song");

        assertEquals(List.of("Ann's Song", "Replaced Song", "Cid's Song"), playlist.songs());
    }

    @Test
    @DisplayName("should sort songs case-insensitively")
    void shouldSortSongsCaseInsensitively() {
        PlaylistManager playlist = new PlaylistManager();
        playlist.add("cid's Song");
        playlist.add("Ann's Song");
        playlist.add("bob's Song");

        playlist.sortAlphabetically();

        assertEquals(List.of("Ann's Song", "bob's Song", "cid's Song"), playlist.songs());
    }

    @Test
    @DisplayName("should mutate the manager's own list in place when sorting")
    void shouldMutateOriginalListWhenSortingInPlace() {
        PlaylistManager playlist = new PlaylistManager();
        playlist.add("Bob's Song");
        playlist.add("Ann's Song");

        List<String> reference = playlist.songs();
        playlist.sortAlphabetically();

        assertEquals("Ann's Song", reference.get(0));
    }
}
