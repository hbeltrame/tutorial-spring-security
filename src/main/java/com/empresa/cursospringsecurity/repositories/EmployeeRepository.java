package com.empresa.cursospringsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.cursospringsecurity.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Employee findByUsername(String username);
}
