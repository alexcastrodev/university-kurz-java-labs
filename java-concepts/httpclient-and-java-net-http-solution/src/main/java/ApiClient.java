import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class ApiClient {

    private final HttpClient client = HttpClient.newHttpClient();

    public String get(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .header("Accept", "application/json")
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() < 200 || response.statusCode() > 299) {
            throw new ApiException(response.statusCode());
        }
        return response.body();
    }

    public CompletableFuture<String> getAsync(URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .header("Accept", "application/json")
            .GET()
            .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body);
    }

    public CompletableFuture<String> getAsyncWithFallback(URI uri, String fallback) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .header("Accept", "application/json")
            .GET()
            .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .exceptionally(ex -> fallback);
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
