package com.innovatehub.inventorymgmt.common.model.pos;

import java.math.BigDecimal;

public class Home {
	private BigDecimal saleTotal;

	private Long stockCount;

	private BigDecimal profit;

	public BigDecimal getSaleTotal() {
		return saleTotal;
	}

	public void setSaleTotal(BigDecimal saleTotal) {
		this.saleTotal = saleTotal;
	}

	public Long getStockCount() {
		return stockCount;
	}

	public void setStockCount(Long stockCount) {
		this.stockCount = stockCount;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
}
