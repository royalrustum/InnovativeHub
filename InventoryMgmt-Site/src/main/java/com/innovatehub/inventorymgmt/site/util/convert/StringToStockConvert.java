package com.innovatehub.inventorymgmt.site.util.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.innovatehub.inventorymgmt.common.model.stock.Stock;
import com.innovatehub.inventorymgmt.services.stock.StockService;

@Component
public class StringToStockConvert implements Converter<String, Stock> {

	private StockService stockService;

	public StringToStockConvert(StockService stockService) {
		this.setStockService(stockService);
	}

	public StockService getStockService() {
		return stockService;
	}

	@Autowired
	public void setStockService(StockService stockService) {
		this.stockService = stockService;
	}	

	@Override
	public Stock convert(String stock) {
		return this.getStockService().getStock(Long.valueOf(stock));
	}

}
