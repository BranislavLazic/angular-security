package org.angularsecurity.service.impl;

import org.angularsecurity.domain.Employee;
import org.angularsecurity.repository.PostRepository;
import org.angularsecurity.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private PostRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(PostRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }
}
