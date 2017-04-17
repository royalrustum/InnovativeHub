package com.innovatehub.inventorymgmt.services.stock;

import java.util.List;

import com.innovatehub.inventorymgmt.common.model.stock.ProductCategory;

public interface ProductCategoryService {
	public int saveProductCategory(ProductCategory productCategory);
	public ProductCategory getProductCategory(int productCategory);
	public List<ProductCategory> getAllProductCategories();
}
