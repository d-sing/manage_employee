package org.ds.manage_employee.controller;

import jakarta.websocket.server.PathParam;
import org.apache.coyote.Response;
import org.ds.manage_employee.model.Employee;
import org.ds.manage_employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage-employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/getEmployee/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }


    @PostMapping("/saveEmployee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }
}
