package com.innovatehub.inventorymgmt.services.stock;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.innovatehub.inventorymgmt.common.model.stock.Stock;
import com.innovatehub.inventorymgmt.common.repository.stock.StockRepository;

@Service
public class StockServiceImpl implements StockService {

	StockRepository stockRepo;

	public StockRepository getStockRepo() {
		return stockRepo;
	}

	public void setStockRepo(StockRepository stockRepo) {
		this.stockRepo = stockRepo;
	}

	@Override
	public Stock getStock(Long stockId) {
		Stock stockModel = new Stock();

		com.innovatehub.inventorymgmt.common.entity.stock.Stock stockEntity = this.getStockRepo().findOne(stockId);
		BeanUtils.copyProperties(stockEntity, stockModel);

		com.innovatehub.inventorymgmt.common.model.stock.SKU skuModel = new com.innovatehub.inventorymgmt.common.model.stock.SKU();
		BeanUtils.copyProperties(stockEntity.getSku(), skuModel);

		stockModel.setSku(skuModel);

		return stockModel;
	}

	@Override
	public Long saveStock(Stock stock) {
		com.innovatehub.inventorymgmt.common.entity.stock.Stock stockEntity = new com.innovatehub.inventorymgmt.common.entity.stock.Stock();
		BeanUtils.copyProperties(stock, stockEntity);

		com.innovatehub.inventorymgmt.common.entity.stock.SKU skuEntity = new com.innovatehub.inventorymgmt.common.entity.stock.SKU();
		BeanUtils.copyProperties(stock.getSku(), skuEntity);

		stockEntity.setSku(skuEntity);

		return this.getStockRepo().save(stockEntity).getStockId();
	}

}
