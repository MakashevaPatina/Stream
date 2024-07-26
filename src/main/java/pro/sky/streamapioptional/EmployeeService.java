package pro.sky.streamapioptional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.streamapioptional.exception.EmployeeAlreadyAddedException;
import pro.sky.streamapioptional.exception.EmployeeNotFoundException;
import pro.sky.streamapioptional.exception.EmployeeStorageFullException;
import pro.sky.streamapioptional.exception.InvalidEmployeeDataException;

import java.util.*;

@Service
public class EmployeeService {
    private static final int maxEmployees = 10;
    private Map<String, Employee> employees = new HashMap<>();

    public EmployeeService() throws EmployeeStorageFullException {
        addEmployee(new Employee("Leo", "Tolstoy", 100000, 1));
        addEmployee(new Employee("Fyodor", "Dostoevsky", 150000, 2));
        addEmployee(new Employee("Anton", "Chekhov", 170000, 3));
        addEmployee(new Employee("Ernest", "Hemingway", 180000, 4));
        addEmployee(new Employee("George", "Orwell", 145000, 5));
        addEmployee(new Employee("Margaret", "Atwood", 178000, 1));
        addEmployee(new Employee("Harper", "Lee", 199000, 2));
        addEmployee(new Employee("Haruki", "Murakami", 122000, 3));
        addEmployee(new Employee("Franz", "Kafka", 111000, 4));
    }

    public void addEmployee(Employee employee) throws EmployeeStorageFullException {
        validateEmployeeData(employee.getFirstName(), employee.getLastName());
        if (employees.size() >= maxEmployees) {
            throw new EmployeeStorageFullException("Превышен лимит количества сотрудников.");
        }
        if (employees.containsKey(employee.getFullname())) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен.");
        }
        employees.put(employee.getFullname(), employee);
    }

    public Employee removeEmployee(String firstName, String lastName) {
        validateEmployeeData(firstName, lastName);
        Employee employee = new Employee(firstName, lastName, 0, 0);
        employees.remove(employee.getFullname(), employee);
        return employee;
    }

    public Employee findEmployee(String firstName, String lastName) {
        validateEmployeeData(firstName, lastName);
        Employee employee = new Employee(firstName, lastName, 0, 0);
        if (employees.containsKey(employee.getFullname())) {
            return employees.get(employee.getFullname());
        }
        throw new EmployeeNotFoundException("Сотрудник не найден.");
    }

    public Map<String, Employee> getAllEmployees() {
        return new HashMap<>(employees);
    }

    private void validateEmployeeData(String firstName, String lastName) {
        if (StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName)) {
            throw new InvalidEmployeeDataException("Имя и фамилия сотрудника не должны быть пустыми или содержать только пробелы.");
        }
    }
}
