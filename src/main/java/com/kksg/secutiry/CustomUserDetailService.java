package com.kksg.secutiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kksg.entity.UserEntity;
import com.kksg.exception.ResourceNotFoundException;
import com.kksg.repo.UserRepo;




@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//Loading user from database
		UserEntity user = this.userRepo.findByEmailAndIsDeletedFalse(username).orElseThrow(()-> new ResourceNotFoundException("User ", " email : " +username, 0));
		return user;
	}

	
}
