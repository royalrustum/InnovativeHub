package com.innovatehub.inventorymgmt.services.pos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovatehub.inventorymgmt.common.entity.pos.Sale;
import com.innovatehub.inventorymgmt.common.model.pos.Home;
import com.innovatehub.inventorymgmt.common.repository.customer.CustomerRepository;
import com.innovatehub.inventorymgmt.common.repository.pos.SaleRepository;
import com.innovatehub.inventorymgmt.common.repository.stock.SKURepository;

@Service
public class HomeServiceImpl implements HomeService {
	@Autowired
	private SaleRepository saleRepo;

	@Autowired
	private SKURepository skuRepo;

	@Autowired
	private CustomerRepository customerRepo;
	
	public SaleRepository getSaleRepo() {
		return saleRepo;
	}

	public void setSaleRepo(SaleRepository saleRepo) {
		this.saleRepo = saleRepo;
	}

	public SKURepository getSkuRepo() {
		return skuRepo;
	}

	public void setSkuRepo(SKURepository skuRepo) {
		this.skuRepo = skuRepo;
	}

	public CustomerRepository getCustomerRepo() {
		return customerRepo;
	}

	public void setCustomerRepo(CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
	}
	
	@Override
	public Home getHomeScreenInfo() {
		Home home = new Home();

		this.populateTilesInfo(home);
		return home;
	}

	private void populateTilesInfo(Home home) {
		Date startDate = Date.from(LocalDate.now().atTime(LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant());
		Date endDate = Date.from(LocalDate.now().atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());

		// Populate Sales for current time period.
		List<Sale> todaySales = this.getSaleRepo().findByRecordCreatedDateBetween(startDate, endDate);
		Double todaySaleTotal = todaySales.stream().mapToDouble(sale -> sale.getTotal().doubleValue()).sum();
		home.setSaleTotal(new BigDecimal(todaySaleTotal));

		// Stock Count.
		Long availableStockCount = this.getSkuRepo().findAll().stream().mapToLong(sku -> sku.getQuantityAvailable())
				.sum();
		home.setStockCount(availableStockCount);

		// Populate Profit for current time period.
		home.setProfit(new BigDecimal(todaySales.stream().mapToDouble(sale -> sale.getProfit().doubleValue()).sum()));
		
		// Populate total customer count.
		home.setCustomerCount(BigDecimal.valueOf(this.getCustomerRepo().count()));
	}
}
