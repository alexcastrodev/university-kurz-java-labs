import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("FileVault")
class FileVaultTest {

    private final FileVault vault = new FileVault();

    @TempDir
    Path tempDir;

    @Test
    @DisplayName("should round-trip the exact content it wrote")
    void shouldRoundTripWrittenContent() throws IOException {
        Path file = tempDir.resolve("notes.txt");

        vault.writeText(file, "hello nio2");

        assertEquals("hello nio2", vault.readText(file));
    }

    @Test
    @DisplayName("should read the content of a pre-existing file")
    void shouldReadPreExistingFile() throws IOException {
        Path file = tempDir.resolve("existing.txt");
        Files.writeString(file, "written without the vault");

        assertEquals("written without the vault", vault.readText(file));
    }

    @Test
    @DisplayName("should list only regular file names, sorted alphabetically")
    void shouldListOnlyRegularFileNamesSorted() throws IOException {
        Files.writeString(tempDir.resolve("charlie.txt"), "c");
        Files.writeString(tempDir.resolve("alpha.txt"), "a");
        Files.writeString(tempDir.resolve("bravo.txt"), "b");
        Files.createDirectory(tempDir.resolve("archive"));

        List<String> names = vault.listFileNames(tempDir);

        assertEquals(List.of("alpha.txt", "bravo.txt", "charlie.txt"), names);
    }

    @Test
    @DisplayName("should copy content to a new target path")
    void shouldCopyContentToNewTarget() throws IOException {
        Path source = tempDir.resolve("source.txt");
        Path target = tempDir.resolve("target.txt");
        Files.writeString(source, "original content");

        vault.copy(source, target);

        assertTrue(Files.exists(target));
        assertEquals("original content", Files.readString(target));
    }

    @Test
    @DisplayName("should overwrite an existing target when copying")
    void shouldOverwriteExistingTargetWhenCopying() throws IOException {
        Path source = tempDir.resolve("source.txt");
        Path target = tempDir.resolve("target.txt");
        Files.writeString(source, "fresh content");
        Files.writeString(target, "stale content");

        vault.copy(source, target);

        assertEquals("fresh content", Files.readString(target));
    }

    @Test
    @DisplayName("should delete a file that exists")
    void shouldDeleteExistingFile() throws IOException {
        Path file = tempDir.resolve("doomed.txt");
        Files.writeString(file, "delete me");

        vault.deleteIfPresent(file);

        assertFalse(Files.exists(file));
    }

    @Test
    @DisplayName("should not throw when deleting a file that does not exist")
    void shouldNotThrowWhenDeletingMissingFile() {
        Path file = tempDir.resolve("never-created.txt");

        assertDoesNotThrow(() -> vault.deleteIfPresent(file));
    }
}
