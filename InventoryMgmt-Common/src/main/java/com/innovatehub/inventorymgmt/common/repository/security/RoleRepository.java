package com.innovatehub.inventorymgmt.common.repository.security;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innovatehub.inventorymgmt.common.entity.security.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findByNameIn(List<String> names);
	Role findByName(String name);
}
