package com.innovatehub.inventorymgmt.common.entity.pos;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.innovatehub.inventorymgmt.common.entity.EntityBase;

@Entity
@Table(name = "SALE_T")
public class Sale extends EntityBase {
	private Long id;
	
	private BigDecimal subTotal;
	
	private BigDecimal discount;
	
	private BigDecimal saleTax;

	private BigDecimal total;
	
	@Id
	@Column(name = "SALE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
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

}
