package org.ds.manage_employee.dao.impl;

import jakarta.persistence.EntityManagerFactory;
import org.ds.manage_employee.dao.EmployeeDao;
import org.ds.manage_employee.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public EmployeeDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }

    @Override
    public Employee getEmployeeById(int id) {
        Session session = sessionFactory.openSession();
        Employee employee = session.get(Employee.class, id);
        session.close();
        return employee;
    }

    @Override
    public Employee saveEmployee(Employee employee){
        Session session = sessionFactory.openSession();
        Transaction txn = session.beginTransaction();

        session.save(employee);

        txn.commit();
        session.close();
        return employee;
    }
}
