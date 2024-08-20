package pro.sky.streamapioptional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.streamapioptional.exception.EmployeeAlreadyAddedException;
import pro.sky.streamapioptional.exception.EmployeeNotFoundException;
import pro.sky.streamapioptional.exception.EmployeeStorageFullException;
import pro.sky.streamapioptional.service.EmployeeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeServiceTest {
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() throws EmployeeStorageFullException {
        EmployeeService employeeService = new EmployeeService();
    }

    @Test
    void shouldAddEmployee() throws EmployeeStorageFullException {
        Employee newEmployee = new Employee("John", "Doe", 50000, 1);
        employeeService.addEmployee(newEmployee);
        assertEquals(newEmployee, employeeService.findEmployee("John", "Doe"));
    }

    @Test
    void shouldThrowExceptionWhenAddingExistingEmployee() {
        Employee newEmployee = new Employee("Leo", "Tolstoy", 100000, 1);
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.addEmployee(newEmployee));
    }

    @Test
    void shouldRemoveEmployeeSuccessfully() {
        employeeService.removeEmployee("Leo", "Tolstoy");
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee("Leo", "Tolstoy"));
    }

    @Test
    void shouldThrowExceptionWhenRemovingNonExistingEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.removeEmployee("test", "testovich"));
    }

    @Test
    void shouldThrowExceptionWhenFindingNonExistingEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee("test", "testovich"));
    }
}
