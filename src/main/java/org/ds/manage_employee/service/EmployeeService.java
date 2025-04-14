package org.ds.manage_employee.service;

import org.ds.manage_employee.model.Employee;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {

    public Employee getEmployeeById(int id);

    public ResponseEntity<Employee> saveEmployee(Employee employee);
}
