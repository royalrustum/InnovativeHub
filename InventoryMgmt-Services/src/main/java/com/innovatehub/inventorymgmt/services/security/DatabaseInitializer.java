package com.innovatehub.inventorymgmt.services.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.innovatehub.inventorymgmt.common.entity.security.Role;
import com.innovatehub.inventorymgmt.common.entity.security.Screen;
import com.innovatehub.inventorymgmt.common.entity.security.ScreenCategory;
import com.innovatehub.inventorymgmt.common.entity.security.User;
import com.innovatehub.inventorymgmt.common.repository.security.UserRepository;
import com.innovatehub.inventorymgmt.common.util.SiteConstants;

@Component
public class DatabaseInitializer {

	private UserRepository userRepository;

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public DatabaseInitializer(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@PostConstruct
	@Transactional(readOnly=false)
	private void populateData() {
		populateUserAndRoleData();
		populateScreenDataForAdminRole();
	}

	private void populateUserAndRoleData() {
		// Populate Admin role and their screens.
		Role adminRole = new Role();
		adminRole.setName("ADMIN_ROLE");
		adminRole.setScreens(this.populateScreenDataForAdminRole());
		
		Set<Role> roles = new HashSet<Role>();
		roles.add(adminRole);

		User adminUser = new User();
		adminUser.setUserName("admin");
		adminUser.setPassword("admin");
		adminUser.setRoles(roles);
		
		
		this.userRepository.save(adminUser);
	}
	
	private ArrayList<Screen> populateScreenDataForAdminRole() {
		ArrayList<Screen> adminScreens = new ArrayList<Screen>();
		
		ScreenCategory stockCategory = new ScreenCategory();
		stockCategory.setName("Stock");
		stockCategory.setIconName("fa-cubes");
		
		Screen categoryScreen = new Screen();
		categoryScreen.setScreenName("Product Category");
		categoryScreen.setScreenIconName("fa-th-list");
		categoryScreen.setUrl(SiteConstants.PAGE_URI_STOCK_PRODUCT_CATEGORY_CREATE);
		categoryScreen.setScreenCategory(stockCategory);
		adminScreens.add(categoryScreen);
		
		Screen productScreen = new Screen();
		productScreen.setScreenName("Product");
		productScreen.setScreenIconName("fa-tags");
		productScreen.setUrl(SiteConstants.PAGE_URI_STOCK_PRODUCT_CREATE);
		productScreen.setScreenCategory(stockCategory);
		adminScreens.add(productScreen);
		
		/*Screen customerScreen = new Screen();
		customerScreen.setScreenName("Customers");
		customerScreen.setScreenIconName("fa-user-o");
		customerScreen.setUrl("/admin/customers");
		customerScreen.setScreenCategory(adminCategory);
		adminScreens.add(customerScreen);*/
		
		return adminScreens;
	}
}
