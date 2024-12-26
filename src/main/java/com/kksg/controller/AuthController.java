package com.kksg.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kksg.contants.Status;
import com.kksg.dtos.AuthRequestDTO;
import com.kksg.dtos.AuthResponseDTO;
import com.kksg.dtos.UserRequestDTO;
import com.kksg.entity.UserEntity;
import com.kksg.service.LoginService;
import com.kksg.service.impl.UserServiceImpl;
import com.kksg.util.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController<UserEntity, AuthResponseDTO, AuthRequestDTO> {

	private LoginService loginService;
	private UserServiceImpl userServiceImpl;
	private ModelMapper modelMapper;

	public AuthController(UserServiceImpl userServiceImpl, ModelMapper modelMapper, LoginService loginService) {
		super(userServiceImpl); // Pass UserServiceImpl to BaseController
		this.userServiceImpl = userServiceImpl; // Initialize userServiceImpl field
		this.modelMapper = modelMapper;
		this.loginService = loginService;
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<AuthResponseDTO>> login(@Valid @RequestBody AuthRequestDTO request) {
		try {
			AuthResponseDTO response = loginService.doLogin(request);
			return ResponseEntity.ok(new ApiResponse<>(Status.SUCCESS, null,"Login Successfully", response));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponse<>(Status.FAILED, e.getMessage(), null, null));
		}
	}

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<AuthResponseDTO>> register(@Valid @RequestBody UserRequestDTO request) {
		UserEntity user = modelMapper.map(request, UserEntity.class);
		userServiceImpl.save(user);
		AuthResponseDTO response = modelMapper.map(user, AuthResponseDTO.class);
		return ResponseEntity.ok(new ApiResponse<>(Status.SUCCESS, null,"Register Successfully", response));
	}

	@Override
	protected UserEntity mapToEntity(AuthRequestDTO dto) {
		return modelMapper.map(dto, UserEntity.class);
		
	}

	@Override
	protected AuthResponseDTO mapToResponse(UserEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}