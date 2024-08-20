package pro.sky.streamapioptional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pro.sky.streamapioptional.service.DepartmentService;
import pro.sky.streamapioptional.service.EmployeeService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnEmployeesInDepartment() {
        Employee employee1 = new Employee("Test1", "Testovich1", 50000, 1);
        Employee employee2 = new Employee("Test2", "Testovich2", 60000, 1);
        when(employeeService.getAllEmployees()).thenReturn(Map.of(
                employee1.getFullname(), employee1,
                employee2.getFullname(), employee2
        ));

        List<Employee> employees = departmentService.getEmployeesInDepartment(1);
        assertEquals(2, employees.size());
        assertTrue(employees.contains(employee1));
        assertTrue(employees.contains(employee2));
    }
    @Test
    void shouldReturnEmptyListForNonExistingDepartment() {
        when(employeeService.getAllEmployees()).thenReturn(Collections.emptyMap());

        List<Employee> employees = departmentService.getEmployeesInDepartment(6);
        assertTrue(employees.isEmpty());
    }
    @Test
    void shouldReturnSalarySumForDepartment() {
        Employee employee1 = new Employee("Test1", "Testovich1", 50000, 1);
        Employee employee2 = new Employee("Test2", "Testovich2", 60000, 1);
        when(employeeService.getAllEmployees()).thenReturn(Map.of(
                employee1.getFullname(), employee1,
                employee2.getFullname(), employee2
        ));

        double salarySum = departmentService.getDepartmentSalarySum(1);
        assertEquals(110000, salarySum);
    }
    @Test
    void shouldReturnMinSalaryForDepartment() {
        Employee employee3 = new Employee("Test5", "Testovich5", 5000000, 1);
        Employee employee1 = new Employee("Test1", "Testovich1", 50000, 1);
        Employee employee2 = new Employee("Test2", "Testovich2", 60000, 1);
        when(employeeService.getAllEmployees()).thenReturn(Map.of(
                employee1.getFullname(), employee1,
                employee2.getFullname(), employee2,
                employee3.getFullname(), employee3
        ));

        double result = departmentService.getDepartmentMinSalary(1);

        assertEquals(50000, result);
    }
}
