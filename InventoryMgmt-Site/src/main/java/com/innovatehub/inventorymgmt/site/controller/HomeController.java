package com.innovatehub.inventorymgmt.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController extends BaseController {
	@RequestMapping("/home")
	public String home() {
		return "dashboard/home";
	}
}
