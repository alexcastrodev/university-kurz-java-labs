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
        songs.add(index, song);
    }

    public int firstIndexOf(String song) {
        return songs.indexOf(song);
    }

    public List<String> nextUp(int fromIndex, int toIndex) {
        return songs.subList(fromIndex, toIndex);
    }

    public void sortAlphabetically() {
        songs.sort(String.CASE_INSENSITIVE_ORDER);
    }

    public void shout() {
        songs.replaceAll(String::toUpperCase);
    }
}
