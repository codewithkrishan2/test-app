package com.kksg.service.impl;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kksg.contants.AppConstants;
import com.kksg.entity.Role;
import com.kksg.entity.UserEntity;
import com.kksg.repo.RoleRepo;
import com.kksg.repo.UserRepo;
import com.kksg.service.BaseService;
import com.kksg.service.IUserService;

@Service
public class UserServiceImpl extends BaseService<UserEntity, Long> implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);	
	private final UserRepo userRepository;
	private PasswordEncoder passwordEncoder;
	private RoleRepo roleRepo;

	public UserServiceImpl(UserRepo userRepository, PasswordEncoder passwordEncoder,RoleRepo roleRepo) {
        super(userRepository, userRepository); // Pass the repository twice (it implements both interfaces)
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
    }
	
	@Override
	protected Specification<UserEntity> prepareFilter() {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isDeleted"), false);
	}

	@Override
	protected void preProcessBeforeSave(UserEntity entity) {
		// Check for unique email
		Optional<UserEntity> existingUser;
		if (entity.getId() != null) {
			existingUser = userRepository.findByEmailAndIsDeletedFalseAndIdNot(entity.getEmail(), entity.getId());
		} else {
			existingUser = userRepository.findByEmailAndIsDeletedFalse(entity.getEmail());
		}

		if (existingUser.isPresent()) {
			throw new IllegalArgumentException("Email already exists");
		}
		
		entity.setPassword(this.passwordEncoder.encode(entity.getPassword()));
		Role role = this.roleRepo.findById(AppConstants.NORLMAL_USER).orElseThrow(()-> new IllegalArgumentException("Role not found"));
		entity.getRoles().add(role);
		logger.info("Roles added:{}", entity.getRoles());
	}

	@Override
	public Optional<UserEntity> getUserByEmail(String email) {
		Optional<UserEntity> findByEmailAndIsDeletedFalse = Optional.empty();
		try {
			findByEmailAndIsDeletedFalse = userRepository.findByEmailAndIsDeletedFalse(email);
		} catch (Exception e) {
			logger.error("Error getting user by email: {}", e.getMessage());
		}
	    return findByEmailAndIsDeletedFalse;
	}
	
//	@Override
//    protected List<UserEntity> postProcessAfterGetData(List<UserEntity> users) {
//        // Example: Enrich user data with additional info
//        for (UserEntity user : users) {
//            // Add related data or manipulate fields as needed
//            user.setAdditionalInf("Enriched Info");
//        }
//        return users;
//    }
//
//    @Override
//    protected UserEntity postProcessAfterGetById(UserEntity user) {
//        // Example: Remove sensitive fields or enrich user data
//        user.setAdditionalInf("Enriched Info");
//        return user;
//    }
}