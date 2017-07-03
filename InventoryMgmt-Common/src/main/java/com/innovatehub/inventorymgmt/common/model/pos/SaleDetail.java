package com.innovatehub.inventorymgmt.common.model.pos;

import java.math.BigDecimal;

import com.innovatehub.inventorymgmt.common.model.ModelBase;
import com.innovatehub.inventorymgmt.common.model.stock.SKU;
import com.innovatehub.inventorymgmt.common.model.stock.Stock;

public class SaleDetail extends ModelBase {
	private Long id;
	
	private SKU sku;
	
	private Long quantity;
	
	private BigDecimal discountPct;
	
	private BigDecimal discount;
	
	private BigDecimal saleTax;
	
	private BigDecimal total;
	
	private BigDecimal subTotal;
	
	private BigDecimal profit;
	
	private Sale sale;
	
	private BigDecimal sellPrice;
	
	private Stock selectedStock;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}
	
	public SKU getSku() {
		return sku;
	}

	public void setSku(SKU sku) {
		this.sku = sku;
	}
	
	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	public BigDecimal getDiscountPct() {
		return discountPct;
	}

	public void setDiscountPct(BigDecimal discountPct) {
		this.discountPct = discountPct;
	}
	
	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	public Stock getSelectedStock() {
		return selectedStock;
	}

	public void setSelectedStock(Stock selectedStock) {
		this.selectedStock = selectedStock;
	}

	public BigDecimal getSaleTax() {
		return saleTax;
	}

	public void setSaleTax(BigDecimal saleTax) {
		this.saleTax = saleTax;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
}
