import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

public class FileVault {

    public void writeText(Path file, String content) throws IOException {
        Files.writeString(file, content);
    }

    public String readText(Path file) throws IOException {
        return Files.readString(file);
    }

    public List<String> listFileNames(Path directory) throws IOException {
        try (Stream<Path> entries = Files.list(directory)) {
            return entries.filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .sorted()
                    .toList();
        }
    }

    public void copy(Path source, Path target) throws IOException {
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
    }

    public void deleteIfPresent(Path file) throws IOException {
        Files.deleteIfExists(file);
    }
}
