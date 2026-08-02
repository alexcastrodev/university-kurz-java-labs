import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ApiClient")
class ApiClientTest {

    private static HttpServer server;
    private static int port;

    @BeforeAll
    static void startServer() throws Exception {
        server = HttpServer.create(new InetSocketAddress("localhost", 0), 0);
        server.createContext("/ok", exchange -> {
            byte[] body = "{\"status\":\"ok\"}".getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, body.length);
            exchange.getResponseBody().write(body);
            exchange.close();
        });
        server.createContext("/not-found", exchange -> {
            byte[] body = "not found".getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(404, body.length);
            exchange.getResponseBody().write(body);
            exchange.close();
        });
        server.start();
        port = server.getAddress().getPort();
    }

    @AfterAll
    static void stopServer() {
        server.stop(0);
    }

    private URI uri(String path) {
        return URI.create("http://localhost:" + port + path);
    }

    @Test
    @DisplayName("should return the response body for a successful GET")
    void shouldReturnBodyForSuccessfulGet() throws Exception {
        ApiClient client = new ApiClient();

        String body = client.get(uri("/ok"));

        assertEquals("{\"status\":\"ok\"}", body);
    }

    @Test
    @DisplayName("should throw ApiException for a non-2xx response")
    void shouldThrowForNon2xxResponse() {
        ApiClient client = new ApiClient();

        ApiException ex = assertThrows(ApiException.class, () -> client.get(uri("/not-found")));
        assertEquals(404, ex.statusCode());
    }

    @Test
    @DisplayName("should return the response body asynchronously")
    void shouldReturnBodyAsynchronously() throws Exception {
        ApiClient client = new ApiClient();

        String body = client.getAsync(uri("/ok")).get(5, TimeUnit.SECONDS);

        assertEquals("{\"status\":\"ok\"}", body);
    }

    @Test
    @DisplayName("should return the fallback when the request fails (bonus)")
    void shouldReturnFallbackOnFailure() throws Exception {
        HttpServer throwaway = HttpServer.create(new InetSocketAddress("localhost", 0), 0);
        int deadPort = throwaway.getAddress().getPort();
        throwaway.start();
        throwaway.stop(0);

        ApiClient client = new ApiClient();
        URI unreachable = URI.create("http://localhost:" + deadPort + "/nope");

        String body = client.getAsyncWithFallback(unreachable, "fallback").get(5, TimeUnit.SECONDS);

        assertEquals("fallback", body);
    }
}
