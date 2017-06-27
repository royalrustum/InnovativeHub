package com.innovatehub.inventorymgmt.services.stock;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovatehub.inventorymgmt.common.model.stock.Product;
import com.innovatehub.inventorymgmt.common.model.stock.ProductCategory;
import com.innovatehub.inventorymgmt.common.model.stock.SKU;
import com.innovatehub.inventorymgmt.common.model.stock.Stock;
import com.innovatehub.inventorymgmt.common.repository.stock.ProductRepository;
import com.innovatehub.inventorymgmt.common.repository.stock.SKURepository;
import com.innovatehub.inventorymgmt.common.util.CommonUtilHelper;
import com.innovatehub.inventorymgmt.services.ServiceBase;

@Service
@Transactional
public class SKUServiceImpl extends ServiceBase implements SKUService {

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
		// com.innovatehub.inventorymgmt.common.entity.stock.Price priceEntity =
		// new com.innovatehub.inventorymgmt.common.entity.stock.Price();

		BeanUtils.copyProperties(sku, skuEntity);
		// BeanUtils.copyProperties(sku.getPrice(), priceEntity);

		// skuEntity.setPrice(priceEntity);

		if (sku.getSelectedProduct() != null) {
			// Get the session tracked object from hibernate. Product
			// will exist prior to sku creation
			com.innovatehub.inventorymgmt.common.entity.stock.Product productEntity = this.getProductRepo()
					.findOne(sku.getSelectedProduct().getProductId());

			skuEntity.setProduct(productEntity);
		}

		this.populateAuditInfo(skuEntity);
		// this.populateAuditInfo(priceEntity);

		return this.getSkuRepo().save(skuEntity).getSkuId();
	}

	@Override
	public SKU getSKU(Long skuId) {
		com.innovatehub.inventorymgmt.common.entity.stock.SKU skuEntity = this.getSkuRepo().findOne(skuId);
		SKU sku = new SKU();

		BeanUtils.copyProperties(skuEntity, sku);

		Long quantityAvailable = sku.getQuantityAvailable() == null ? 0 : sku.getQuantityAvailable();
		sku.setQuantityAvailable(quantityAvailable);

		Product product = new Product();
		BeanUtils.copyProperties(skuEntity.getProduct(), product);

		sku.setSelectedProduct(product);

		return sku;
	}

	@Override
	public List<SKU> getAllSKUForProduct(Long productId) {
		List<com.innovatehub.inventorymgmt.common.entity.stock.SKU> skuEntities = this.getSkuRepo()
				.findByProductProductId(productId);

		List<SKU> skuModels = new ArrayList<SKU>();
		for (com.innovatehub.inventorymgmt.common.entity.stock.SKU skuEntity : skuEntities) {
			skuModels.add(this.convertSKUEntityToModel(skuEntity));
		}

		return skuModels;
	}

	@Override
	public List<SKU> getAllSKUForAllProducts() {
		List<com.innovatehub.inventorymgmt.common.entity.stock.SKU> skuEntities = this.getSkuRepo().findAll();

		List<SKU> skuModels = new ArrayList<SKU>();
		for (com.innovatehub.inventorymgmt.common.entity.stock.SKU skuEntity : skuEntities) {
			skuModels.add(this.convertSKUEntityToModel(skuEntity));
		}

		return skuModels;
	}

	private SKU convertSKUEntityToModel(com.innovatehub.inventorymgmt.common.entity.stock.SKU skuEntity) {
		SKU skuModel = new SKU();
		BeanUtils.copyProperties(skuEntity, skuModel);

		Long quantityAvailable = skuModel.getQuantityAvailable() == null ? 0 : skuModel.getQuantityAvailable();
		skuModel.setQuantityAvailable(quantityAvailable);

		Product product = new Product();
		BeanUtils.copyProperties(skuEntity.getProduct(), product);

		if (skuEntity.getProduct().getProductImage() != null) {
			product.setProductImage(CommonUtilHelper.getByteArrayFromBlob(skuEntity.getProduct().getProductImage()));
		}

		ProductCategory productCat = new ProductCategory();
		BeanUtils.copyProperties(skuEntity.getProduct().getProductCategory(), productCat);

		product.setSelectedProdCategory(productCat);

		/*
		 * Price price = new Price();
		 * BeanUtils.copyProperties(skuEntity.getPrice(), price);
		 * skuModel.setPrice(price);
		 */

		List<Stock> stocksModel = new ArrayList<Stock>();
		for (com.innovatehub.inventorymgmt.common.entity.stock.Stock stockEntity : skuEntity.getStockList()) {
			Stock stockModel = new Stock();
			BeanUtils.copyProperties(stockEntity, stockModel);
			
			stocksModel.add(stockModel);
		}

		skuModel.setSelectedSKUStockList(stocksModel);
		skuModel.setSelectedProduct(product);

		return skuModel;
	}
}
