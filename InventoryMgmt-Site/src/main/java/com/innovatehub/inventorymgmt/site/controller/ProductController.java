package com.innovatehub.inventorymgmt.site.controller;

import java.io.IOException;
import java.sql.SQLException;
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

import com.innovatehub.inventorymgmt.common.model.stock.Product;
import com.innovatehub.inventorymgmt.common.model.stock.ProductCategory;
import com.innovatehub.inventorymgmt.common.util.SiteConstants;
import com.innovatehub.inventorymgmt.services.stock.ProductService;
import com.innovatehub.inventorymgmt.site.util.GenericUtilHelper;

@Controller
public class ProductController extends BaseController {
	private static final String MODEL_ATTRIB_PROD = "product";

	@Autowired
	private ProductService productService;

	@Autowired
	MessageSource messageSource;

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productCatService) {
		this.productService = productCatService;
	}
	
	@RequestMapping(value = "/stock/product/create")
	public String displayCreateCategory(Locale locale, Model model) {

		model.addAttribute(MODEL_ATTRIB_PROD, new Product());
		model.addAttribute(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE,
				messageSource.getMessage(SiteConstants.PAGE_TITLE_STOCK_PROD_CREATE, null, locale));

		return SiteConstants.VIEW_NAME_STOCK_PRODUCT_CREATE;
	}

	@RequestMapping(value = "/stock/product/create", method = RequestMethod.POST)
	public String saveCategory(@Valid @ModelAttribute(MODEL_ATTRIB_PROD) Product product, BindingResult bindResult,
			@RequestParam("file") MultipartFile file, Model model, Locale locale,
			final RedirectAttributes redirectAttributes) throws IOException {
		if (bindResult.hasErrors()) {
			model.addAttribute(MODEL_ATTRIB_PROD, product);
			model.addAttribute(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE,
					messageSource.getMessage(SiteConstants.PAGE_TITLE_STOCK_PROD_CREATE, null, locale));
			model.addAttribute(SiteConstants.MODEL_ATTRIBUTE_FORM_ERRORS, true);

			return SiteConstants.VIEW_NAME_STOCK_PRODUCT_CREATE;
		}

		product.setProductImage(GenericUtilHelper.getByteArrayFromMultiPart(file));

		Long productId = this.productService.saveProduct(product);

		// Show the Success alert.
		redirectAttributes.addFlashAttribute(SiteConstants.CSS_ALERT, SiteConstants.CSS_MSG_SUCCESS);
		redirectAttributes.addFlashAttribute(SiteConstants.ALERT_MSG, messageSource
				.getMessage(SiteConstants.MSG_PRODUCT_CREATE_SUCCESS, new Object[] { product.getProductName() }, locale));

		String viewProductURL = String.format("%s/%s", SiteConstants.PAGE_URI_STOCK_PRODUCT_VIEW, productId);
		return "redirect:" + viewProductURL;
	}
	
	@RequestMapping(value = "/stock/product/view/{id}")
	public ModelAndView viewCategory(@PathVariable("id") Long productId, Locale locale) throws SQLException {

		ModelAndView modelView = new ModelAndView();
		modelView.setViewName(SiteConstants.VIEW_NAME_STOCK_PRODUCT_VIEW);
		modelView.addObject(MODEL_ATTRIB_PROD, this.productService.getProduct(productId));
		modelView.addObject(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE,
				messageSource.getMessage(SiteConstants.PAGE_TITLE_STOCK_PROD_VIEW, null, locale));

		return modelView;
	}
}
