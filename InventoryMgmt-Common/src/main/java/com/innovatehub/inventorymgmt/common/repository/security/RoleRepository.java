package com.innovatehub.inventorymgmt.common.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innovatehub.inventorymgmt.common.model.security.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
