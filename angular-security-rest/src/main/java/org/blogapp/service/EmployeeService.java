package org.blogapp.service;

import org.blogapp.domain.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee save(Employee employee);
}
