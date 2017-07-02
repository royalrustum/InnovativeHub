package com.innovatehub.inventorymgmt.common.model.stock;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.innovatehub.inventorymgmt.common.model.stock.SKU;
import com.innovatehub.inventorymgmt.common.model.ModelBase;

public class Stock extends ModelBase {
	private Long stockId;

	@NotNull
	private Date stockDate;

	private SKU sku;

	@NotNull
	@Min(value= 1)
	private Long units;

	@NotNull
	@DecimalMin(value = "0.0")
	private BigDecimal unitPrice;
	
	private Long unitsSold;
	
	private List<ProductCategory> allProductCategories;

	@NotNull
	private ProductCategory selectedProdCategory;
	
	private List<Product> allProducts;

	@NotNull
	private Product selectedProduct;
	
	@NotNull
	private SKU selectedSKU;
	
	private List<SKU> allSKU;
	
	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Date getStockDate() {
		return stockDate;
	}

	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}

	public SKU getSku() {
		return sku;
	}

	public void setSku(SKU sku) {
		this.sku = sku;
	}

	public Long getUnits() {
		return units;
	}

	public void setUnits(Long units) {
		this.units = units;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal price) {
		this.unitPrice = price;
	}
	
	public List<ProductCategory> getAllProductCategories() {
		return allProductCategories;
	}

	public void setAllProductCategories(List<ProductCategory> allProductCategories) {
		this.allProductCategories = allProductCategories;
	}
	
	public ProductCategory getSelectedProdCategory() {
		return selectedProdCategory;
	}

	public void setSelectedProdCategory(ProductCategory selectedProdCategory) {
		this.selectedProdCategory = selectedProdCategory;
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
	
	public SKU getSelectedSKU() {
		return selectedSKU;
	}

	public void setSelectedSKU(SKU selectedSKU) {
		this.selectedSKU = selectedSKU;
	}
	
	public List<SKU> getAllSKU() {
		return allSKU;
	}

	public void setAllSKU(List<SKU> allSKU) {
		this.allSKU = allSKU;
	}

	public Long getUnitsSold() {
		return unitsSold;
	}

	public void setUnitsSold(Long unitsSold) {
		this.unitsSold = unitsSold;
	}

	public Long getUnitsAvailable() {
		Long unitsStocked = this.getUnits() == null ? 0 : this.getUnits();
		Long stockedUnitsSold = this.getUnitsSold() == null ? 0 : this.getUnitsSold();
		
		return unitsStocked - stockedUnitsSold;
	}
}
