package com.innovatehub.inventorymgmt.services.pos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovatehub.inventorymgmt.common.model.customer.Customer;
import com.innovatehub.inventorymgmt.common.model.pos.Sale;
import com.innovatehub.inventorymgmt.common.model.pos.SaleDetail;
import com.innovatehub.inventorymgmt.common.model.stock.Product;
import com.innovatehub.inventorymgmt.common.model.stock.SKU;
import com.innovatehub.inventorymgmt.common.model.stock.Stock;
import com.innovatehub.inventorymgmt.common.repository.customer.CustomerRepository;
import com.innovatehub.inventorymgmt.common.repository.pos.SaleDetailRepository;
import com.innovatehub.inventorymgmt.common.repository.pos.SaleRepository;
//import com.innovatehub.inventorymgmt.common.repository.stock.PriceRepository;
import com.innovatehub.inventorymgmt.common.repository.stock.SKURepository;
import com.innovatehub.inventorymgmt.common.repository.stock.StockRepository;
import com.innovatehub.inventorymgmt.services.ServiceBase;

@Service
public class SaleServiceImpl extends ServiceBase implements SaleService {

	SaleRepository saleRepo;

	SaleDetailRepository saleDetailsRepo;

	SKURepository skuRepo;

	// PriceRepository priceRepo;

	CustomerRepository customerRepo;

	StockRepository stockRepo;

	public SaleRepository getSaleRepo() {
		return saleRepo;
	}

	@Autowired
	public void setSaleRepo(SaleRepository saleRepo) {
		this.saleRepo = saleRepo;
	}

	public SaleDetailRepository getSaleDetailsRepo() {
		return saleDetailsRepo;
	}

	@Autowired
	public void setSaleDetailsRepo(SaleDetailRepository saleDetailsRepo) {
		this.saleDetailsRepo = saleDetailsRepo;
	}

	public SKURepository getSkuRepo() {
		return skuRepo;
	}

	@Autowired
	public void setSkuRepo(SKURepository skuRepo) {
		this.skuRepo = skuRepo;
	}

	/*
	 * public PriceRepository getPriceRepo() { return priceRepo; }
	 * 
	 * @Autowired public void setPriceRepo(PriceRepository priceRepo) {
	 * this.priceRepo = priceRepo; }
	 */

	public CustomerRepository getCustomerRepo() {
		return customerRepo;
	}

	@Autowired
	public void setCustomerRepo(CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
	}

	public StockRepository getStockRepo() {
		return stockRepo;
	}

	@Autowired
	public void setStockRepo(StockRepository stockRepo) {
		this.stockRepo = stockRepo;
	}

	@Override
	@Transactional
	public Sale getSale(Long saleId) {
		/*
		 * Session session = this.getSessionFactory().getObject().getCurrentSession();
		 * Criteria saleCritieria =
		 * session.createCriteria(com.innovatehub.inventorymgmt.common.entity.pos.Sale.
		 * class); saleCritieria = saleCritieria.add(Restrictions.idEq(saleId));
		 * 
		 * com.innovatehub.inventorymgmt.common.entity.pos.Sale sale =
		 * (com.innovatehub.inventorymgmt.common.entity.pos.Sale)
		 * saleCritieria.list().get(0);
		 */
		return this.convertSaleEntityToModel(this.getSaleRepo().findOne(saleId));
	}

	@Override
	public Long saveSale(Sale saleModel) {
		com.innovatehub.inventorymgmt.common.entity.pos.Sale saleEntity = this.convertSaleModelToEntity(saleModel);

		this.populateAuditInfo(saleEntity);

		com.innovatehub.inventorymgmt.common.entity.pos.Sale saleEntityPersisted = this.getSaleRepo().save(saleEntity);

		BigDecimal profitSaleDetailItems = new BigDecimal(0);
		for (com.innovatehub.inventorymgmt.common.entity.pos.SaleDetail saleDetailEntity : saleEntity
				.getSaleDetails()) {
			saleDetailEntity.setSale(saleEntityPersisted);
			this.getSaleDetailsRepo().save(saleDetailEntity);

			//ToDo: Get from UI.
			saleDetailEntity.setQuantity(1L);
			
			// ToDo: update the quantity with the input from the UI.
			// ToDo: Update the quantity in the Stock table.
			// ToDo: Parallel Checkout. Do an additional validation to see that
			// Quantity_Available > 0 before checkout.
			com.innovatehub.inventorymgmt.common.entity.stock.SKU skuEntity = this.getSkuRepo()
					.findOne(saleDetailEntity.getSku().getSkuId());
			profitSaleDetailItems = profitSaleDetailItems
					.add((saleDetailEntity.getSellPrice().subtract(saleDetailEntity.getStock().getUnitPrice()))
							.multiply(new BigDecimal(saleDetailEntity.getQuantity())));

			skuEntity.setQuantityAvailable(skuEntity.getQuantityAvailable() - 1);
			this.getSkuRepo().save(skuEntity);

			// Update the Units sold column of Stock table.
			com.innovatehub.inventorymgmt.common.entity.stock.Stock stockSelected = this.getStockRepo()
					.getOne(saleDetailEntity.getStock().getStockId());
			Long unitsSold = stockSelected.getUnitsSold() == null ? 0L : stockSelected.getUnitsSold();

			stockSelected.setUnitsSold(unitsSold + 1);
			this.getStockRepo().save(stockSelected);
		}

		saleEntityPersisted.setProfit(profitSaleDetailItems);
		this.getSaleRepo().save(saleEntityPersisted);

		return saleEntityPersisted.getId();
	}

	private com.innovatehub.inventorymgmt.common.entity.pos.Sale convertSaleModelToEntity(Sale saleModel) {
		com.innovatehub.inventorymgmt.common.entity.pos.Sale saleEntity = new com.innovatehub.inventorymgmt.common.entity.pos.Sale();

		BeanUtils.copyProperties(saleModel.getSaleDetails().get(0).getSale(), saleEntity);

		// Create new Array.
		saleEntity.setSaleDetails(new HashSet<com.innovatehub.inventorymgmt.common.entity.pos.SaleDetail>());

		List<SaleDetail> saleDetailsModel = saleModel.getSaleDetails();
		for (SaleDetail saleDetailModel : saleDetailsModel) {
			saleEntity.getSaleDetails().add(this.convertSaleDetailModelToEntity(saleDetailModel));
		}

		com.innovatehub.inventorymgmt.common.entity.customer.Customer customerEntity = this.getCustomerRepo()
				.findOne(saleModel.getCustomer().getCustomerId());
		saleEntity.setCustomer(customerEntity);

		return saleEntity;
	}

	private Sale convertSaleEntityToModel(com.innovatehub.inventorymgmt.common.entity.pos.Sale saleEntity) {
		Sale saleModel = new Sale();
		Customer customerModel = new Customer();

		BeanUtils.copyProperties(saleEntity, saleModel);

		BeanUtils.copyProperties(saleEntity.getCustomer(), customerModel);
		saleModel.setCustomer(customerModel);

		saleModel.setSaleDetails(new ArrayList<SaleDetail>());

		Set<com.innovatehub.inventorymgmt.common.entity.pos.SaleDetail> saleDetailsEntity = saleEntity.getSaleDetails();

		for (com.innovatehub.inventorymgmt.common.entity.pos.SaleDetail saleDetailEntity : saleDetailsEntity) {
			saleModel.getSaleDetails().add(this.convertSaleDetailEntityToModel(saleDetailEntity));
		}

		return saleModel;
	}

	private com.innovatehub.inventorymgmt.common.entity.pos.SaleDetail convertSaleDetailModelToEntity(
			SaleDetail saleDetailModel) {
		com.innovatehub.inventorymgmt.common.entity.pos.SaleDetail saleDetailEntity = new com.innovatehub.inventorymgmt.common.entity.pos.SaleDetail();

		BeanUtils.copyProperties(saleDetailModel, saleDetailEntity);

		com.innovatehub.inventorymgmt.common.entity.stock.SKU skuEntity = this.getSkuRepo()
				.findOne(saleDetailModel.getSku().getSkuId());
		saleDetailEntity.setSku(skuEntity);

		// com.innovatehub.inventorymgmt.common.entity.stock.Price priceEntity =
		// this.getPriceRepo().findOne(saleDetailModel.getSku().getPrice());
		saleDetailEntity.setSellPrice(skuEntity.getSellPrice());

		/*
		 * com.innovatehub.inventorymgmt.common.entity.pos.Sale saleEntity = new
		 * com.innovatehub.inventorymgmt.common.entity.pos.Sale();
		 * BeanUtils.copyProperties(saleDetailModel.getSale(), saleEntity);
		 * saleDetailEntity.setSale(saleEntity);
		 */

		com.innovatehub.inventorymgmt.common.entity.stock.Stock stockEntity = this.getStockRepo()
				.findOne(saleDetailModel.getSelectedStock().getStockId());

		saleDetailEntity.setStock(stockEntity);

		saleDetailEntity.setDiscountPct(new BigDecimal(0.0));
		saleDetailEntity.setTotal(new BigDecimal(0.0));
		saleDetailEntity.setQuantity(0L);
		this.populateAuditInfo(saleDetailEntity);

		return saleDetailEntity;
	}

	private SaleDetail convertSaleDetailEntityToModel(
			com.innovatehub.inventorymgmt.common.entity.pos.SaleDetail saleDetailEntity) {
		SaleDetail saleDetailModel = new SaleDetail();

		BeanUtils.copyProperties(saleDetailEntity, saleDetailModel);

		SKU skuModel = new SKU();
		BeanUtils.copyProperties(saleDetailEntity.getSku(), skuModel);
		saleDetailModel.setSku(skuModel);

		Product productModel = new Product();
		BeanUtils.copyProperties(saleDetailEntity.getSku().getProduct(), productModel);
		saleDetailModel.getSku().setSelectedProduct(productModel);

		/*
		 * Price priceModel = new Price();
		 * BeanUtils.copyProperties(saleDetailEntity.getPrice(), priceModel);
		 * saleDetailModel.setPrice(priceModel);
		 */
		Stock stockModel = new Stock();
		BeanUtils.copyProperties(saleDetailEntity.getStock().getStockId(), stockModel);
		saleDetailModel.setSelectedStock(stockModel);

		Sale saleModel = new Sale();
		BeanUtils.copyProperties(saleDetailEntity.getSale(), saleModel);
		saleDetailModel.setSale(saleModel);

		return saleDetailModel;
	}
}
