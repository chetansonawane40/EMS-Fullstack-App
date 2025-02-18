package com.empMagmtService.Employee.Management.System.service;

import com.empMagmtService.Employee.Management.System.model.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmployeeService {

    public List<Employee> getAllEmployees();
    public Employee createEmployee(Employee employee);
    public Optional<Employee> getEmployeeById(Long id);
    public Employee updateEmplyee(Long id, Employee employee);
    public ResponseEntity<Map<String, String>> deleteEmployee(Long id);

}
