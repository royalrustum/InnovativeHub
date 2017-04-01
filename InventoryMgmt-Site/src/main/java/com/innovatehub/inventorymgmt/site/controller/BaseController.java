package com.innovatehub.inventorymgmt.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.innovatehub.inventorymgmt.site.util.SessionCache;

public abstract class BaseController {
	protected SessionCache sessionCache;
	
	@ModelAttribute("sessionCache")
	public SessionCache getSessionCache() {
		return sessionCache;
	}

	@Autowired
	public void setSessionCache(SessionCache sessionCache) {
		this.sessionCache = sessionCache;
	}
}
