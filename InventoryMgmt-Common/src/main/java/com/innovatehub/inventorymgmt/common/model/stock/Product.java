package com.innovatehub.inventorymgmt.common.model.stock;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.innovatehub.inventorymgmt.common.util.CommonUtilHelper;

public class Product {
	
	private Long productId;
	
	@NotEmpty
	private String productName;

	private byte[] productImage;

	private List<ProductCategory> productCategories;

	@NotNull
	private ProductCategory selectedProdCategory;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public byte[] getProductImage() {
		return productImage;
	}

	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}

	public List<ProductCategory> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(List<ProductCategory> productCategories) {
		this.productCategories = productCategories;
	}

	public ProductCategory getSelectedProdCategory() {
		return selectedProdCategory;
	}

	public void setSelectedProdCategory(ProductCategory selectedProdCategory) {
		this.selectedProdCategory = selectedProdCategory;
	}

	public String getBase64FileBytesString() {
		return CommonUtilHelper.getBase64ImageString(this.getProductImage());
	}
}
