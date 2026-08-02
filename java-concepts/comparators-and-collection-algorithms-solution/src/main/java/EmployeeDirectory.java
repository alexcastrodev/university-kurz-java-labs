import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

record Employee(String firstName, String lastName, double salary) {
}

public class EmployeeDirectory {

    private final List<Employee> employees = new ArrayList<>();

    public void add(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> sortedByLastThenFirst() {
        List<Employee> copy = new ArrayList<>(employees);
        copy.sort(Comparator.comparing(Employee::lastName).thenComparing(Employee::firstName));
        return copy;
    }

    public List<Employee> sortedBySalaryDescending() {
        List<Employee> copy = new ArrayList<>(employees);
        copy.sort(Comparator.comparingDouble(Employee::salary).reversed());
        return copy;
    }

    public List<Employee> unmodifiableView() {
        return Collections.unmodifiableList(employees);
    }

    public Employee highestPaid() {
        return Collections.max(employees, Comparator.comparingDouble(Employee::salary));
    }

    public Employee lowestPaid() {
        return Collections.min(employees, Comparator.comparingDouble(Employee::salary));
    }
}
