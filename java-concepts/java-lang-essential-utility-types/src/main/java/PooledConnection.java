public class PooledConnection implements AutoCloseable {

    private final String id;
    private boolean closed = false;

    public PooledConnection(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

    public boolean isClosed() {
        // TODO-01: Return whether this connection has been closed.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public void close() {
        // TODO-02: Mark this connection as closed. Closing an already-closed
        // connection must be safe (no exception) — calling close() twice is fine.
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
