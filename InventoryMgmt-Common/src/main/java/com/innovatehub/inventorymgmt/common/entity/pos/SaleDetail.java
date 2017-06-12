package com.innovatehub.inventorymgmt.common.entity.pos;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.innovatehub.inventorymgmt.common.entity.EntityBase;
import com.innovatehub.inventorymgmt.common.entity.stock.Price;
import com.innovatehub.inventorymgmt.common.entity.stock.SKU;

@Entity
@Table(name = "SALE_DETAIL_T")
public class SaleDetail extends EntityBase {
	private Long id;
	
	private SKU sku;
	
	private Price price;
	
	private Long quantity;
	
	private BigDecimal discountPct;
	
	private BigDecimal total;
	
	private Sale sale;
	
	@Id
	@Column(name = "SALE_DETAIL_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "SALE_ID")
	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "SKU_ID")
	public SKU getSku() {
		return sku;
	}

	public void setSku(SKU sku) {
		this.sku = sku;
	}
	
	@OneToOne(cascade = { CascadeType.REMOVE, CascadeType.DETACH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "PRICE_ID")
	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}
	
	@Column(name = "QUANTITY")
	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	@Column(name = "DISCOUNT_PERCENT")
	public BigDecimal getDiscountPct() {
		return discountPct;
	}

	public void setDiscountPct(BigDecimal discountPct) {
		this.discountPct = discountPct;
	}
	
	@Column(name = "TOTAL")
	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}
