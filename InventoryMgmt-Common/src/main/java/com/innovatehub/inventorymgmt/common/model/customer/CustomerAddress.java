package com.innovatehub.inventorymgmt.common.model.customer;

import com.innovatehub.inventorymgmt.common.model.ModelBase;

public class CustomerAddress extends ModelBase {
	private Long id;
	private Customer customer;
	private Address address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
