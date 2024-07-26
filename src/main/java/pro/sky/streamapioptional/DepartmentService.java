package pro.sky.streamapioptional;

import org.springframework.stereotype.Service;
import pro.sky.streamapioptional.exception.EmployeeNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee findEmployeeWithMaxSalaryInDepartment(int departmentId) {
        return employeeService.getAllEmployees().values().stream()
                .filter(employee -> employee.isInDepartment(departmentId))
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден в данном отделе"));
    }

    public Employee findEmployeeWithMinSalaryInDepartment(int departmentId) {
        return employeeService.getAllEmployees().values().stream()
                .filter(employee -> employee.isInDepartment(departmentId))
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден в данном отделе"));
    }

    public List<Employee> getEmployeesInDepartment(int departmentId) {
        return employeeService.getAllEmployees().values().stream()
                .filter(employee -> employee.isInDepartment(departmentId))
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> getAllEmployeesByDepartment() {
        return employeeService.getAllEmployees().values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
