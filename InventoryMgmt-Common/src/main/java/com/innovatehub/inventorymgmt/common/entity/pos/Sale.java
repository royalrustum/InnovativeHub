package com.innovatehub.inventorymgmt.common.entity.pos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.innovatehub.inventorymgmt.common.entity.EntityBase;
import com.innovatehub.inventorymgmt.common.entity.customer.Customer;

@Entity
@Table(name = "SALE_T")
@SequenceGenerator(name="ID_GEN_SEQ", sequenceName="SALE_ID_SEQ", initialValue=1, allocationSize=1)
public class Sale extends EntityBase {
	private Long id;
	
	private BigDecimal subTotal;
	
	private BigDecimal discount;
	
	private BigDecimal saleTax;

	private BigDecimal total;
	
	private BigDecimal profit;
	
	private Set<SaleDetail> saleDetails;
	
	private Customer customer;
	
	private Integer itemCount;
	
	@Id
	@Column(name = "SALE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ID_GEN_SEQ")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "SUB_TOTAL")
	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	@Column(name = "DISCOUNT")
	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	@Column(name = "SALE_TAX")
	public BigDecimal getSaleTax() {
		return saleTax;
	}

	public void setSaleTax(BigDecimal saleTax) {
		this.saleTax = saleTax;
	}

	@Column(name = "TOTAL")
	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Column(name = "PROFIT")
	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	
	@OneToMany(cascade = { CascadeType.REMOVE, CascadeType.DETACH}, fetch = FetchType.EAGER, mappedBy="sale")
	public Set<SaleDetail> getSaleDetails() {
		return saleDetails;
	}

	public void setSaleDetails(Set<SaleDetail> saleDetails) {
		this.saleDetails = saleDetails;
	}

	@ManyToOne(cascade = { CascadeType.REMOVE, CascadeType.DETACH}, fetch = FetchType.EAGER)
	@JoinColumn(name = "CUSTOMER_ID")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "ITEM_COUNT")
	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}
}
