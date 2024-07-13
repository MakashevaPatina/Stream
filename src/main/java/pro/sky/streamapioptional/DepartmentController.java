package pro.sky.streamapioptional;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee getEmployeeWithMaxSalaryInDepartment(
            @RequestParam("departmentId") int departmentId) {
        return departmentService.findEmployeeWithMaxSalaryInDepartment(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee getEmployeeWithMinSalaryInDepartment(
            @RequestParam("departmentId") int departmentId) {
        return departmentService.findEmployeeWithMinSalaryInDepartment(departmentId);
    }

    @GetMapping("/all")
    public List<Employee> getEmployeesInDepartment(
            @RequestParam("departmentId") int departmentId) {
        return departmentService.getEmployeesInDepartment(departmentId);
    }

    @GetMapping("/all-by-department")
    public Map<Integer, List<Employee>> getAllEmployeesByDepartment() {
        return departmentService.getAllEmployeesByDepartment();
    }

}
