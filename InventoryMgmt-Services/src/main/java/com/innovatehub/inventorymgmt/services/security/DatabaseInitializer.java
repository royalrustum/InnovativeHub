package com.innovatehub.inventorymgmt.services.security;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.innovatehub.inventorymgmt.common.model.security.Role;
import com.innovatehub.inventorymgmt.common.model.security.User;
import com.innovatehub.inventorymgmt.common.repository.security.UserRepository;

@Component
public class DatabaseInitializer {

	private UserRepository userRepository;

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public DatabaseInitializer(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@PostConstruct
	@Transactional(readOnly=false)
	private void populateData() {

		Role adminRole = new Role();
		adminRole.setName("ADMIN_ROLE");

		Set<Role> roles = new HashSet<Role>();
		roles.add(adminRole);

		User adminUser = new User();
		adminUser.setUserName("admin");
		adminUser.setPassword("admin");
		adminUser.setRoles(roles);

		this.userRepository.save(adminUser);
	}
}
