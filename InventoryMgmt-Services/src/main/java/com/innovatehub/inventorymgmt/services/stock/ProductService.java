package com.innovatehub.inventorymgmt.services.stock;

import java.util.List;

import com.innovatehub.inventorymgmt.common.model.stock.Product;

public interface ProductService {
	Product getProduct(Long productId);

	List<Product> getAllProducts();

	Long saveProduct(Product product);
}
