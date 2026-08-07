import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("AuditScanner")
class AuditScannerTest {

    private final AuditScanner scanner = new AuditScanner();

    @Test
    @DisplayName("should find every method annotated with @Important")
    void shouldFindEveryMethodAnnotatedWithImportant() {
        List<String> result = scanner.findImportantMethods(SampleService.class);

        // Class.getDeclaredMethods() makes no ordering guarantee, so this asserts on
        // the set of names plus the size instead of an exact declaration-order list.
        assertEquals(Set.of("flushBuffers", "validateState", "deleteAll"), Set.copyOf(result));
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("should skip methods without @Important")
    void shouldSkipMethodsWithoutImportant() {
        List<String> result = scanner.findImportantMethods(SampleService.class);

        assertFalse(result.contains("logHeartbeat"));
    }

    @Test
    @DisplayName("should return the annotation value as the reason")
    void shouldReturnTheAnnotationValueAsTheReason() {
        assertEquals("must run before shutdown", scanner.reasonFor(SampleService.class, "flushBuffers"));
        assertEquals("audited operation", scanner.reasonFor(SampleService.class, "deleteAll"));
    }

    @Test
    @DisplayName("should return the empty default when the annotation has no value")
    void shouldReturnTheEmptyDefaultWhenTheAnnotationHasNoValue() {
        assertEquals("", scanner.reasonFor(SampleService.class, "validateState"));
    }

    @Test
    @DisplayName("should throw when the method exists but is not annotated")
    void shouldThrowWhenTheMethodExistsButIsNotAnnotated() {
        assertThrows(NoSuchElementException.class, () -> scanner.reasonFor(SampleService.class, "logHeartbeat"));
    }

    @Test
    @DisplayName("should throw when the method does not exist")
    void shouldThrowWhenTheMethodDoesNotExist() {
        assertThrows(NoSuchElementException.class, () -> scanner.reasonFor(SampleService.class, "doesNotExist"));
    }

    @Test
    @DisplayName("should count the annotated methods")
    void shouldCountTheAnnotatedMethods() {
        assertEquals(3, scanner.countImportant(SampleService.class));
    }

    @Test
    @DisplayName("should return the annotated methods alphabetically sorted")
    void shouldReturnTheAnnotatedMethodsAlphabeticallySorted() {
        assertEquals(
                List.of("deleteAll", "flushBuffers", "validateState"),
                scanner.findImportantMethodsSorted(SampleService.class));
    }
}
