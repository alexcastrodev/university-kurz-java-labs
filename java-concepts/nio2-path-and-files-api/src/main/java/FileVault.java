import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

public class FileVault {

    public void writeText(Path file, String content) throws IOException {
        // TODO-00: Write `content` to `file`, creating it if necessary.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public String readText(Path file) throws IOException {
        // TODO-01: Read and return the full text content of `file`.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public List<String> listFileNames(Path directory) throws IOException {
        // TODO-02: Return the names (not full paths) of every regular file directly
        // inside `directory`, sorted alphabetically. Do not recurse into subdirectories.
        // Hint: Files.list(directory) returns a Stream<Path> that MUST be closed —
        // use try-with-resources on it.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void copy(Path source, Path target) throws IOException {
        // TODO-03: Copy `source` to `target`, overwriting `target` if it already exists.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void deleteIfPresent(Path file) throws IOException {
        // TODO-04 (optional): Delete `file` if it exists; do nothing (no exception) if it doesn't.
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
