import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ComponentScanner")
class ComponentScannerTest {

    private static final List<Class<?>> CANDIDATES =
            List.of(EmailNotifier.class, SmsNotifier.class, LegacyHelper.class);

    @Test
    @DisplayName("should find every candidate implementing the target interface")
    void shouldFindEveryCandidateImplementingTargetInterface() {
        ComponentScanner scanner = new ComponentScanner();

        List<Class<? extends Notifier>> found = scanner.findImplementations(CANDIDATES, Notifier.class);

        assertEquals(List.of(EmailNotifier.class, SmsNotifier.class), found);
    }

    @Test
    @DisplayName("should find every candidate carrying the annotation")
    void shouldFindEveryCandidateCarryingTheAnnotation() {
        ComponentScanner scanner = new ComponentScanner();

        List<Class<?>> found = scanner.findAnnotated(CANDIDATES, Component.class);

        assertEquals(List.of(EmailNotifier.class, LegacyHelper.class), found);
    }

    @Test
    @DisplayName("should instantiate every discovered implementation via its no-arg constructor")
    void shouldInstantiateEveryDiscoveredImplementation() throws ReflectiveOperationException {
        ComponentScanner scanner = new ComponentScanner();

        List<Class<? extends Notifier>> found = scanner.findImplementations(CANDIDATES, Notifier.class);
        List<Notifier> instances = scanner.instantiateAll(found);

        assertEquals(2, instances.size());
        assertEquals("email: hi", instances.get(0).notify("hi"));
        assertEquals("sms: hi", instances.get(1).notify("hi"));
    }

    @Test
    @DisplayName("should find only candidates matching both the interface and the annotation")
    void shouldFindOnlyCandidatesMatchingBothConditions() {
        ComponentScanner scanner = new ComponentScanner();

        List<Class<? extends Notifier>> found =
                scanner.findAnnotatedImplementations(CANDIDATES, Notifier.class, Component.class);

        assertEquals(List.of(EmailNotifier.class), found);
    }
}
