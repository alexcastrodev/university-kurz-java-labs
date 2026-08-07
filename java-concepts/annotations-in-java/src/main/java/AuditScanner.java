import java.lang.reflect.Method;
import java.util.List;
import java.util.NoSuchElementException;

public class AuditScanner {

    public List<String> findImportantMethods(Class<?> type) {
        // TODO-00: Return the names of every method in `type` annotated with @Important,
        // in the order Class.getDeclaredMethods() returns them.
        // Hint: Method.isAnnotationPresent(Class) tells you whether an annotation is there.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public String reasonFor(Class<?> type, String methodName) {
        // TODO-01: Return the @Important annotation's value() for the method named
        // `methodName` declared on `type`.
        // Throw new NoSuchElementException(methodName) if that method isn't annotated
        // with @Important (or doesn't exist as a declared method of `type`).
        // Hint: Method.getAnnotation(Important.class) gives you the annotation instance.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public int countImportant(Class<?> type) {
        // TODO-02: Return how many declared methods of `type` are annotated with @Important.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public List<String> findImportantMethodsSorted(Class<?> type) {
        // TODO-03 (optional): Same as findImportantMethods, but alphabetically sorted.
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
