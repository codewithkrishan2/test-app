package com.kksg.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class BaseResponseDTO {

	private Long id;
    private LocalDateTime created;
    private LocalDateTime modified;
    private boolean isDeleted = false;
	
}
