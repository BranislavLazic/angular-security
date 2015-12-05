package org.angularsecurity.controller;

import java.util.List;

import javax.validation.Valid;

import org.angularsecurity.domain.Employee;
import org.angularsecurity.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@RequestMapping(value = "/admin/employee/all", method = RequestMethod.GET)
	public List<Employee> getAllPosts() {
		return employeeService.findAll();
	}

	@RequestMapping(value = "/admin/employee/save", method = RequestMethod.POST)
	public ResponseEntity<Employee> savePost(@RequestBody @Valid Employee employee, BindingResult result) {
		if (!result.hasErrors()) {
			employeeService.save(employee);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}