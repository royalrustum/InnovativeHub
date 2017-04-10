package com.innovatehub.inventorymgmt.services.stock;

import java.sql.SQLException;

import com.innovatehub.inventorymgmt.common.model.stock.ProductCategory;

public interface ProductCategoryService {
	public int saveProductCategory(ProductCategory productCategory);
	public ProductCategory getProductCategory(int productCategory) throws SQLException;
}
