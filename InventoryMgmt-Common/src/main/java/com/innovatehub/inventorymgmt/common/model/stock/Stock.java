package com.innovatehub.inventorymgmt.common.model.stock;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.innovatehub.inventorymgmt.common.entity.EntityBase;
import com.innovatehub.inventorymgmt.common.model.ModelBase;

public class Stock extends ModelBase {
	private Long stockId;

	private Date stockDate;

	private SKU sku;

	private Long units;

	private BigDecimal unitPrice;
	
	private List<ProductCategory> allProductCategories;

	private ProductCategory selectedProdCategory;
	
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
}
