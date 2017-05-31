package com.innovatehub.inventorymgmt.services.stock;

import java.util.List;

import com.innovatehub.inventorymgmt.common.model.stock.SKU;

public interface SKUService {
	public Long saveSKU(SKU sku);
	public SKU getSKU(Long skuId);
	public List<SKU> getAllSKUForProduct(Long productId);
	public List<SKU> getAllSKUForAllProducts();
}
