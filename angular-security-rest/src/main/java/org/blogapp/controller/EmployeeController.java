package org.blogapp.controller;

import org.blogapp.domain.Employee;
import org.blogapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/api/employee/all", method = RequestMethod.GET)
    public List<Employee> getAllPosts() {
        return employeeService.findAll();
    }

    @RequestMapping(value = "/api/employee/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Employee savePost(@RequestBody @Valid Employee employee, BindingResult result) {
        if(!result.hasErrors()) {
            employeeService.save(employee);
        }
        return employee;
    }
}
