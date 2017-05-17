package com.innovatehub.inventorymgmt.common.repository.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innovatehub.inventorymgmt.common.entity.customer.CustomerAddress;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {
	CustomerAddress findByCustomerCustomerId(Long customerId);
}
