package com.empresa.cursospringsecurity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.cursospringsecurity.models.Employee;
import com.empresa.cursospringsecurity.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;
	
	public List<Employee> findAll() {
		return repository.findAll();
	}
}
