package org.ds.manage_employee.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Scope;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="employeeId")
    int employeeId;

    @Column(name="firstName")
    String firstName;

    @Column(name="lastName")
    String lastName;

    @Column(name="salary")
    double salary;

    @Column(name="employeeType")
    String employeeType;

}
