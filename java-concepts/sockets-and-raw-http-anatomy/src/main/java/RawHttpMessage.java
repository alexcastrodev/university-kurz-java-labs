import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class RawHttpMessage {

    public String requestLine(String method, String path) {
        // TODO-00: Build a request line, e.g. "GET /users HTTP/1.1\r\n"
        // (method, a space, path, a space, "HTTP/1.1", then CRLF).
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public String buildRequest(String method, String path, Map<String, String> headers, String body) {
        // TODO-01: Build a full raw HTTP/1.1 request: the request line, then one
        // "Name: value\r\n" line per entry in `headers` (in the order the map iterates),
        // then a blank line ("\r\n") separating headers from the body, then the body
        // itself (if `body` is null or empty, still include the blank line, but no body
        // text after it).
        // If `body` is non-empty, ALSO add a "Content-Length: <byte length of body>"
        // header automatically (computed via body.getBytes(StandardCharsets.UTF_8).length),
        // even if the caller didn't include one in `headers` — real HTTP clients do this
        // for you, so this method should too.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public Map<String, String> parseHeaders(String rawHeaderBlock) {
        // TODO-02: Parse a block of "Name: value\r\n" lines (no request line, no blank
        // line, no body — just the header lines themselves, e.g.
        // "Host: example.com\r\nContent-Type: text/plain\r\n") into a Map preserving
        // insertion order (use LinkedHashMap). Trim any leading space after the colon.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public String parseMethod(String rawRequest) {
        // TODO-03: Given a full raw request (starting with its request line), return
        // just the HTTP method token (e.g. "GET" from "GET /users HTTP/1.1\r\n...").
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public String sendOverSocket(String host, int port, String rawRequest) throws IOException {
        // TODO-04 (optional): Open a real Socket to host:port, write `rawRequest`'s bytes
        // to it, then read and return everything the other end sends back as a String
        // (until the connection closes). Use try-with-resources on the Socket.
        // Hint: socket.getOutputStream().write(rawRequest.getBytes(StandardCharsets.UTF_8));
        // then flush; read the response via socket.getInputStream().readAllBytes().
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
