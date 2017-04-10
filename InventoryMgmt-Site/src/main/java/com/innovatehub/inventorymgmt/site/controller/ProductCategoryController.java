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

import com.innovatehub.inventorymgmt.common.util.SiteConstants;
import com.innovatehub.inventorymgmt.services.stock.ProductCategoryService;
import com.innovatehub.inventorymgmt.site.util.GenericUtilHelper;
import com.innovatehub.inventorymgmt.common.model.stock.ProductCategory;

@Controller
public class ProductCategoryController extends BaseController {
	private static final String PAGE_URI_STOCK_PRODUCT_CATEGORY_VIEW_FMT = "/stock/category/view/%s";

	private static final String MODEL_ATTRIB_PROD_CAT = "prodCategory";

	@Autowired
	private ProductCategoryService productCatService;

	@Autowired
	MessageSource messageSource;

	public ProductCategoryService getProductCatService() {
		return productCatService;
	}

	public void setProductCatService(ProductCategoryService productCatService) {
		this.productCatService = productCatService;
	}

	@RequestMapping(value = "/stock/category/create")
	public String displayCreateCategory(Locale locale, Model model) {

		model.addAttribute(MODEL_ATTRIB_PROD_CAT, new ProductCategory());
		model.addAttribute(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE,
				messageSource.getMessage(SiteConstants.PAGE_TITLE_STOCK_PROD_CATEGORY_CREATE, null, locale));

		return SiteConstants.VIEW_NAME_STOCK_PRODUCT_CATEGORY_CREATE;
	}

	@RequestMapping(value = "/stock/category/create", method = RequestMethod.POST)
	public String saveCategory(@Valid @ModelAttribute("prodCategory") ProductCategory prodCategory,
			BindingResult bindResult, @RequestParam("file") MultipartFile file, Model model, Locale locale,
			final RedirectAttributes redirectAttributes) throws IOException {
		if (bindResult.hasErrors()) {
			model.addAttribute(MODEL_ATTRIB_PROD_CAT, prodCategory);
			model.addAttribute(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE,
					messageSource.getMessage(SiteConstants.PAGE_TITLE_STOCK_PROD_CATEGORY_CREATE, null, locale));
			model.addAttribute(SiteConstants.MODEL_ATTRIBUTE_FORM_ERRORS, true);

			return SiteConstants.VIEW_NAME_STOCK_PRODUCT_CATEGORY_CREATE;
		}

		prodCategory.setUploadedFileBytes(GenericUtilHelper.getByteArrayFromMultiPart(file));

		int productCatId = this.productCatService.saveProductCategory(prodCategory);

		// Show the Success alert.
		redirectAttributes.addFlashAttribute(SiteConstants.CSS_ALERT, SiteConstants.CSS_MSG_SUCCESS);
		redirectAttributes.addFlashAttribute(SiteConstants.ALERT_MSG, messageSource.getMessage(
				SiteConstants.MSG_PROD_CAT_SUCCESS, new Object[] { prodCategory.getCategoryName() }, locale));

		String categoryViewUrl = String.format(PAGE_URI_STOCK_PRODUCT_CATEGORY_VIEW_FMT, productCatId);
		return "redirect:" + categoryViewUrl;
	}

	@RequestMapping(value = "/stock/category/view/{id}")
	public ModelAndView viewCategory(@PathVariable("id") int prodCategoryId, Locale locale) throws SQLException {

		ModelAndView modelView = new ModelAndView();
		modelView.setViewName(SiteConstants.VIEW_NAME_STOCK_PRODUCT_CATEGORY_VIEW);
		modelView.addObject(MODEL_ATTRIB_PROD_CAT, this.productCatService.getProductCategory(prodCategoryId));
		modelView.addObject(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE,
				messageSource.getMessage(SiteConstants.PAGE_TITLE_STOCK_PROD_CATEGORY_VIEW, null, locale));

		return modelView;
	}
}
