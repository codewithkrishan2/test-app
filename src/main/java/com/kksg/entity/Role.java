package com.kksg.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer roleId;
	
	private String name;
}
	