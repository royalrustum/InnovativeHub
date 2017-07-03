package com.innovatehub.inventorymgmt.common.entity.stock;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.innovatehub.inventorymgmt.common.entity.EntityBase;

@Entity
@Table(name = "STOCK_T")
@SequenceGenerator(name="ID_GEN_SEQ", sequenceName="STOCK_ID_SEQ", initialValue=1, allocationSize=1)
public class Stock extends EntityBase {
	private Long stockId;

	private Date stockDate;

	private SKU sku;

	private Long units;

	private BigDecimal unitPrice;
	
	private Long unitsSold;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ID_GEN_SEQ")
	@Column(name = "STOCK_ID")
	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	@Temporal(TemporalType.DATE)
	public Date getStockDate() {
		return stockDate;
	}

	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}

	@ManyToOne(cascade = { CascadeType.REMOVE, CascadeType.DETACH } , fetch = FetchType.EAGER)
	@JoinColumn(name = "SKU_ID")
	public SKU getSku() {
		return sku;
	}

	public void setSku(SKU sku) {
		this.sku = sku;
	}

	@Column(name = "UNITS")
	public Long getUnits() {
		return units;
	}

	public void setUnits(Long units) {
		this.units = units;
	}

	@Column(name = "UNIT_PRICE")
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal price) {
		this.unitPrice = price;
	}
	
	@Column(name = "UNITS_SOLD")
	public Long getUnitsSold() {
		return unitsSold;
	}

	public void setUnitsSold(Long unitsSold) {
		this.unitsSold = unitsSold;
	}

}
