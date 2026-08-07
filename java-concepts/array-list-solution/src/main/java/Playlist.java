import java.util.ArrayList;

public class Playlist {

    private final ArrayList<String> tracks = new ArrayList<>();

    public void addTrack(String track) {
        tracks.add(track);
    }

    public void insertAt(int index, String track) {
        tracks.add(index, track);
    }

    public void removeAt(int index) {
        tracks.remove(index);
    }

    public int size() {
        return tracks.size();
    }

    public String trackAt(int index) {
        return tracks.get(index);
    }

    public String[] toArray() {
        return tracks.toArray(new String[0]);
    }

    public void prepareForBulkLoad(int expectedTotal) {
        tracks.ensureCapacity(expectedTotal);
    }
}
