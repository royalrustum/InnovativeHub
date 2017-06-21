package com.innovatehub.inventorymgmt.site.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.innovatehub.inventorymgmt.common.model.customer.Customer;
import com.innovatehub.inventorymgmt.common.model.stock.SKU;
import com.innovatehub.inventorymgmt.common.util.SiteConstants;
import com.innovatehub.inventorymgmt.services.customer.CustomerService;

@Controller
public class CustomerController extends BaseController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	MessageSource messageSource;

	private static final String MODEL_ATTRIB_CUSTOMER = "customer";

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/customer/profile/create")
	public String displayCreateCustomer(Locale locale, Model model) {

		Customer customer = new Customer();

		model.addAttribute(MODEL_ATTRIB_CUSTOMER, customer);

		model.addAttribute(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE,
				messageSource.getMessage(SiteConstants.PAGE_TITLE_CUSTOMER_PROFILE_CREATE, null, locale));

		return SiteConstants.VIEW_NAME_CUSTOMER_PROFILE_CREATE;
	}

	@RequestMapping(value = "/customer/profile/create", method = RequestMethod.POST)
	public String saveCustomer(@Valid @ModelAttribute(MODEL_ATTRIB_CUSTOMER) Customer customer,
			BindingResult bindResult, Model model, Locale locale, final RedirectAttributes redirectAttributes)
			throws IOException {
		if (bindResult.hasErrors()) {

			model.addAttribute(MODEL_ATTRIB_CUSTOMER, customer);
			model.addAttribute(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE,
					messageSource.getMessage(SiteConstants.PAGE_TITLE_CUSTOMER_PROFILE_CREATE, null, locale));
			model.addAttribute(SiteConstants.MODEL_ATTRIBUTE_FORM_ERRORS, true);

			return SiteConstants.VIEW_NAME_CUSTOMER_PROFILE_CREATE;
		}

		Long customerId = this.getCustomerService().saveCustomer(customer);

		// Show the Success alert.
		redirectAttributes.addFlashAttribute(SiteConstants.CSS_ALERT, SiteConstants.CSS_MSG_SUCCESS);
		redirectAttributes.addFlashAttribute(SiteConstants.ALERT_MSG,
				messageSource.getMessage(SiteConstants.MSG_CUSTOMER_CREATE_SUCCESS,
						new Object[] { customer.getFirstName() + " " + customer.getLastName() }, locale));

		String viewCustomerURL = String.format("%s/%s", SiteConstants.PAGE_URI_CUSTOMER_PROFILE_VIEW, customerId);
		return "redirect:" + viewCustomerURL;
	}

	@RequestMapping(value = "/customer/profile/view/{id}")
	public ModelAndView viewCustomer(@PathVariable("id") Long customerId, Locale locale) {

		ModelAndView modelView = new ModelAndView();
		modelView.setViewName(SiteConstants.VIEW_NAME_CUSTOMER_PROFILE_VIEW);
		modelView.addObject(MODEL_ATTRIB_CUSTOMER, this.getCustomerService().getCustomer(customerId));
		modelView.addObject(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE,
				messageSource.getMessage(SiteConstants.PAGE_TITLE_CUSTOMER_PROFILE_VIEW, null, locale));

		return modelView;
	}

	@RequestMapping(value = "/customer/profile/matches/{q}")
	public @ResponseBody List<Customer> getAllSKUInAllProducts(@PathVariable("q") String customerName) {
		List<Customer> allCustomers = this.getCustomerService().getAllCustomers();

		allCustomers = allCustomers.stream()
				.filter(customer -> ((customer.getFirstName().toUpperCase().indexOf(customerName.toUpperCase()) >= 0)
						|| (customer.getLastName().toUpperCase().indexOf(customerName.toUpperCase()) >= 0)))
				.collect(Collectors.toList());

		return allCustomers;
	}

	@RequestMapping(value = "/customer/profile/list")
	public ModelAndView viewCustomer(Locale locale) {

		ModelAndView modelView = new ModelAndView();
		modelView.setViewName(SiteConstants.VIEW_NAME_CUSTOMER_PROFILE_LIST);
		modelView.addObject(MODEL_ATTRIB_CUSTOMER, this.getCustomerService().getAllCustomers());
		modelView.addObject(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE,
				messageSource.getMessage(SiteConstants.PAGE_TITLE_CUSTOMER_PROFILE_LIST, null, locale));

		return modelView;
	}
}
