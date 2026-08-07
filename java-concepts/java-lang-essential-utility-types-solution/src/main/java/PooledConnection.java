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
        return closed;
    }

    @Override
    public void close() {
        closed = true;
    }
}
