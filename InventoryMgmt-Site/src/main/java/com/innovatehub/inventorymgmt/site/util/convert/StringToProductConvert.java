package com.innovatehub.inventorymgmt.site.util.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.innovatehub.inventorymgmt.common.model.stock.Product;
import com.innovatehub.inventorymgmt.services.stock.ProductService;

@Component
public class StringToProductConvert implements Converter<String, Product> {

	private ProductService productService;

	public StringToProductConvert(ProductService productService) {
		this.setProductService(productService);
	}

	public ProductService getProductService() {
		return productService;
	}

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public Product convert(String product) {
		return this.getProductService().getProduct(Long.valueOf(product));
	}

}
