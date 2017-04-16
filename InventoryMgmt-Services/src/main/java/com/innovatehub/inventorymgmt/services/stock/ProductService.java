package com.innovatehub.inventorymgmt.services.stock;

import com.innovatehub.inventorymgmt.common.model.stock.Product;

public interface ProductService {
	Product getProduct(Long productId);
	Long saveProduct(Product product);
}
