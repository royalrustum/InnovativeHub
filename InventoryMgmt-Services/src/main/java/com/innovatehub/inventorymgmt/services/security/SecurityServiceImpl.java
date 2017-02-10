package com.innovatehub.inventorymgmt.services.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityServiceImpl implements SecurityService {

	@Override
	public String getCurrentUserName() {
		Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
		if(details instanceof UserDetails) {
			return ((UserDetails)details).getUsername();
		}
		
		return null;
	}

}
