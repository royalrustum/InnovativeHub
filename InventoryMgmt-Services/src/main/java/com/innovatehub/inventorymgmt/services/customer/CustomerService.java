package com.innovatehub.inventorymgmt.services.customer;

import java.util.List;

import com.innovatehub.inventorymgmt.common.model.customer.Customer;

public interface CustomerService {
	Long saveCustomer(Customer customer);
	Customer getCustomer(Long customerId);
	List<Customer> getAllCustomers();
}
