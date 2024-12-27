package com.kksg.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO extends BaseResponseDTO {
	
	@NotBlank(message = "Name is required")
	private String name;
	
	private String description;
}
