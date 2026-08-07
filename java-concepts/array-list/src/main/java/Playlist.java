import java.util.ArrayList;

public class Playlist {

    private final ArrayList<String> tracks = new ArrayList<>();

    public void addTrack(String track) {
        // TODO-00: Append `track` to the end of the playlist.
        tracks.add(track);
    }

    public void insertAt(int index, String track) {
        // TODO-01: Insert `track` at `index`, shifting everything at and after
        // that index one position later.
        tracks.add(index, track);
    }

    public void removeAt(int index) {
        // TODO-02: Remove the track at `index`, shifting everything after it
        // one position earlier.
        tracks.remove(index);
    }

    public int size() {
        return tracks.size();
    }

    public String trackAt(int index) {
        return tracks.get(index);
    }

    public String[] toArray() {
        // TODO-03: Return the tracks as a String[] (not Object[]).
        return tracks.toArray(String[]::new);
    }

    public void prepareForBulkLoad(int expectedTotal) {
        // TODO-04 (optional): Hint to the underlying ArrayList that roughly
        // `expectedTotal` tracks are coming, to avoid repeated reallocation.
        tracks.ensureCapacity(expectedTotal);
    }
}
