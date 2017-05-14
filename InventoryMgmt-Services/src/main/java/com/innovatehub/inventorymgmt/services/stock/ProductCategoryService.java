package com.innovatehub.inventorymgmt.services.stock;

import java.util.List;

import com.innovatehub.inventorymgmt.common.model.stock.ProductCategory;

public interface ProductCategoryService {
	public Long saveProductCategory(ProductCategory productCategory);
	public ProductCategory getProductCategory(Long productCategory);
	public List<ProductCategory> getAllProductCategories();
}
