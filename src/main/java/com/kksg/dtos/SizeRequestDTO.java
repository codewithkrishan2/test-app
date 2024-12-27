package com.kksg.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SizeRequestDTO {
	
	
	@NotBlank(message = "Size label cannot be empty")
    private String sizeLabel;  // For example: S, M, L, XL, or 32B, 34C

    @DecimalMin(value = "0.0", message = "Bust measurement must be non-negative")
    private Double bustMeasurement; 

    @DecimalMin(value = "0.0", message = "Waist measurement must be non-negative")
    private Double waistMeasurement; 
    
    @DecimalMin(value = "0.0", message = "Hips measurement must be non-negative")
    private Double hipsMeasurement; 
    
    @NotNull(message = "Underwire status cannot be null")
    private Boolean underwire;

    @NotBlank(message = "Size type cannot be empty")
    private String sizeType;
    
}