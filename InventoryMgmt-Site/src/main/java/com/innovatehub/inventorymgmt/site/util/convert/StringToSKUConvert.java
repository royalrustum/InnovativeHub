package com.innovatehub.inventorymgmt.site.util.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.innovatehub.inventorymgmt.common.model.stock.Product;
import com.innovatehub.inventorymgmt.common.model.stock.SKU;
import com.innovatehub.inventorymgmt.services.stock.ProductService;
import com.innovatehub.inventorymgmt.services.stock.SKUService;

@Component
public class StringToSKUConvert implements Converter<String, SKU> {

	private SKUService skuService;

	public StringToSKUConvert(SKUService skuService) {
		this.setSkuService(skuService);
	}

	public SKUService getSkuService() {
		return skuService;
	}

	@Autowired
	public void setSkuService(SKUService skuService) {
		this.skuService = skuService;
	}

	@Override
	public SKU convert(String sku) {
		return this.getSkuService().getSKU(Long.valueOf(sku));
	}

}
