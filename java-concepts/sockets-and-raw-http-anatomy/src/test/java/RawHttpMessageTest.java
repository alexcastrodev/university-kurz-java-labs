import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RawHttpMessage")
class RawHttpMessageTest {

    private final RawHttpMessage message = new RawHttpMessage();

    @Test
    @DisplayName("should build a request line terminated by CRLF")
    void shouldBuildRequestLineTerminatedByCrlf() {
        assertEquals("GET /users HTTP/1.1\r\n", message.requestLine("GET", "/users"));
    }

    @Test
    @DisplayName("should build a request with headers and a blank line when the body is empty")
    void shouldBuildRequestWithHeadersAndBlankLineWhenBodyIsEmpty() {
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Host", "example.com");

        String request = message.buildRequest("GET", "/users", headers, "");

        assertEquals("GET /users HTTP/1.1\r\n"
                + "Host: example.com\r\n"
                + "\r\n", request);
    }

    @Test
    @DisplayName("should add Content-Length automatically when a body is present")
    void shouldAddContentLengthAutomaticallyWhenBodyIsPresent() {
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Host", "example.com");
        headers.put("Content-Type", "application/json");

        String request = message.buildRequest("POST", "/orders", headers, "{\"x\":1}");

        assertEquals("POST /orders HTTP/1.1\r\n"
                + "Host: example.com\r\n"
                + "Content-Type: application/json\r\n"
                + "Content-Length: 7\r\n"
                + "\r\n"
                + "{\"x\":1}", request);
        assertTrue(request.contains("Content-Length: 7"));
    }

    @Test
    @DisplayName("should parse a header block into a map that preserves order")
    void shouldParseHeaderBlockIntoMapPreservingOrder() {
        String headerBlock = "Host: example.com\r\nContent-Type: text/plain\r\n";

        Map<String, String> headers = message.parseHeaders(headerBlock);

        assertEquals(2, headers.size());
        assertEquals("example.com", headers.get("Host"));
        assertEquals("text/plain", headers.get("Content-Type"));
        assertEquals(List.of("Host", "Content-Type"), List.copyOf(headers.keySet()));
    }

    @Test
    @DisplayName("should extract the method token from a raw request")
    void shouldExtractMethodTokenFromRawRequest() {
        String rawRequest = "POST /orders HTTP/1.1\r\nHost: example.com\r\n\r\n";

        assertEquals("POST", message.parseMethod(rawRequest));
    }

    @Test
    @DisplayName("should round-trip headers through buildRequest and parseHeaders")
    void shouldRoundTripHeadersThroughBuildRequestAndParseHeaders() {
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Host", "example.com");
        headers.put("Accept", "application/json");

        String request = message.buildRequest("GET", "/users", headers, "");

        int headerBlockStart = request.indexOf("\r\n") + 2;
        int headerBlockEnd = request.indexOf("\r\n\r\n") + 2;
        String headerBlock = request.substring(headerBlockStart, headerBlockEnd);

        assertEquals(headers, message.parseHeaders(headerBlock));
    }

    @Test
    @DisplayName("should send a raw request over a real socket and read the raw response back")
    void shouldSendRawRequestOverRealSocket() throws Exception {
        String rawResponse = "HTTP/1.1 200 OK\r\n\r\nhello";

        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Host", "localhost");
        String rawRequest = message.buildRequest("GET", "/ping", headers, "");

        try (ServerSocket serverSocket = new ServerSocket(0)) {
            int port = serverSocket.getLocalPort();

            CompletableFuture<String> serverReceived = new CompletableFuture<>();
            Thread server = new Thread(() -> {
                try (Socket connection = serverSocket.accept()) {
                    InputStream in = connection.getInputStream();
                    byte[] buffer = new byte[8192];
                    int read = in.read(buffer);

                    OutputStream out = connection.getOutputStream();
                    out.write(rawResponse.getBytes(StandardCharsets.UTF_8));
                    out.flush();

                    serverReceived.complete(new String(buffer, 0, read, StandardCharsets.UTF_8));
                } catch (IOException e) {
                    serverReceived.completeExceptionally(e);
                }
            });
            server.setDaemon(true);
            server.start();

            CompletableFuture<String> clientResponse = CompletableFuture.supplyAsync(() -> {
                try {
                    return message.sendOverSocket("localhost", port, rawRequest);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });

            assertEquals(rawResponse, clientResponse.get(5, TimeUnit.SECONDS));
            assertEquals(rawRequest, serverReceived.get(5, TimeUnit.SECONDS));
        }
    }
}
