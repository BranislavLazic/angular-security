package org.angularsecurity.service;

import org.angularsecurity.domain.Employee;

import java.util.List;

public interface EmployeeService {

	List<Employee> findAll();

	Employee save(Employee employee);
}