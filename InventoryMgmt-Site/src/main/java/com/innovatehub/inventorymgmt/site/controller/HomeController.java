package com.innovatehub.inventorymgmt.site.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.innovatehub.inventorymgmt.common.util.SiteConstants;

@Controller
public class HomeController extends BaseController {
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(SiteConstants.PAGE_URI_HOME)
	public ModelAndView home(Locale locale) {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName(SiteConstants.VIEW_NAME_HOME);
		modelView.addObject(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE, messageSource.getMessage(SiteConstants.PAGE_TITLE_HOME, null, locale));
		
		return modelView;
	}
}
