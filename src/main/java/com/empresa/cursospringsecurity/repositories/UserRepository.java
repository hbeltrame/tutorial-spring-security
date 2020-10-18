package com.empresa.cursospringsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.cursospringsecurity.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
