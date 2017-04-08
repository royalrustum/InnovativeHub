package com.innovatehub.inventorymgmt.common.repository.stock;


import org.springframework.stereotype.Repository;

import com.innovatehub.inventorymgmt.common.entity.stock.ProductCategory;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

}
