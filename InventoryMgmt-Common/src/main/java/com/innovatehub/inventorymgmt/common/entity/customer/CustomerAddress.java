package com.innovatehub.inventorymgmt.common.entity.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.innovatehub.inventorymgmt.common.entity.EntityBase;

@Entity
@Table(name = "CUSTOMER_ADDRESS_T")
public class CustomerAddress extends EntityBase {
	private Long id;
	private Customer customer;
	private Address address;

	@Id
	@Column(name = "CUSTOMER_ADDRESS_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CUSTOMER_ID")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ADDRESS_ID")
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
