package com.kksg.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kksg.contants.Status;
import com.kksg.dtos.UserRequestDTO;
import com.kksg.dtos.UserResponseDTO;
import com.kksg.entity.UserEntity;
import com.kksg.service.IUserService;
import com.kksg.service.impl.UserServiceImpl;
import com.kksg.util.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController<UserEntity, UserResponseDTO, UserRequestDTO >  {
   
	private final IUserService userService; // Add a field for UserServiceImpl
	private ModelMapper modelMapper;

    public UserController(UserServiceImpl userServiceImpl, ModelMapper modelMapper) {
        super(userServiceImpl); // Pass UserServiceImpl to BaseController
        this.userService = userServiceImpl; // Initialize userService field
		this.modelMapper = modelMapper;
    }


    @GetMapping("/email/{username}")
    public ResponseEntity<ApiResponse<UserEntity>> getByUsername(@PathVariable String username) {
        return userService.getUserByEmail(username)
                .map(user -> ResponseEntity.ok(new ApiResponse<>(Status.SUCCESS, null,"Successfully ", user)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(Status.FAILED, "User not found", null, null)));
    }
    

	@Override
	protected UserEntity mapToEntity(@Valid UserRequestDTO dto) {
		return modelMapper.map(dto, UserEntity.class);
	}


	@Override
	protected UserResponseDTO mapToResponse(UserEntity entity) {
		return modelMapper.map(entity, UserResponseDTO.class);
	}

}
