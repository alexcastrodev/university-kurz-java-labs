import java.util.LinkedList;

public class PlaybackQueue {

    private final LinkedList<String> queue = new LinkedList<>();

    public void enqueue(String track) {
        queue.addLast(track);
    }

    public void playNext(String track) {
        queue.addFirst(track);
    }

    public String nowPlaying() {
        return queue.getFirst();
    }

    public String skip() {
        return queue.removeFirst();
    }

    public void insertAt(int index, String track) {
        queue.add(index, track);
    }

    public int size() {
        return queue.size();
    }
}
