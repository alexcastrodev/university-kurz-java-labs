import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class ComponentScanner {

    public <T> List<Class<? extends T>> findImplementations(List<Class<?>> candidates, Class<T> targetInterface) {
        // TODO-00: Return every class in `candidates` that implements `targetInterface`
        // (and isn't `targetInterface` itself). Preserve the input order.
        // Hint: Class.isAssignableFrom() tells you if one type can be assigned from another.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public List<Class<?>> findAnnotated(List<Class<?>> candidates, Class<? extends Annotation> annotationType) {
        // TODO-01: Return every class in `candidates` annotated with `annotationType`.
        // Preserve the input order.
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public <T> List<T> instantiateAll(List<Class<? extends T>> classes) throws ReflectiveOperationException {
        // TODO-02: Create one instance of each class in `classes` using its no-arg
        // constructor, and return the instances in the same order.
        // Hint: Class.getDeclaredConstructor().newInstance().
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public <T> List<Class<? extends T>> findAnnotatedImplementations(
            List<Class<?>> candidates, Class<T> targetInterface, Class<? extends Annotation> annotationType) {
        // TODO-03 (optional): Return every class in `candidates` that BOTH implements
        // `targetInterface` AND is annotated with `annotationType`.
        // Hint: you can build this out of findImplementations and findAnnotated.
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
