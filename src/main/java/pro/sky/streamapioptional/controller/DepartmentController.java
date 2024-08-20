package pro.sky.streamapioptional.controller;


import org.springframework.web.bind.annotation.*;
import pro.sky.streamapioptional.service.DepartmentService;
import pro.sky.streamapioptional.Employee;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesByDepartment(@PathVariable int id) {
        return departmentService.getEmployeesInDepartment(id);
    }

    @GetMapping("/{id}/salary/sum")
    public double getDepartmentSalarySum(@PathVariable int id) {
        return departmentService.getDepartmentSalarySum(id);
    }

    @GetMapping("/{id}/salary/max")
    public double getDepartmentMaxSalary(@PathVariable int id) {
        return departmentService.getDepartmentMaxSalary(id);
    }

    @GetMapping("/{id}/salary/min")
    public double getDepartmentMinSalary(@PathVariable int id) {
        return departmentService.getDepartmentMinSalary(id);
    }

    @GetMapping(path = "/employees")
    public Map<Integer, List<Employee>> getAllEmployeesByDepartment() {
        return departmentService.getAllEmployeesByDepartment();
    }

}
