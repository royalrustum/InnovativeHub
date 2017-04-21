package com.innovatehub.inventorymgmt.common.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innovatehub.inventorymgmt.common.entity.security.Role_Screen;

@Repository
public interface Role_ScreenRepository extends JpaRepository<Role_Screen, Long> {

}
