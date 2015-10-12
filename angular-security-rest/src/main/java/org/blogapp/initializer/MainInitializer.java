package org.blogapp.initializer;

import org.blogapp.domain.Employee;
import org.blogapp.domain.Role;
import org.blogapp.domain.User;
import org.blogapp.service.EmployeeService;
import org.blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MainInitializer {

    private EmployeeService employeeService;

    private UserService userService;

    @Autowired
    public MainInitializer(EmployeeService employeeService, UserService userService) {
        this.employeeService = employeeService;
        this.userService = userService;
    }

    @PostConstruct
    public void initializePosts() {
        employeeService.save(new Employee("John", "Doe", 26));
        employeeService.save(new Employee("Jane", "Doe", 23));
    }

    @PostConstruct
    public void initializeUsers() {
        userService.save(new User("admin", "admin", Role.ADMIN));
    }
}
