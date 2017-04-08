package com.innovatehub.inventorymgmt.common.model.stock;

import org.hibernate.validator.constraints.NotEmpty;

public class ProductCategory {
	
	@NotEmpty
	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
