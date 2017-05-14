package com.innovatehub.inventorymgmt.services.stock;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovatehub.inventorymgmt.common.model.stock.Stock;
import com.innovatehub.inventorymgmt.common.repository.stock.SKURepository;
import com.innovatehub.inventorymgmt.common.repository.stock.StockRepository;
import com.innovatehub.inventorymgmt.services.ServiceBase;

@Service
public class StockServiceImpl extends ServiceBase implements StockService {

	StockRepository stockRepo;
	
	SKURepository skuRepo;

	public StockRepository getStockRepo() {
		return stockRepo;
	}

	@Autowired
	public void setStockRepo(StockRepository stockRepo) {
		this.stockRepo = stockRepo;
	}

	public SKURepository getSkuRepo() {
		return skuRepo;
	}

	@Autowired
	public void setSkuRepo(SKURepository skuRepo) {
		this.skuRepo = skuRepo;
	}
	
	@Override
	public Stock getStock(Long stockId) {
		Stock stockModel = new Stock();

		com.innovatehub.inventorymgmt.common.entity.stock.Stock stockEntity = this.getStockRepo().findOne(stockId);
		BeanUtils.copyProperties(stockEntity, stockModel);

		com.innovatehub.inventorymgmt.common.model.stock.SKU skuModel = new com.innovatehub.inventorymgmt.common.model.stock.SKU();
		BeanUtils.copyProperties(stockEntity.getSku(), skuModel);

		stockModel.setSku(skuModel);
		
		com.innovatehub.inventorymgmt.common.model.stock.Product productModel = new com.innovatehub.inventorymgmt.common.model.stock.Product();
		BeanUtils.copyProperties(stockEntity.getSku().getProduct(), productModel);
		
		stockModel.getSku().setSelectedProduct(productModel);
		
		com.innovatehub.inventorymgmt.common.model.stock.ProductCategory productCatModel = new com.innovatehub.inventorymgmt.common.model.stock.ProductCategory();
		BeanUtils.copyProperties(stockEntity.getSku().getProduct().getProductCategory(), productCatModel);
		
		stockModel.getSku().getSelectedProduct().setSelectedProdCategory(productCatModel);
		
		return stockModel;
	}

	@Override
	public Long saveStock(Stock stock) {
		com.innovatehub.inventorymgmt.common.entity.stock.Stock stockEntity = new com.innovatehub.inventorymgmt.common.entity.stock.Stock();
		BeanUtils.copyProperties(stock, stockEntity);

		if (stock.getSelectedSKU() != null) {
			// Get the session tracked object from hibernate. Product Category
			// will exist prior to product creation
			com.innovatehub.inventorymgmt.common.entity.stock.SKU skuEntity = this.getSkuRepo()
					.findOne(stock.getSelectedSKU().getSkuId());

			stockEntity.setSku(skuEntity);
			this.populateAuditInfo(skuEntity);
		}

		this.populateAuditInfo(stockEntity);
		
		return this.getStockRepo().save(stockEntity).getStockId();
	}

}
