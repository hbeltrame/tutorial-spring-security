package com.empresa.cursospringsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.empresa.cursospringsecurity.models.Employee;
import com.empresa.cursospringsecurity.repositories.EmployeeRepository;
import com.empresa.cursospringsecurity.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private EmployeeRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = repository.findByUsername(username);
		if (employee == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new UserSS(employee.getId(), employee.getUsername(), employee.getPassword(), employee.getRoles());
	}

}
