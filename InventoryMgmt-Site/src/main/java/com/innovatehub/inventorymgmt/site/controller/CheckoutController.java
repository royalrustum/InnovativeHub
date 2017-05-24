package com.innovatehub.inventorymgmt.site.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.innovatehub.inventorymgmt.common.model.stock.Stock;
import com.innovatehub.inventorymgmt.common.util.SiteConstants;

@Controller
public class CheckoutController extends BaseController {
	@Autowired
	MessageSource messageSource;

	private static final String MODEL_ATTRIB_CHECKOUT = "checkout";

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/pos/checkout/create")
	public String displayCreateStock(Locale locale, Model model) {

		Stock checkout = new Stock();

		model.addAttribute(MODEL_ATTRIB_CHECKOUT, checkout);
		model.addAttribute(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE,
				messageSource.getMessage(SiteConstants.PAGE_TITLE_SALE_CHECKOUT_CREATE, null, locale));

		return SiteConstants.VIEW_NAME_SALE_CHECKOUT_CREATE;
	}
	
	/*@RequestMapping(value = "/pos/checkout/create", method = RequestMethod.POST)
	public String saveProduct(@Valid @ModelAttribute(MODEL_ATTRIB_STOCK) Stock stock, BindingResult bindResult, Model model,
			Locale locale, final RedirectAttributes redirectAttributes) throws IOException {
		stock.setStockDate(new Date());
		if (bindResult.hasErrors()) {

			stock.setAllProductCategories(this.getProdCatService().getAllProductCategories());

			model.addAttribute(MODEL_ATTRIB_STOCK, stock);
			model.addAttribute(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE,
					messageSource.getMessage(SiteConstants.PAGE_TITLE_STOCK_STOCK_CREATE, null, locale));
			model.addAttribute(SiteConstants.MODEL_ATTRIBUTE_FORM_ERRORS, true);

			return SiteConstants.VIEW_NAME_STOCK_STOCK_CREATE;
		}

		Long stockId = this.getStockService().saveStock(stock);

		// Show the Success alert.
		redirectAttributes.addFlashAttribute(SiteConstants.CSS_ALERT, SiteConstants.CSS_MSG_SUCCESS);
		redirectAttributes.addFlashAttribute(SiteConstants.ALERT_MSG, messageSource
				.getMessage(SiteConstants.MSG_STOCK_CREATE_SUCCESS, new Object[] { stock.getStockDate() }, locale));

		String viewProductURL = String.format("%s/%s", SiteConstants.PAGE_URI_STOCK_STOCK_VIEW, stockId);
		return "redirect:" + viewProductURL;
	}*/

/*	@RequestMapping(value = "/pos/checkout/view/{id}")
	public ModelAndView viewProduct(@PathVariable("id") Long stockId, Locale locale) {

		ModelAndView modelView = new ModelAndView();
		modelView.setViewName(SiteConstants.VIEW_NAME_STOCK_STOCK_VIEW);
		modelView.addObject(MODEL_ATTRIB_STOCK, this.getStockService().getStock(stockId));
		modelView.addObject(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE,
				messageSource.getMessage(SiteConstants.PAGE_TITLE_STOCK_STOCK_VIEW, null, locale));

		return modelView;
	}*/
}
