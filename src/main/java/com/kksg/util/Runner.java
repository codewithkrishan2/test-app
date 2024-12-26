package com.kksg.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.kksg.contants.AppConstants;
import com.kksg.entity.Role;
import com.kksg.repo.RoleRepo;

@Component
public class Runner implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(Runner.class);
	
	private RoleRepo roleRepo;
	
	public Runner(RoleRepo roleRepo) {
		this.roleRepo = roleRepo;
	}
		
	@Override
	public void run(String... args) throws Exception {
		
		try {
			Role role1 = new Role();
			role1.setRoleId(AppConstants.ADMIN_USER);
			role1.setName("ROLE_ADMIN");
			
			Role role2 = new Role();
			role2.setRoleId(AppConstants.NORLMAL_USER);
			role2.setName("ROLE_NORMAL");
			
			Role role3 = new Role();
			role3.setRoleId(AppConstants.SUPERADMIN_USER);
			role3.setName("ROLE_SUPERADMIN");
			
			List<Role> roles = List.of(role1,role2,role3);
			List<Role> savedRoles = this.roleRepo.saveAll(roles);
			savedRoles.forEach(r->logger.info("Role saved: {}",r));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
