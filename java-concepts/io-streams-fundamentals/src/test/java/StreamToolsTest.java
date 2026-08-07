import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("StreamTools")
class StreamToolsTest {

    private final StreamTools tools = new StreamTools();

    @Test
    @DisplayName("should count every line available from the reader")
    void shouldCountEveryLineAvailableFromTheReader() throws IOException {
        StringReader reader = new StringReader("a\nb\nc");

        assertEquals(3, tools.countLines(reader));
    }

    @Test
    @DisplayName("should count zero lines when the reader is empty")
    void shouldCountZeroLinesWhenTheReaderIsEmpty() throws IOException {
        StringReader reader = new StringReader("");

        assertEquals(0, tools.countLines(reader));
    }

    @Test
    @DisplayName("should copy every byte from the input stream to the output stream")
    void shouldCopyEveryByteFromInputToOutput() throws IOException {
        byte[] original = "hello streams".getBytes(StandardCharsets.UTF_8);
        ByteArrayInputStream in = new ByteArrayInputStream(original);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        tools.copy(in, out);

        assertArrayEquals(original, out.toByteArray());
    }

    @Test
    @DisplayName("should decode every byte of the input stream as UTF-8 text")
    void shouldDecodeEveryByteAsUtf8Text() throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream("olá, mundo".getBytes(StandardCharsets.UTF_8));

        assertEquals("olá, mundo", tools.readAllText(in));
    }

    @Test
    @DisplayName("should write lines that can be read back unchanged")
    void shouldWriteLinesThatCanBeReadBackUnchanged() throws IOException {
        List<String> lines = List.of("first", "second", "third");
        StringWriter writer = new StringWriter();

        tools.writeLines(writer, lines);

        assertEquals(lines, readBack(writer.toString()));
    }

    private List<String> readBack(String content) throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader(content));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }
}
