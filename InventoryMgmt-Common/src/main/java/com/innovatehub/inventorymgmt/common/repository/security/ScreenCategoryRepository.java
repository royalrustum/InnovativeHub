package com.innovatehub.inventorymgmt.common.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innovatehub.inventorymgmt.common.entity.security.ScreenCategory;

@Repository
public interface ScreenCategoryRepository extends JpaRepository<ScreenCategory, Long> {

}
