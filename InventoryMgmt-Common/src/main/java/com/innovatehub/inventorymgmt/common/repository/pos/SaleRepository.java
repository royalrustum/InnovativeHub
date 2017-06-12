package com.innovatehub.inventorymgmt.common.repository.pos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innovatehub.inventorymgmt.common.entity.pos.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

}