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

import com.innovatehub.inventorymgmt.common.entity.EntityBase;

@Entity
@Table(name = "SKU_T")
public class SKU extends EntityBase {
	private Long skuId;

	private String skuName;
	
	private Product product;

	private BigDecimal priceOffset;

	private StockDetail stockDetail;
	
	private BigDecimal margin;
	
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

	@Column(name = "SKU_NAME", unique=true)
	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
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
	
	@Column(name = "PROFIT_MARGIN")
	public BigDecimal getMargin() {
		return margin;
	}

	public void setMargin(BigDecimal margin) {
		this.margin = margin;
	}
}
