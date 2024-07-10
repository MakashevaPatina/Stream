package pro.sky.streamapioptional;

import org.springframework.web.bind.annotation.*;
import pro.sky.streamapioptional.exception.EmployeeStorageFullException;

import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Employee addEmployee(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("salary") double salary,
            @RequestParam("department") int department) throws EmployeeStorageFullException {
        Employee employee = new Employee(firstName, lastName, salary, department);
        employeeService.addEmployee(employee);
        return employee;
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) {
        Employee employee = new Employee(firstName, lastName, 0, 0);
        employeeService.removeEmployee(firstName, lastName);
        return employee;
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping("/all")
    public Map<String, Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}

