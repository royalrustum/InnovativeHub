package com.innovatehub.inventorymgmt.common.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innovatehub.inventorymgmt.common.model.security.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
