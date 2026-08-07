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
        BufferedReader buffered = new BufferedReader(reader);
        int count = 0;
        while (buffered.readLine() != null) {
            count++;
        }
        return count;
    }

    public void copy(InputStream in, OutputStream out) throws IOException {
        in.transferTo(out);
    }

    public String readAllText(InputStream in) throws IOException {
        return new String(in.readAllBytes(), StandardCharsets.UTF_8);
    }

    public void writeLines(Writer writer, List<String> lines) throws IOException {
        BufferedWriter buffered = new BufferedWriter(writer);
        for (String line : lines) {
            buffered.write(line);
            buffered.newLine();
        }
        buffered.flush();
    }
}
