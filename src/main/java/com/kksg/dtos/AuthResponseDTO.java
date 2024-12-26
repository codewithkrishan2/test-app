package com.kksg.dtos;

import lombok.Data;

@Data
public class AuthResponseDTO {
	private String token;
	private UserResponseDTO user;	
}
