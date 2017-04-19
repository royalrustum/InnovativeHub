package com.innovatehub.inventorymgmt.services.stock;

import com.innovatehub.inventorymgmt.common.model.stock.SKU;

public interface SKUService {
	public Long saveSKU(SKU sku);
	public SKU getSKU(Long skuId);
}
