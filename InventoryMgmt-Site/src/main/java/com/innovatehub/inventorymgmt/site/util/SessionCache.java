package com.innovatehub.inventorymgmt.site.util;

import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.innovatehub.inventorymgmt.common.model.security.Screen;
import com.innovatehub.inventorymgmt.services.security.SecurityService;
import com.innovatehub.inventorymgmt.site.model.LeftNavSection;

@Component
//@Scope("session")
public class SessionCache {
	private Set<Screen> authorizedScreens;
	
	private List<LeftNavSection> leftNavSections;
	
	@Autowired
	private SecurityService securityService;
	
	public List<LeftNavSection> getLeftNavSections() {
		return leftNavSections;
	}

	public void setLeftNavSections(List<LeftNavSection> leftNavSections) {
		this.leftNavSections = leftNavSections;
	}

	public Set<Screen> getAuthorizedScreens() {
		return authorizedScreens;
	}

	public void setAuthorizedScreens(Set<Screen> authorizedScreens) {
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
