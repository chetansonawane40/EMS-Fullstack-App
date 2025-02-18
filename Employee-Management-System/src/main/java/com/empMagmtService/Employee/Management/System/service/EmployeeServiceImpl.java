package com.empMagmtService.Employee.Management.System.service;

import com.empMagmtService.Employee.Management.System.model.Employee;
import com.empMagmtService.Employee.Management.System.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    //private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee updateEmplyee(Long id, Employee updateEmp) {
        return employeeRepository.findById(id).map(employee ->
        {
            employee.setFirstName(updateEmp.getFirstName());
            employee.setLastName(updateEmp.getLastName());
            employee.setEmailId(updateEmp.getEmailId());
            //employee.setDepartment(updateEmp.getDepartment());
            return employeeRepository.save(employee);
        }).orElseThrow(() -> new RuntimeException("Employee Not Found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployee(Long id) {
        Map<String, String> response = new HashMap<>();

        return employeeRepository.findById(id)
                .map(employee -> {
                    employeeRepository.delete(employee);
                    response.put("message", "Employee deleted successfully!");
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    response.put("error", "Employee not found with ID: " + id);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                });
    }
}
