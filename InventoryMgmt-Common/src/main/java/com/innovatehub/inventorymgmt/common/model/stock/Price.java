package com.innovatehub.inventorymgmt.common.model.stock;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class Price {
	private Long priceId;

	@NotNull
	@DecimalMin(value = "0.0")
	private BigDecimal buyingPrice;
	
	@NotNull
	@DecimalMin(value = "0.0")
	private BigDecimal sellingPrice;
	
	public Long getPriceId() {
		return priceId;
	}

	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}

	public BigDecimal getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(BigDecimal buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
}
