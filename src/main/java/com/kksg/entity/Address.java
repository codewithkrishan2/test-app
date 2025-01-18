package com.kksg.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address extends BaseEntity {

	@ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
	private String addressType;
    
	private String name;
    private String street;
    private String city;
    private String state;
    private String country;
    private String pincode;
    private boolean isDefault;
}
