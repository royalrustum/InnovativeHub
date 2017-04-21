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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.innovatehub.inventorymgmt.common.model.stock.SKU;
import com.innovatehub.inventorymgmt.common.util.SiteConstants;
import com.innovatehub.inventorymgmt.services.stock.ProductService;
import com.innovatehub.inventorymgmt.services.stock.SKUService;

@Controller
public class SKUController extends BaseController {

	@Autowired
	private ProductService productService;

	@Autowired
	private SKUService skuService;

	@Autowired
	MessageSource messageSource;

	private static final String MODEL_ATTRIB_SKU = "sku";

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public SKUService getSkuService() {
		return skuService;
	}

	public void setSkuService(SKUService skuService) {
		this.skuService = skuService;
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/stock/sku/create")
	public String displayCreateProduct(Locale locale, Model model) {

		SKU sku = new SKU();
		sku.setAllProducts(productService.getAllProducts());
		model.addAttribute(MODEL_ATTRIB_SKU, sku);

		model.addAttribute(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE,
				messageSource.getMessage(SiteConstants.PAGE_TITLE_STOCK_SKU_CREATE, null, locale));

		return SiteConstants.VIEW_NAME_STOCK_SKU_CREATE;
	}

	@RequestMapping(value = "/stock/sku/create", method = RequestMethod.POST)
	public String saveProduct(@Valid @ModelAttribute(MODEL_ATTRIB_SKU) SKU sku, BindingResult bindResult, Model model,
			Locale locale, final RedirectAttributes redirectAttributes) throws IOException {
		if (bindResult.hasErrors()) {

			sku.setAllProducts(this.getProductService().getAllProducts());

			model.addAttribute(MODEL_ATTRIB_SKU, sku);
			model.addAttribute(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE,
					messageSource.getMessage(SiteConstants.PAGE_TITLE_STOCK_SKU_CREATE, null, locale));
			model.addAttribute(SiteConstants.MODEL_ATTRIBUTE_FORM_ERRORS, true);

			return SiteConstants.VIEW_NAME_STOCK_SKU_CREATE;
		}

		Long skuId = this.getSkuService().saveSKU(sku);

		// Show the Success alert.
		redirectAttributes.addFlashAttribute(SiteConstants.CSS_ALERT, SiteConstants.CSS_MSG_SUCCESS);
		redirectAttributes.addFlashAttribute(SiteConstants.ALERT_MSG, messageSource
				.getMessage(SiteConstants.MSG_SKU_CREATE_SUCCESS, new Object[] { sku.getSkuName() }, locale));

		String viewProductURL = String.format("%s/%s", SiteConstants.PAGE_URI_STOCK_SKU_VIEW, skuId);
		return "redirect:" + viewProductURL;
	}

	@RequestMapping(value = "/stock/sku/view/{id}")
	public ModelAndView viewProduct(@PathVariable("id") Long skuId, Locale locale) {

		ModelAndView modelView = new ModelAndView();
		modelView.setViewName(SiteConstants.VIEW_NAME_STOCK_SKU_VIEW);
		modelView.addObject(MODEL_ATTRIB_SKU, this.getSkuService().getSKU(skuId));
		modelView.addObject(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE,
				messageSource.getMessage(SiteConstants.PAGE_TITLE_STOCK_SKU_VIEW, null, locale));

		return modelView;
	}
}