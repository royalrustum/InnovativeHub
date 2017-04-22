package com.innovatehub.inventorymgmt.common.model.stock;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


public class SKU {
	private Long skuId;

	@NotEmpty
	private String skuName;

	private List<Product> allProducts;

	@NotNull
	private Product selectedProduct;

	@NotNull
	@Valid
	private Price price;

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public List<Product> getAllProducts() {
		return allProducts;
	}

	public void setAllProducts(List<Product> allProducts) {
		this.allProducts = allProducts;
	}

	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}
	
	
}
