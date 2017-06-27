package com.innovatehub.inventorymgmt.common.model.stock;

import java.math.BigDecimal;
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
	private BigDecimal sellPrice;

	private Long quantityAvailable;
	
	private List<Stock> selectedSKUStockList;
	
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

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	public Long getQuantityAvailable() {
		return quantityAvailable != null ? quantityAvailable : 0;
	}

	public void setQuantityAvailable(Long quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}
	
	public List<Stock> getSelectedSKUStockList() {
		return selectedSKUStockList;
	}

	public void setSelectedSKUStockList(List<Stock> selectedSKUStockList) {
		this.selectedSKUStockList = selectedSKUStockList;
	}
}
