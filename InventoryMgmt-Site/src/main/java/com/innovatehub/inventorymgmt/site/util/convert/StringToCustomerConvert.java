package com.innovatehub.inventorymgmt.site.util.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.innovatehub.inventorymgmt.common.model.customer.Customer;
import com.innovatehub.inventorymgmt.common.model.stock.Product;
import com.innovatehub.inventorymgmt.common.model.stock.SKU;
import com.innovatehub.inventorymgmt.services.customer.CustomerService;
import com.innovatehub.inventorymgmt.services.stock.ProductService;
import com.innovatehub.inventorymgmt.services.stock.SKUService;

@Component
public class StringToCustomerConvert implements Converter<String, Customer> {

	private CustomerService customerService;

	public StringToCustomerConvert(CustomerService customerService) {
		this.setCustomerService(customerService);
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	public Customer convert(String customer) {
		return this.getCustomerService().getCustomer(Long.valueOf(customer));
	}

}
