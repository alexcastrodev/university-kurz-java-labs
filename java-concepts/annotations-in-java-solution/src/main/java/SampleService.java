public class SampleService {

    @Important("must run before shutdown")
    public void flushBuffers() {
    }

    @Important
    public void validateState() {
    }

    public void logHeartbeat() {
    }

    @Important("audited operation")
    public void deleteAll() {
    }
}
