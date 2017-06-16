package com.innovatehub.inventorymgmt.services.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.innovatehub.inventorymgmt.common.entity.security.Role;
import com.innovatehub.inventorymgmt.common.entity.security.Role_Screen;
import com.innovatehub.inventorymgmt.common.entity.security.Screen;
import com.innovatehub.inventorymgmt.common.entity.security.ScreenCategory;
import com.innovatehub.inventorymgmt.common.entity.security.User;
import com.innovatehub.inventorymgmt.common.repository.security.RoleRepository;
import com.innovatehub.inventorymgmt.common.repository.security.Role_ScreenRepository;
import com.innovatehub.inventorymgmt.common.repository.security.ScreenCategoryRepository;
import com.innovatehub.inventorymgmt.common.repository.security.ScreenRepository;
import com.innovatehub.inventorymgmt.common.repository.security.UserRepository;
import com.innovatehub.inventorymgmt.common.util.SiteConstants;

@Component
public class DatabaseInitializer {

	private UserRepository userRepository;

	private Role_ScreenRepository roleScreenRepo;

	private RoleRepository roleRepository;

	private ScreenRepository screenRepository;

	private ScreenCategoryRepository screenCatRepo;

	@Autowired
	public DatabaseInitializer(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Role_ScreenRepository getRoleScreenRepo() {
		return roleScreenRepo;
	}

	@Autowired
	public void setRoleScreenRepo(Role_ScreenRepository roleScreenRepo) {
		this.roleScreenRepo = roleScreenRepo;
	}

	public RoleRepository getRoleRepository() {
		return roleRepository;
	}

	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public ScreenRepository getScreenRepository() {
		return screenRepository;
	}

	@Autowired
	public void setScreenRepository(ScreenRepository screenRepository) {
		this.screenRepository = screenRepository;
	}

	public ScreenCategoryRepository getScreenCatRepo() {
		return screenCatRepo;
	}

	@Autowired
	public void setScreenCatRepo(ScreenCategoryRepository screenCatRepo) {
		this.screenCatRepo = screenCatRepo;
	}

	@PostConstruct
	@Transactional(readOnly = false)
	private void populateData() {
		/*populateUserAndRoleData();
		populateLeftNavScreens();*/
	}

	private void populateUserAndRoleData() {
		// Populate Admin role and their screens.
		Role adminRole = populateRoles();

		this.populateLeftNavScreens();
		
		List<Screen> allScreens = this.getScreenRepository().findAll();
		for (Screen screen : allScreens) {
			Role_Screen roleScreen = new Role_Screen();
			roleScreen.setScreen(screen);
			roleScreen.setRole(adminRole);
			this.getRoleScreenRepo().save(roleScreen);
		}

		this.populateUsers(adminRole);
	}

	private void populateUsers(Role adminRole) {
		Set<Role> roles = new HashSet<Role>();
		roles.add(adminRole);

		User adminUser = new User();
		adminUser.setUserName("admin");
		adminUser.setPassword("admin");
		adminUser.setRoles(roles);

		this.userRepository.save(adminUser);
	}

	private Role populateRoles() {
		Role adminRole = new Role();
		adminRole.setName("ADMIN_ROLE");

		adminRole = this.getRoleRepository().save(adminRole);
		return adminRole;
	}

	private ArrayList<Screen> populateLeftNavScreens() {
		ArrayList<Screen> adminScreens = new ArrayList<Screen>();
		adminScreens.addAll(this.populateCustomerCategoryNavItems());
		adminScreens.addAll(this.populateStockCategoryNavItems());
		adminScreens.addAll(this.populatePOSNavItems());
		
		/*
		 * Screen customerScreen = new Screen();
		 * customerScreen.setScreenName("Customers");
		 * customerScreen.setScreenIconName("fa-user-o");
		 * customerScreen.setUrl("/admin/customers");
		 * customerScreen.setScreenCategory(adminCategory);
		 * adminScreens.add(customerScreen);
		 */

		return adminScreens;
	}

	private List<Screen> populateStockCategoryNavItems() {
		ArrayList<Screen> stockCatScreens = new ArrayList<Screen>();
		
		ScreenCategory stockCategory = new ScreenCategory();
		stockCategory.setName("Stock");
		stockCategory.setIconName("fa-cubes");

		stockCategory = this.getScreenCatRepo().save(stockCategory);

		Screen categoryScreen = new Screen();
		categoryScreen.setScreenName("Product Category");
		categoryScreen.setScreenIconName("fa-th-list");
		categoryScreen.setUrl(SiteConstants.PAGE_URI_STOCK_PRODUCT_CATEGORY_CREATE);
		categoryScreen.setScreenCategory(stockCategory);
		this.getScreenRepository().save(categoryScreen);
		stockCatScreens.add(categoryScreen);
		
		Screen productScreen = new Screen();
		productScreen.setScreenName("Product");
		productScreen.setScreenIconName("fa-cube");
		productScreen.setUrl(SiteConstants.PAGE_URI_STOCK_PRODUCT_CREATE);
		productScreen.setScreenCategory(stockCategory);
		this.getScreenRepository().save(productScreen);
		stockCatScreens.add(productScreen);
		
		Screen skuScreen = new Screen();
		skuScreen.setScreenName("SKU");
		skuScreen.setScreenIconName("fa-tags");
		skuScreen.setUrl(SiteConstants.PAGE_URI_STOCK_SKU_CREATE);
		skuScreen.setScreenCategory(stockCategory);
		this.getScreenRepository().save(skuScreen);
		stockCatScreens.add(skuScreen);
		
		Screen stockScreen = new Screen();
		stockScreen.setScreenName("Stock Diary");
		stockScreen.setScreenIconName("fa-truck");
		stockScreen.setUrl(SiteConstants.PAGE_URI_STOCK_STOCK_CREATE);
		stockScreen.setScreenCategory(stockCategory);
		this.getScreenRepository().save(stockScreen);
		stockCatScreens.add(stockScreen);
		
		return stockCatScreens;
	}
	
	private List<Screen> populateCustomerCategoryNavItems() {
		ArrayList<Screen> customerCatScreens = new ArrayList<Screen>();
		
		ScreenCategory customerCategory = new ScreenCategory();
		customerCategory.setName("Customer");
		customerCategory.setIconName("fa-address-card");

		customerCategory = this.getScreenCatRepo().save(customerCategory);

		Screen categoryScreen = new Screen();
		categoryScreen.setScreenName("Profile");
		categoryScreen.setScreenIconName("fa-address-book-o");
		categoryScreen.setUrl(SiteConstants.PAGE_URI_CUSTOMER_PROFILE_CREATE);
		categoryScreen.setScreenCategory(customerCategory);
		this.getScreenRepository().save(categoryScreen);
		
		customerCatScreens.add(categoryScreen);
		return 	customerCatScreens;
	}
	
	private List<Screen> populatePOSNavItems() {
		ArrayList<Screen> posCatScreens = new ArrayList<Screen>();
		
		ScreenCategory posCategory = new ScreenCategory();
		posCategory.setName("Sale");
		posCategory.setIconName("fa-shopping-cart");

		posCategory = this.getScreenCatRepo().save(posCategory);

		Screen categoryScreen = new Screen();
		categoryScreen.setScreenName("Checkout");
		categoryScreen.setScreenIconName("fa-money");
		categoryScreen.setUrl(SiteConstants.PAGE_URI_POS_CHECKOUT_CREATE);
		categoryScreen.setScreenCategory(posCategory);
		this.getScreenRepository().save(categoryScreen);
		
		posCatScreens.add(categoryScreen);
		return 	posCatScreens;
	}
}
