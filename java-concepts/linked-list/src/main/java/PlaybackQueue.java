import java.util.LinkedList;

public class PlaybackQueue {

    private final LinkedList<String> queue = new LinkedList<>();

    public void enqueue(String track) {
        // TODO-00: Add `track` to the back of the queue (normal, wait-your-turn addition).
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void playNext(String track) {
        // TODO-01: Add `track` to the FRONT of the queue, so it plays immediately
        // after whatever's currently playing — jumping ahead of everything already queued.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public String nowPlaying() {
        // TODO-02: Return the track at the front of the queue WITHOUT removing it.
        // Let it throw its natural exception if the queue is empty (don't catch/rethrow).
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public String skip() {
        // TODO-03: Remove and return the track at the front of the queue.
        // Let it throw its natural exception if the queue is empty (don't catch/rethrow).
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void insertAt(int index, String track) {
        // TODO-04 (optional): Insert `track` at the given position in the queue
        // (0 = front), using LinkedList's List-style positional insertion.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public int size() {
        return queue.size();
    }
}
