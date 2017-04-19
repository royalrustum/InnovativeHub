package com.innovatehub.inventorymgmt.common.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.innovatehub.inventorymgmt.common.entity.stock.SKU;

@Repository
public interface SKURepository extends JpaRepository<SKU, Long> {

}
