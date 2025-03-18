package org.ds.manage_employee.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {

    int employeeId;
    String firstName;
    String lastName;
    double salary;
    Integer managerId;

    public Employee(int employeeId, String firstName, String lastName, double salary, Integer managerId) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerId = managerId;
    }
}
