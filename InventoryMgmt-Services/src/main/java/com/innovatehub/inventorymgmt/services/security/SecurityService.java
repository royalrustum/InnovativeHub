package com.innovatehub.inventorymgmt.services.security;

import java.util.List;
import java.util.Set;

import com.innovatehub.inventorymgmt.common.entity.security.Role;
import com.innovatehub.inventorymgmt.common.entity.security.Screen;

public interface SecurityService {

	public String getCurrentUserName();
	public Set<Role> getUserRoles(String userName);
	public List<Screen> getUserScreens(Set<Role> roles);
	public List<Screen> getUserScreens(String userName);
}
