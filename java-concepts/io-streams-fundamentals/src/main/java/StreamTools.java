import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class StreamTools {

    public int countLines(Reader reader) throws IOException {
        // TODO-00: Count how many lines are available from `reader`.
        // Hint: wrap it in a BufferedReader and call readLine() in a loop until null.
        // Do NOT close `reader` — the caller owns it and may still need it.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void copy(InputStream in, OutputStream out) throws IOException {
        // TODO-01: Copy every byte from `in` to `out`.
        // Hint: InputStream has a method that does exactly this in one call (JDK 9+).
        // Do NOT close either stream — the caller owns them.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public String readAllText(InputStream in) throws IOException {
        // TODO-02: Read every byte from `in` and decode it as UTF-8 text.
        // Do NOT close `in`.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void writeLines(Writer writer, List<String> lines) throws IOException {
        // TODO-03: Write every line to `writer`, each followed by a line separator,
        // so that reading them back later (e.g. with BufferedReader.readLine()) reproduces
        // the original lines. Do NOT close `writer`.
        // Hint: wrapping in a BufferedWriter gives you newLine() for a platform-correct separator.
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
