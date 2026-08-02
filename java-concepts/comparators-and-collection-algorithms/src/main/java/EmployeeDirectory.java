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

    /**
     * Returns a new list of every employee, sorted by last name, then by
     * first name when last names tie. Does not modify the directory's
     * internal order.
     */
    public List<Employee> sortedByLastThenFirst() {
        // TODO-00: Build a comparator with Comparator.comparing(...) and
        // thenComparing(...), sort a COPY of the employee list (not the
        // internal one), and return it.

        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Returns a new list of every employee, sorted by salary, highest first.
     */
    public List<Employee> sortedBySalaryDescending() {
        // TODO-01: Build a comparator with Comparator.comparingDouble(...),
        // reverse it, sort a copy, and return it.

        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Returns a read-only VIEW backed by the directory's internal list —
     * changes made through add() after this method is called must still be
     * visible through the returned list.
     */
    public List<Employee> unmodifiableView() {
        // TODO-02: Wrap the internal list with the Collections method that
        // returns a read-only view (not a copy).

        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Returns the highest-paid employee.
     *
     * @throws java.util.NoSuchElementException if the directory is empty
     */
    public Employee highestPaid() {
        // TODO-03: Use a Collections static algorithm with a salary
        // comparator to find the highest-paid employee in one call.

        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Returns the lowest-paid employee.
     */
    public Employee lowestPaid() {
        // TODO-04 (optional): Same idea as highestPaid(), the other direction.

        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
