package com.innovatehub.inventorymgmt.common.model.pos;

import java.math.BigDecimal;
import java.util.List;

import com.innovatehub.inventorymgmt.common.model.ModelBase;
import com.innovatehub.inventorymgmt.common.model.customer.Customer;
import com.innovatehub.inventorymgmt.common.model.stock.Stock;

public class Sale extends ModelBase {
	private Long id;
	
	private BigDecimal subTotal;
	
	private BigDecimal discount;
	
	private BigDecimal saleTax;

	private BigDecimal total;
	
	private List<SaleDetail> saleDetails;
	
	private Customer customer;
	
	private List<Stock> stockList;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getSaleTax() {
		return saleTax;
	}

	public void setSaleTax(BigDecimal saleTax) {
		this.saleTax = saleTax;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<SaleDetail> getSaleDetails() {
		return saleDetails;
	}

	public void setSaleDetails(List<SaleDetail> saleDetails) {
		this.saleDetails = saleDetails;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public List<Stock> getStockList() {
		return stockList;
	}

	public void setStockList(List<Stock> stockList) {
		this.stockList = stockList;
	}
}
