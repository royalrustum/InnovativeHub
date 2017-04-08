package com.innovatehub.inventorymgmt.services.stock;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovatehub.inventorymgmt.common.entity.stock.ProductCategory;
import com.innovatehub.inventorymgmt.common.repository.stock.ProductCategoryRepository;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	ProductCategoryRepository productCategoryRepo;

	public ProductCategoryRepository getProductCategoryRepo() {
		return productCategoryRepo;
	}

	public void setProductCategoryRepo(ProductCategoryRepository productCategoryRepo) {
		this.productCategoryRepo = productCategoryRepo;
	}

	@Override
	public int saveProductCategory(com.innovatehub.inventorymgmt.common.model.stock.ProductCategory productCategory) {
		ProductCategory prodCategoryEntity = new ProductCategory();
		BeanUtils.copyProperties(productCategory, prodCategoryEntity);
		return this.getProductCategoryRepo().save(prodCategoryEntity).getProductCategoryId();
	}

	@Override
	public com.innovatehub.inventorymgmt.common.model.stock.ProductCategory getProductCategory(int productCategoryId) {
		ProductCategory prodCategoryEntity = this.getProductCategoryRepo().findOne(productCategoryId);

		com.innovatehub.inventorymgmt.common.model.stock.ProductCategory productCategory = new com.innovatehub.inventorymgmt.common.model.stock.ProductCategory();
		BeanUtils.copyProperties(prodCategoryEntity, productCategory);
		return productCategory;

	}

}
