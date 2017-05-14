package com.innovatehub.inventorymgmt.common.repository.stock;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innovatehub.inventorymgmt.common.entity.stock.SKU;

@Repository
public interface SKURepository extends JpaRepository<SKU, Long> {
	List<SKU> findByProductProductId(Long productId);
}
