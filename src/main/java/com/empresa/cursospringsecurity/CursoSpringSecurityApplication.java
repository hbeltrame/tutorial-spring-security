package com.empresa.cursospringsecurity;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.empresa.cursospringsecurity.models.User;
import com.empresa.cursospringsecurity.models.enums.Role;
import com.empresa.cursospringsecurity.repositories.UserRepository;

@SpringBootApplication
public class CursoSpringSecurityApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "Henrique Beltrame", encoder.encode("1234"));
		User u2 = new User(null, "Fernanda Pereira", encoder.encode("1234"));
		u2.addRole(Role.ADMIN);
		
		userRepository.saveAll(Arrays.asList(u1, u2));
	}

}
