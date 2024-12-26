package com.kksg.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kksg.entity.Role;


public interface RoleRepo extends JpaRepository<Role, Integer> {

	
}
