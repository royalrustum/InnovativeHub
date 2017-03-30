package com.innovatehub.inventorymgmt.services.security;

import java.util.Set;

import com.innovatehub.inventorymgmt.common.model.security.Role;
import com.innovatehub.inventorymgmt.common.model.security.Screen;

public interface SecurityService {

	public String getCurrentUserName();
	public Set<Role> getUserRoles(String userName);
	public Set<Screen> getUserScreens(Set<Role> roles);
	public Set<Screen> getUserScreens(String userName);
}
