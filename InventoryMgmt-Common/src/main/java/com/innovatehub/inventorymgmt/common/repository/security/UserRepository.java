package com.innovatehub.inventorymgmt.common.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import com.innovatehub.inventorymgmt.common.model.security.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String userName);
}
