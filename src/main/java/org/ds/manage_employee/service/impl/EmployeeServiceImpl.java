package org.ds.manage_employee.service.impl;

import org.ds.manage_employee.dao.EmployeeDao;
import org.ds.manage_employee.model.Employee;
import org.ds.manage_employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;


    public Employee getEmployeeById(int employeeId){
       return employeeDao.getEmployeeById(employeeId);
    }


    public ResponseEntity<Employee> saveEmployee(Employee employee){
       Employee emp= employeeDao.saveEmployee(employee);
       return new ResponseEntity<>(emp, HttpStatus.CREATED);
    }
}
