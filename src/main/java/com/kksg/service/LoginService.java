package com.kksg.service;

import org.springframework.stereotype.Service;

import com.kksg.dtos.AuthRequestDTO;
import com.kksg.dtos.AuthResponseDTO;

@Service
public interface LoginService {
	public AuthResponseDTO doLogin(AuthRequestDTO request);
//	public UserResponseDTO doRegister(UserRequestDTO request);
}