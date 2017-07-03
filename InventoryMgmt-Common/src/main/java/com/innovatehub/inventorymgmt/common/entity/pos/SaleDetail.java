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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.innovatehub.inventorymgmt.common.entity.EntityBase;
//import com.innovatehub.inventorymgmt.common.entity.stock.Price;
import com.innovatehub.inventorymgmt.common.entity.stock.SKU;
import com.innovatehub.inventorymgmt.common.entity.stock.Stock;

@Entity
@Table(name = "SALE_DETAIL_T")
@SequenceGenerator(name="ID_GEN_SEQ", sequenceName="SALE_DETAIL_ID_SEQ", initialValue=1, allocationSize=1)
public class SaleDetail extends EntityBase {
	private Long id;
	
	private SKU sku;
	
	private BigDecimal sellPrice;
	
	private BigDecimal saleTax;
	
	private Long quantity;
	
	private BigDecimal discountPct;
	
	private BigDecimal discount;
	
	private BigDecimal total;
	
	private BigDecimal subTotal;
	
	private BigDecimal profit;
	
	private Sale sale;
	
	private Stock stock;
	
	@Id
	@Column(name = "SALE_DETAIL_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ID_GEN_SEQ")
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
	
	@Column(name = "SELL_PRICE")
	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
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
	
	@OneToOne(cascade = { CascadeType.REMOVE, CascadeType.DETACH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "STOCK_ID")
	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@Column(name = "SALE_TAX")
	public BigDecimal getSaleTax() {
		return saleTax;
	}

	public void setSaleTax(BigDecimal saleTax) {
		this.saleTax = saleTax;
	}

	@Column(name = "DISCOUNT")
	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	@Column(name = "SUB_TOTAL")
	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	@Column(name = "PROFIT")
	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
}
