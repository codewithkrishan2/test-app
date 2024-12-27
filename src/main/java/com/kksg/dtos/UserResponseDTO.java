package com.kksg.dtos;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO extends BaseResponseDTO {
	
    private String name;
    private String email;
	private String password;
	private Set<RolesDto> roles = new HashSet<>();
//	private String additionalInf;
	
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
	
	@JsonIgnore
	public String getPassword() {
		return password;
	}
		
}
