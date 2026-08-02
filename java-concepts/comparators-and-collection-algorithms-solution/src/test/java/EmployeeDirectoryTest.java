import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("EmployeeDirectory")
class EmployeeDirectoryTest {

    @Test
    @DisplayName("should sort by last name, then first name on ties")
    void shouldSortByLastThenFirst() {
        EmployeeDirectory directory = new EmployeeDirectory();
        directory.add(new Employee("Bruno", "Silva", 3000));
        directory.add(new Employee("Ana", "Silva", 3200));
        directory.add(new Employee("Carla", "Alves", 2800));

        List<Employee> sorted = directory.sortedByLastThenFirst();

        assertEquals(List.of("Alves", "Silva", "Silva"),
            sorted.stream().map(Employee::lastName).toList());
        assertEquals(List.of("Carla", "Ana", "Bruno"),
            sorted.stream().map(Employee::firstName).toList());
    }

    @Test
    @DisplayName("should sort by salary, highest first")
    void shouldSortBySalaryDescending() {
        EmployeeDirectory directory = new EmployeeDirectory();
        directory.add(new Employee("Ana", "Silva", 3200));
        directory.add(new Employee("Bruno", "Costa", 5000));
        directory.add(new Employee("Carla", "Alves", 2800));

        List<Employee> sorted = directory.sortedBySalaryDescending();

        assertEquals(List.of(5000.0, 3200.0, 2800.0),
            sorted.stream().map(Employee::salary).toList());
    }

    @Test
    @DisplayName("should not mutate the internal order when sorting")
    void shouldNotMutateInternalOrderWhenSorting() {
        EmployeeDirectory directory = new EmployeeDirectory();
        directory.add(new Employee("Bruno", "Zeta", 3000));
        directory.add(new Employee("Ana", "Alves", 3200));

        directory.sortedByLastThenFirst();
        List<Employee> view = directory.unmodifiableView();

        assertEquals(List.of("Zeta", "Alves"),
            view.stream().map(Employee::lastName).toList());
    }

    @Test
    @DisplayName("should return a live view that reflects later additions")
    void shouldReturnLiveUnmodifiableView() {
        EmployeeDirectory directory = new EmployeeDirectory();
        directory.add(new Employee("Ana", "Silva", 3200));

        List<Employee> view = directory.unmodifiableView();
        assertEquals(1, view.size());

        directory.add(new Employee("Bruno", "Costa", 5000));

        assertEquals(2, view.size());
    }

    @Test
    @DisplayName("should throw when modifying the unmodifiable view")
    void shouldThrowWhenModifyingView() {
        EmployeeDirectory directory = new EmployeeDirectory();
        directory.add(new Employee("Ana", "Silva", 3200));

        List<Employee> view = directory.unmodifiableView();

        assertThrows(UnsupportedOperationException.class,
            () -> view.add(new Employee("Bruno", "Costa", 5000)));
    }

    @Test
    @DisplayName("should return the highest-paid employee")
    void shouldReturnHighestPaidEmployee() {
        EmployeeDirectory directory = new EmployeeDirectory();
        directory.add(new Employee("Ana", "Silva", 3200));
        directory.add(new Employee("Bruno", "Costa", 5000));

        assertEquals("Bruno", directory.highestPaid().firstName());
    }

    @Test
    @DisplayName("should throw when finding the highest paid in an empty directory")
    void shouldThrowWhenDirectoryIsEmpty() {
        EmployeeDirectory directory = new EmployeeDirectory();

        assertThrows(NoSuchElementException.class, directory::highestPaid);
    }

    @Test
    @DisplayName("should return the lowest-paid employee (bonus)")
    void shouldReturnLowestPaidEmployee() {
        EmployeeDirectory directory = new EmployeeDirectory();
        directory.add(new Employee("Ana", "Silva", 3200));
        directory.add(new Employee("Bruno", "Costa", 5000));

        assertEquals("Ana", directory.lowestPaid().firstName());
    }
}
