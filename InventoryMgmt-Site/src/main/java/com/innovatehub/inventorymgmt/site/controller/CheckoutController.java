package com.innovatehub.inventorymgmt.site.controller;

import java.io.IOException;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.innovatehub.inventorymgmt.common.model.pos.Sale;
import com.innovatehub.inventorymgmt.common.model.stock.Stock;
import com.innovatehub.inventorymgmt.common.util.SiteConstants;
import com.innovatehub.inventorymgmt.services.pos.SaleService;

@Controller
public class CheckoutController extends BaseController {
	@Autowired
	MessageSource messageSource;

	@Autowired
	SaleService saleService;
	
	private static final String MODEL_ATTRIB_CHECKOUT = "checkout";

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public SaleService getSaleService() {
		return saleService;
	}

	public void setSaleService(SaleService saleService) {
		this.saleService = saleService;
	}
	
	@RequestMapping(value = "/pos/checkout/create")
	public String displayCreateStock(Locale locale, Model model) {

		Stock checkout = new Stock();

		model.addAttribute(MODEL_ATTRIB_CHECKOUT, checkout);
		model.addAttribute(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE,
				messageSource.getMessage(SiteConstants.PAGE_TITLE_SALE_CHECKOUT_CREATE, null, locale));

		return SiteConstants.VIEW_NAME_SALE_CHECKOUT_CREATE;
	}

	@RequestMapping(value = "/pos/checkout/create", method = RequestMethod.POST)
	public String saveProduct(@Valid @ModelAttribute(MODEL_ATTRIB_CHECKOUT) Sale sale, BindingResult bindResult,
			Model model, Locale locale, final RedirectAttributes redirectAttributes) throws IOException {
		
		if (bindResult.hasErrors()) {

			model.addAttribute(MODEL_ATTRIB_CHECKOUT, sale);
			model.addAttribute(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE,
					messageSource.getMessage(SiteConstants.PAGE_TITLE_SALE_CHECKOUT_CREATE, null, locale));
			model.addAttribute(SiteConstants.MODEL_ATTRIBUTE_FORM_ERRORS, true);

			return SiteConstants.VIEW_NAME_SALE_CHECKOUT_CREATE;
		}

		Long saleId = this.getSaleService().saveSale(sale);

		// Show the Success alert.
		/*redirectAttributes.addFlashAttribute(SiteConstants.CSS_ALERT, SiteConstants.CSS_MSG_SUCCESS);
		redirectAttributes.addFlashAttribute(SiteConstants.ALERT_MSG, messageSource
				.getMessage(SiteConstants.MSG_STOCK_CREATE_SUCCESS, new Object[] { sale.getStockDate() }, locale));
		 */
		
		
		String saleCompleteURL = String.format("%s/%s", SiteConstants.PAGE_URI_POS_CHECKOUT_COMPLETE, saleId);
		return "redirect:" + saleCompleteURL;
	}

	/*
	 * @RequestMapping(value = "/pos/checkout/view/{id}") public ModelAndView
	 * viewProduct(@PathVariable("id") Long stockId, Locale locale) {
	 * 
	 * ModelAndView modelView = new ModelAndView();
	 * modelView.setViewName(SiteConstants.VIEW_NAME_STOCK_STOCK_VIEW);
	 * modelView.addObject(MODEL_ATTRIB_STOCK,
	 * this.getStockService().getStock(stockId));
	 * modelView.addObject(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE,
	 * messageSource.getMessage(SiteConstants.PAGE_TITLE_STOCK_STOCK_VIEW, null,
	 * locale));
	 * 
	 * return modelView; }
	 */
}
