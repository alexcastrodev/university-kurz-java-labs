import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class ComponentScanner {

    public <T> List<Class<? extends T>> findImplementations(List<Class<?>> candidates, Class<T> targetInterface) {
        List<Class<? extends T>> result = new ArrayList<>();
        for (Class<?> candidate : candidates) {
            if (candidate != targetInterface && targetInterface.isAssignableFrom(candidate)) {
                result.add(candidate.asSubclass(targetInterface));
            }
        }
        return result;
    }

    public List<Class<?>> findAnnotated(List<Class<?>> candidates, Class<? extends Annotation> annotationType) {
        List<Class<?>> result = new ArrayList<>();
        for (Class<?> candidate : candidates) {
            if (candidate.isAnnotationPresent(annotationType)) {
                result.add(candidate);
            }
        }
        return result;
    }

    public <T> List<T> instantiateAll(List<Class<? extends T>> classes) throws ReflectiveOperationException {
        List<T> instances = new ArrayList<>();
        for (Class<? extends T> type : classes) {
            instances.add(type.getDeclaredConstructor().newInstance());
        }
        return instances;
    }

    public <T> List<Class<? extends T>> findAnnotatedImplementations(
            List<Class<?>> candidates, Class<T> targetInterface, Class<? extends Annotation> annotationType) {
        List<Class<? extends T>> implementations = findImplementations(candidates, targetInterface);
        List<Class<?>> annotated = findAnnotated(candidates, annotationType);
        List<Class<? extends T>> result = new ArrayList<>();
        for (Class<? extends T> implementation : implementations) {
            if (annotated.contains(implementation)) {
                result.add(implementation);
            }
        }
        return result;
    }
}
