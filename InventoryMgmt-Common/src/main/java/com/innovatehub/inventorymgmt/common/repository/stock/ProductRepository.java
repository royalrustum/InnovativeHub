package com.innovatehub.inventorymgmt.common.repository.stock;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innovatehub.inventorymgmt.common.entity.stock.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByProductCategoryProductCategoryId(Long productCategoryId);
}
