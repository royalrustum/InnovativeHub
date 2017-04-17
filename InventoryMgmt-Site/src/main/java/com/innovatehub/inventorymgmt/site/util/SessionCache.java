package com.innovatehub.inventorymgmt.site.util;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.innovatehub.inventorymgmt.common.entity.security.Screen;
import com.innovatehub.inventorymgmt.services.security.SecurityService;
import com.innovatehub.inventorymgmt.site.model.util.LeftNavSection;

@Component
//@Scope("session")
public class SessionCache {
	private List<Screen> authorizedScreens;
	
	private List<LeftNavSection> leftNavSections;
	
	@Autowired
	private SecurityService securityService;
	
	public List<LeftNavSection> getLeftNavSections() {
		return leftNavSections;
	}

	public void setLeftNavSections(List<LeftNavSection> leftNavSections) {
		this.leftNavSections = leftNavSections;
	}

	public List<Screen> getAuthorizedScreens() {
		return authorizedScreens;
	}

	public void setAuthorizedScreens(List<Screen> authorizedScreens) {
		this.authorizedScreens = authorizedScreens;
	}
	
	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
	
	public void initialize() {
		String currentUserName = securityService.getCurrentUserName();
		this.setAuthorizedScreens(this.securityService.getUserScreens(currentUserName));
		
		this.setLeftNavSections(GUIUtilHelper.PopulateLeftNavigation(this.getAuthorizedScreens()));
	}
}
