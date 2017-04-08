package com.innovatehub.inventorymgmt.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.innovatehub.inventorymgmt.common.util.SiteConstants;

@Controller
public class LoginController extends BaseController {
	
	@RequestMapping(SiteConstants.PAGE_URI_LOGIN)
	public String login() {
		return SiteConstants.VIEW_NAME_LOGIN;		
	}

}
