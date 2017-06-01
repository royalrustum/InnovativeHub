package com.innovatehub.inventorymgmt.services.customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovatehub.inventorymgmt.common.entity.customer.CustomerAddress;
import com.innovatehub.inventorymgmt.common.model.customer.Address;
import com.innovatehub.inventorymgmt.common.model.customer.Customer;
import com.innovatehub.inventorymgmt.common.repository.customer.AddressRepository;
import com.innovatehub.inventorymgmt.common.repository.customer.CustomerAddressRepository;
import com.innovatehub.inventorymgmt.common.repository.customer.CustomerRepository;
import com.innovatehub.inventorymgmt.services.ServiceBase;

@Service
public class CustomerServiceImpl extends ServiceBase implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private CustomerAddressRepository customerAddressRepo;

	@Autowired
	private AddressRepository addressRepo;

	public CustomerRepository getCustomerRepo() {
		return customerRepo;
	}

	public void setCustomerRepo(CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
	}

	public CustomerAddressRepository getCustomerAddressRepo() {
		return customerAddressRepo;
	}

	public void setCustomerAddressRepo(CustomerAddressRepository customerAddressRepo) {
		this.customerAddressRepo = customerAddressRepo;
	}

	public AddressRepository getAddressRepo() {
		return addressRepo;
	}

	public void setAddressRepo(AddressRepository addressRepo) {
		this.addressRepo = addressRepo;
	}

	@Override
	public Long saveCustomer(Customer customer) {
		// Save Customer.
		com.innovatehub.inventorymgmt.common.entity.customer.Customer customerEntity = new com.innovatehub.inventorymgmt.common.entity.customer.Customer();
		BeanUtils.copyProperties(customer, customerEntity);

		this.getCustomerRepo().save(customerEntity);

		// Save Address.
		com.innovatehub.inventorymgmt.common.entity.customer.Address addressEntity = new com.innovatehub.inventorymgmt.common.entity.customer.Address();
		BeanUtils.copyProperties(customer.getAddress(), addressEntity);

		this.getAddressRepo().save(addressEntity);

		// Save Customer Address.
		com.innovatehub.inventorymgmt.common.entity.customer.CustomerAddress custAddressEntity = new com.innovatehub.inventorymgmt.common.entity.customer.CustomerAddress();
		custAddressEntity.setAddress(addressEntity);
		custAddressEntity.setCustomer(customerEntity);

		this.getCustomerAddressRepo().save(custAddressEntity);

		return customerEntity.getCustomerId();
	}

	@Override
	public Customer getCustomer(Long customerId) {
		// Get Customer.
		Customer customerModel = new Customer();

		BeanUtils.copyProperties(this.getCustomerRepo().findOne(customerId), customerModel);

		// Get Address.
		CustomerAddress customerAddress = this.getCustomerAddressRepo().findByCustomerCustomerId(customerId);

		Address addressModel = new Address();
		BeanUtils.copyProperties(customerAddress.getAddress(), addressModel);

		customerModel.setAddress(addressModel);

		return customerModel;
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> allCustomers = new ArrayList<Customer>();

		for(com.innovatehub.inventorymgmt.common.entity.customer.Customer customerEntity : 
			this.getCustomerRepo().findAll())
		{
			allCustomers.add(this.convertCustomerEntityToModel(customerEntity));
		}
		
		return allCustomers;
	}

	private Customer convertCustomerEntityToModel(
			com.innovatehub.inventorymgmt.common.entity.customer.Customer customerEntity) {
		Customer customerModel = new Customer();

		BeanUtils.copyProperties(customerEntity, customerModel);

		// Get Address.
		CustomerAddress customerAddress = this.getCustomerAddressRepo().findByCustomerCustomerId(customerEntity.getCustomerId());

		Address addressModel = new Address();
		BeanUtils.copyProperties(customerAddress.getAddress(), addressModel);

		customerModel.setAddress(addressModel);
		
		return customerModel;
	}

}
