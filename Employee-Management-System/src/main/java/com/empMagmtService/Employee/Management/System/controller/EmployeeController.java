package com.empMagmtService.Employee.Management.System.controller;

import com.empMagmtService.Employee.Management.System.model.Employee;
import com.empMagmtService.Employee.Management.System.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> fetchAllEmployeeData() {
        List<Employee> listEmployee = employeeService.getAllEmployees();
        return listEmployee.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listEmployee);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployeeRecord(@Valid @RequestBody Employee employee) {
        Employee savedEmp = employeeService.createEmployee(employee);
        return savedEmp != null ? ResponseEntity.status(HttpStatus.CREATED).body(savedEmp)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return Optional.ofNullable(employeeService.getEmployeeById(id)).get()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployeeRecord(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.updateEmplyee(id, employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }
}
