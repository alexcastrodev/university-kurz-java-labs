import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlaylistManager {

    private final List<String> songs = new ArrayList<>();

    public void add(String song) {
        songs.add(song);
    }

    public List<String> songs() {
        return songs;
    }

    public void insertAt(int index, String song) {
        // TODO-00: Insert song at the given index, shifting later songs back.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public int firstIndexOf(String song) {
        // TODO-01: Return the first index of song, or -1 if it isn't in the playlist.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public List<String> nextUp(int fromIndex, int toIndex) {
        // TODO-02: Return a VIEW (not a copy) of the songs between fromIndex (inclusive)
        // and toIndex (exclusive) — changes through this view must be visible in songs().
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void sortAlphabetically() {
        // TODO-03: Sort the songs in place, case-insensitively.
        // Hint: String has a ready-made case-insensitive Comparator constant.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void shout() {
        // TODO-04 (optional): Uppercase every song title in place, using a single List method.
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
