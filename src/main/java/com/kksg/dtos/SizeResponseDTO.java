package com.kksg.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SizeResponseDTO extends BaseResponseDTO {
    private String sizeLabel;
    private Double bustMeasurement;  
    private Double waistMeasurement; 
    private Double hipsMeasurement; 
    private String sizeType;    
    private Boolean underwire; 
}
