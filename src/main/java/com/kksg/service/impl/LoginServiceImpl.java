package com.kksg.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.kksg.dtos.AuthRequestDTO;
import com.kksg.dtos.AuthResponseDTO;
import com.kksg.dtos.UserResponseDTO;
import com.kksg.entity.UserEntity;
import com.kksg.exception.ApiException;
import com.kksg.repo.UserRepo;
import com.kksg.secutiry.JwtTokenHelper;
import com.kksg.service.BaseService;
import com.kksg.service.LoginService;

@Service
public class LoginServiceImpl extends BaseService<UserEntity, Long> implements LoginService {
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	private UserDetailsService userDetailsService;
	private AuthenticationManager authenticationManager;
	private JwtTokenHelper jwtTokenHelper;
	private ModelMapper mapper;
	
	private final UserRepo userRepository;


	public LoginServiceImpl(UserRepo userRepository,UserDetailsService userDetailsService, AuthenticationManager authenticationManager,
			JwtTokenHelper jwtTokenHelper, ModelMapper mapper, UserRepo userRepo) {
		super(userRepository, userRepository); 
		this.userDetailsService = userDetailsService;
		this.authenticationManager = authenticationManager;
		this.jwtTokenHelper = jwtTokenHelper;
		this.mapper = mapper;
		this.userRepository = userRepo;
		logger.info("LoginServiceImpl created");
	}
	
	@Override
	public AuthResponseDTO doLogin(AuthRequestDTO request) {
		AuthResponseDTO response = new AuthResponseDTO();
		try {
			this.authenticate(request.getEmail(), request.getPassword());
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmail());
			String generatedToken = this.jwtTokenHelper.generateToken(userDetails);		
			response.setToken(generatedToken);
			response.setUser(mapper.map((UserEntity) userDetails, UserResponseDTO.class));
		} catch (Exception e) {
			logger.error("Error while login", e);
			throw new ApiException(e.getMessage());
		}
		return response;
	}
	
	private void authenticate(String username, String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		try {
			this.authenticationManager.authenticate(authenticationToken);
		} catch (BadCredentialsException e) {
			logger.error("Invalid Username or password", e);
			throw new ApiException("Invalid Username or password");
		}
	}

	@Override
	protected Specification<UserEntity> prepareFilter() {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isDeleted"), false);
	}

	@Override
	protected UserEntity preProcessBeforeSave(UserEntity entity) {
		// Check for unique email
		Optional<UserEntity> existingUser;
		if (entity.getId() != null) {
			existingUser = userRepository.findByEmailAndIsDeletedFalseAndIdNot(entity.getEmail(), entity.getId());
		} else {
			existingUser = userRepository.findByEmailAndIsDeletedFalse(entity.getEmail());
		}

		if (existingUser.isPresent()) {
			throw new IllegalArgumentException("Email already exists");
		}
		
		return super.preProcessBeforeSave(entity);
	}

}
