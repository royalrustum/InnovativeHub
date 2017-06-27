package com.innovatehub.inventorymgmt.site.controller;

import java.io.IOException;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.innovatehub.inventorymgmt.common.model.pos.Sale;
import com.innovatehub.inventorymgmt.common.util.SiteConstants;
import com.innovatehub.inventorymgmt.services.pos.PrintService;
import com.innovatehub.inventorymgmt.services.pos.SaleService;
import com.innovatehub.inventorymgmt.services.stock.StockService;

@Controller
public class CheckoutController extends BaseController {
	@Autowired
	MessageSource messageSource;

	@Autowired
	SaleService saleService;

	@Autowired
	PrintService printService;
	
	@Autowired
	StockService stockService;

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

	public PrintService getPrintService() {
		return printService;
	}

	public void setPrintService(PrintService printService) {
		this.printService = printService;
	}

	public StockService getStockService() {
		return stockService;
	}

	public void setStockService(StockService stockService) {
		this.stockService = stockService;
	}
	
	@RequestMapping(value = "/pos/checkout/create")
	public String displayCreateStock(Locale locale, Model model) {

		Sale checkout = new Sale();

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
		/*
		 * redirectAttributes.addFlashAttribute(SiteConstants.CSS_ALERT,
		 * SiteConstants.CSS_MSG_SUCCESS);
		 * redirectAttributes.addFlashAttribute(SiteConstants.ALERT_MSG,
		 * messageSource .getMessage(SiteConstants.MSG_STOCK_CREATE_SUCCESS, new
		 * Object[] { sale.getStockDate() }, locale));
		 */

		String saleCompleteURL = String.format("%s/%s", SiteConstants.PAGE_URI_POS_CHECKOUT_COMPLETE, saleId);
		return "redirect:" + saleCompleteURL;
	}

	@RequestMapping(value = "/pos/checkout/complete/{id}")
	public ModelAndView viewProduct(@PathVariable("id") Long saleId, Locale locale) {

		Sale sale = this.getSaleService().getSale(saleId);

		ModelAndView modelView = new ModelAndView();
		modelView.setViewName(SiteConstants.VIEW_NAME_SALE_CHECKOUT_COMPLETE);
		modelView.addObject(MODEL_ATTRIB_CHECKOUT, sale);
		modelView.addObject(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE,
				messageSource.getMessage(SiteConstants.PAGE_TITLE_SALE_CHECKOUT_COMPLETE, null, locale));

		return modelView;
	}

	@RequestMapping(value = "/pos/checkout/printreceipt/{id}", method = RequestMethod.POST)
	public ResponseEntity<byte[]> printReceipt(@PathVariable("id") Long saleId) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));

		// String filename = "Receipt.pdf";

		// Uncomment this for download.
		//headers.setContentDispositionFormData(filename, filename);
		
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

		Sale sale = this.getSaleService().getSale(saleId);

		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(printService.generateSaleReceipt(sale), headers,
				HttpStatus.OK);
		return response;
	}
}
