package com.innovatehub.inventorymgmt.site.util.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.innovatehub.inventorymgmt.common.model.stock.ProductCategory;
import com.innovatehub.inventorymgmt.services.stock.ProductCategoryService;

@Component
public class StringToProductCatConvert implements Converter<String, ProductCategory> {

	private ProductCategoryService prodCatService;

	public StringToProductCatConvert(ProductCategoryService prodCatService) {
		this.setProdCatService(prodCatService);
	}

	public ProductCategoryService getProdCatService() {
		return prodCatService;
	}

	@Autowired
	public void setProdCatService(ProductCategoryService prodCatService) {
		this.prodCatService = prodCatService;
	}

	@Override
	public ProductCategory convert(String prodCat) {
		return this.getProdCatService().getProductCategory(Integer.valueOf(prodCat));
	}

}
