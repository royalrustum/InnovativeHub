package com.innovatehub.inventorymgmt.services.stock;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovatehub.inventorymgmt.common.model.stock.Product;
import com.innovatehub.inventorymgmt.common.model.stock.SKU;
import com.innovatehub.inventorymgmt.common.repository.stock.ProductRepository;
import com.innovatehub.inventorymgmt.common.repository.stock.SKURepository;

@Service
@Transactional
public class SKUServiceImpl implements SKUService {

	@Autowired
	SKURepository skuRepo;

	@Autowired
	ProductRepository productRepo;
	
	public SKURepository getSkuRepo() {
		return skuRepo;
	}

	public void setSkuRepo(SKURepository skuRepo) {
		this.skuRepo = skuRepo;
	}

	public ProductRepository getProductRepo() {
		return productRepo;
	}

	public void setProductRepo(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}
	
	@Override
	public Long saveSKU(SKU sku) {
		com.innovatehub.inventorymgmt.common.entity.stock.SKU skuEntity = new com.innovatehub.inventorymgmt.common.entity.stock.SKU();
		BeanUtils.copyProperties(sku, skuEntity);

		if (sku.getSelectedProduct() != null) {
			// Get the session tracked object from hibernate. Product
			// will exist prior to sku creation
			com.innovatehub.inventorymgmt.common.entity.stock.Product productEntity = this.getProductRepo()
					.findOne(sku.getSelectedProduct().getProductId());

			skuEntity.setProduct(productEntity);
		}
		
		return this.getSkuRepo().save(skuEntity).getSkuId();
	}

	@Override
	public SKU getSKU(Long skuId) {
		com.innovatehub.inventorymgmt.common.entity.stock.SKU skuEntity= this.getSkuRepo().findOne(skuId);
		SKU sku = new SKU();
		
		BeanUtils.copyProperties(skuEntity, sku);
		
		Product product = new Product();
		BeanUtils.copyProperties(skuEntity.getProduct(), product);
		
		sku.setSelectedProduct(product);
		
		return sku;
	}
}