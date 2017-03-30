package com.innovatehub.inventorymgmt.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.innovatehub.inventorymgmt.site.util.SessionCache;

@Controller
public class HomeController {

	SessionCache sessionCache;
	
	public SessionCache getSessionCache() {
		return sessionCache;
	}

	@Autowired
	public void setSessionCache(SessionCache sessionCache) {
		this.sessionCache = sessionCache;
	}

	@RequestMapping("/home")
	public String home() {
		
		this.getSessionCache().initialize();
		
		return "dashboard/home";
	}
}
