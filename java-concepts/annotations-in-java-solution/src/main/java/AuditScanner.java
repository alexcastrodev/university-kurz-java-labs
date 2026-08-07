import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AuditScanner {

    public List<String> findImportantMethods(Class<?> type) {
        List<String> names = new ArrayList<>();
        for (Method method : type.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Important.class)) {
                names.add(method.getName());
            }
        }
        return names;
    }

    public String reasonFor(Class<?> type, String methodName) {
        for (Method method : type.getDeclaredMethods()) {
            if (method.getName().equals(methodName) && method.isAnnotationPresent(Important.class)) {
                return method.getAnnotation(Important.class).value();
            }
        }
        throw new NoSuchElementException(methodName);
    }

    public int countImportant(Class<?> type) {
        return findImportantMethods(type).size();
    }

    public List<String> findImportantMethodsSorted(Class<?> type) {
        return findImportantMethods(type).stream().sorted().toList();
    }
}
