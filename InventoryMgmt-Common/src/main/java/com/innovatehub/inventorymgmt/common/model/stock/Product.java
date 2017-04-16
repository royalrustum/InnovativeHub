package com.innovatehub.inventorymgmt.common.model.stock;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.innovatehub.inventorymgmt.common.util.CommonUtilHelper;

public class Product {
	@NotEmpty
	private String productName;

	private String productCategory;

	@NotNull
	@DecimalMin(value = "0.0")
	private BigDecimal margin;

	private byte[] productImage;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public byte[] getProductImage() {
		return productImage;
	}

	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}

	public BigDecimal getMargin() {
		return margin;
	}

	public void setMargin(BigDecimal margin) {
		this.margin = margin;
	}
	
	public String getBase64FileBytesString() {
		return CommonUtilHelper.getBase64ImageString(this.getProductImage());
	}
}
