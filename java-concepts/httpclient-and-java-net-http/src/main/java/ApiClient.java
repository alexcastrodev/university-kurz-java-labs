import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class ApiClient {

    private final HttpClient client = HttpClient.newHttpClient();

    /**
     * Sends a synchronous GET request and returns the response body as a
     * String.
     *
     * @throws ApiException if the response status code is not in the 200-299 range
     */
    public String get(URI uri) throws IOException, InterruptedException {
        // TODO-00: Build a GET HttpRequest for `uri` with an "Accept:
        // application/json" header.
        //
        // TODO-01: Send it synchronously with client.send(...), using
        // HttpResponse.BodyHandlers.ofString(). If the response's status code
        // is not between 200 and 299 (inclusive), throw
        // new ApiException(response.statusCode()). Otherwise return the body.

        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Sends an asynchronous GET request and returns a CompletableFuture of
     * the response body.
     */
    public CompletableFuture<String> getAsync(URI uri) {
        // TODO-02: Build the same kind of GET request as get(), send it with
        // client.sendAsync(...), and map the resulting HttpResponse<String>
        // to just its body.

        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Like getAsync(), but returns `fallback` instead of failing when the
     * request throws (e.g. connection refused).
     */
    public CompletableFuture<String> getAsyncWithFallback(URI uri, String fallback) {
        // TODO-03 (optional): Same as getAsync(), but recover from a failed
        // future with `fallback` instead of letting the exception propagate.
        // Hint: look at CompletableFuture.exceptionally().

        throw new UnsupportedOperationException("Not implemented yet.");
    }
}

class ApiException extends RuntimeException {
    private final int statusCode;

    ApiException(int statusCode) {
        super("unexpected status code: " + statusCode);
        this.statusCode = statusCode;
    }

    int statusCode() {
        return statusCode;
    }
}
