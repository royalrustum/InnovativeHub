package com.innovatehub.inventorymgmt.common.model.customer;

import org.hibernate.validator.constraints.NotEmpty;

import com.innovatehub.inventorymgmt.common.model.ModelBase;

public class Customer extends ModelBase {
	@NotEmpty
	private String firstName;
	
	@NotEmpty
	private String lastName;
	
	private String email;
	
	private String phone;
	
	private Long customerId;

	private Address address;
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
