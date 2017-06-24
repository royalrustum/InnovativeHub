package com.innovatehub.inventorymgmt.site.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.innovatehub.inventorymgmt.common.util.SiteConstants;
import com.innovatehub.inventorymgmt.services.pos.HomeService;

@Controller
public class HomeController extends BaseController {

	@Autowired
	MessageSource messageSource;

	@Autowired
	HomeService homeService;

	private static final String MODEL_ATTRIB_HOME = "home";

	public HomeService getHomeService() {
		return homeService;
	}

	public void setHomeService(HomeService homeService) {
		this.homeService = homeService;
	}
	
	@RequestMapping(SiteConstants.PAGE_URI_HOME)
	public String home(Locale locale, Model model) {
		model.addAttribute(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE,
				messageSource.getMessage(SiteConstants.PAGE_TITLE_HOME, null, locale));

		model.addAttribute(MODEL_ATTRIB_HOME, this.getHomeService().getHomeScreenInfo());

		return SiteConstants.VIEW_NAME_HOME;
	}
}
