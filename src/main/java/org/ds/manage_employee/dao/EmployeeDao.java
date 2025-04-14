package org.ds.manage_employee.dao;

import org.ds.manage_employee.model.Employee;

public interface EmployeeDao {

    public Employee getEmployeeById(int id);

    public Employee saveEmployee(Employee employee);
}
