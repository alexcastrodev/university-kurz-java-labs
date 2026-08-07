import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class RawHttpMessage {

    public String requestLine(String method, String path) {
        return method + " " + path + " HTTP/1.1\r\n";
    }

    public String buildRequest(String method, String path, Map<String, String> headers, String body) {
        StringBuilder sb = new StringBuilder();
        sb.append(requestLine(method, path));

        Map<String, String> allHeaders = new LinkedHashMap<>(headers);
        if (body != null && !body.isEmpty()) {
            allHeaders.put("Content-Length", String.valueOf(body.getBytes(StandardCharsets.UTF_8).length));
        }

        for (Map.Entry<String, String> header : allHeaders.entrySet()) {
            sb.append(header.getKey()).append(": ").append(header.getValue()).append("\r\n");
        }

        sb.append("\r\n");

        if (body != null && !body.isEmpty()) {
            sb.append(body);
        }

        return sb.toString();
    }

    public Map<String, String> parseHeaders(String rawHeaderBlock) {
        Map<String, String> result = new LinkedHashMap<>();
        for (String line : rawHeaderBlock.split("\r\n")) {
            if (line.isBlank()) {
                continue;
            }
            int colon = line.indexOf(':');
            String name = line.substring(0, colon);
            String value = line.substring(colon + 1).trim();
            result.put(name, value);
        }
        return result;
    }

    public String parseMethod(String rawRequest) {
        return rawRequest.substring(0, rawRequest.indexOf(' '));
    }

    public String sendOverSocket(String host, int port, String rawRequest) throws IOException {
        try (Socket socket = new Socket(host, port)) {
            OutputStream out = socket.getOutputStream();
            out.write(rawRequest.getBytes(StandardCharsets.UTF_8));
            out.flush();

            InputStream in = socket.getInputStream();
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
