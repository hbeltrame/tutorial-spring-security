package com.empresa.cursospringsecurity;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.empresa.cursospringsecurity.models.Employee;
import com.empresa.cursospringsecurity.models.enums.Role;
import com.empresa.cursospringsecurity.repositories.EmployeeRepository;

@SpringBootApplication
public class CursoSpringSecurityApplication implements CommandLineRunner {
	
	@Autowired
	private EmployeeRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Employee e1 = new Employee(null, "henrique.beltrame", encoder.encode("1234"), 
				                   "Henrique", "henrique@email.com", "99999999");
		Employee e2 = new Employee(null, "fernanda.pereira", encoder.encode("1234"), 
				                   "Fernanda", "fernanda@email.com", "88888888");
		e2.addRole(Role.ADMIN);
		
		userRepository.saveAll(Arrays.asList(e1, e2));
	}

}
