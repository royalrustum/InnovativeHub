package com.innovatehub.inventorymgmt.services.stock;

import com.innovatehub.inventorymgmt.common.model.stock.Stock;

public interface StockService {
	Stock getStock(Long stockId);
	Long saveStock(Stock stock);
}
