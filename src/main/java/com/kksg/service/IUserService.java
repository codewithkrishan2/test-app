package com.kksg.service;

import java.util.Optional;

import com.kksg.entity.UserEntity;

public interface IUserService {
	public Optional<UserEntity> getUserByEmail(String email);
}
