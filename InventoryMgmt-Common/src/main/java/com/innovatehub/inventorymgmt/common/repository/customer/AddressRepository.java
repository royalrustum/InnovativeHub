package com.innovatehub.inventorymgmt.common.repository.customer;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innovatehub.inventorymgmt.common.entity.customer.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
