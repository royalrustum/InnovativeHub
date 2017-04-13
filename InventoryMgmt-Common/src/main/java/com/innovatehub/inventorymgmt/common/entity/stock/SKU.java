package com.innovatehub.inventorymgmt.common.entity.stock;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SKU_T")
public class SKU {
	private Long skuId;

	private Product product;

	private String description;

	private BigDecimal priceOffset;

	private StockDetail stockDetail;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SKU_ID")
	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT_ID")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "DESCRIPTION", unique=true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "PRICE_OFFSET")
	public BigDecimal getPriceOffset() {
		return priceOffset;
	}

	public void setPriceOffset(BigDecimal priceOffset) {
		this.priceOffset = priceOffset;
	}
	
	@OneToOne
	@JoinColumn(name = "STOCK_DETAIL_ID")
	public StockDetail getStockDetail() {
		return stockDetail;
	}

	public void setStockDetail(StockDetail stockDetail) {
		this.stockDetail = stockDetail;
	}
}
