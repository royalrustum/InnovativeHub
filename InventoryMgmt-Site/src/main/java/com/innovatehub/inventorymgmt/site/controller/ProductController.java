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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.innovatehub.inventorymgmt.common.model.stock.Product;
import com.innovatehub.inventorymgmt.common.util.SiteConstants;
import com.innovatehub.inventorymgmt.services.stock.ProductCategoryService;
import com.innovatehub.inventorymgmt.services.stock.ProductService;
import com.innovatehub.inventorymgmt.site.util.GenericUtilHelper;

@Controller
public class ProductController extends BaseController {
	private static final String MODEL_ATTRIB_PROD = "product";

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductCategoryService prodCatService;

	@Autowired
	MessageSource messageSource;

	public ProductCategoryService getProdCatService() {
		return prodCatService;
	}

	public void setProdCatService(ProductCategoryService prodCatService) {
		this.prodCatService = prodCatService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productCatService) {
		this.productService = productCatService;
	}

	@RequestMapping(value = "/stock/product/create")
	public String displayCreateProduct(Locale locale, Model model) {

		Product product = new Product();
		product.setProductCategories(prodCatService.getAllProductCategories());
		model.addAttribute(MODEL_ATTRIB_PROD, product);

		model.addAttribute(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE,
				messageSource.getMessage(SiteConstants.PAGE_TITLE_STOCK_PROD_CREATE, null, locale));

		return SiteConstants.VIEW_NAME_STOCK_PRODUCT_CREATE;
	}

	@RequestMapping(value = "/stock/product/create", method = RequestMethod.POST)
	public String saveProduct(@Valid @ModelAttribute(MODEL_ATTRIB_PROD) Product product, BindingResult bindResult,
			@RequestParam("file") MultipartFile file, Model model, Locale locale,
			final RedirectAttributes redirectAttributes) throws IOException {
		if (bindResult.hasErrors()) {

			product.setProductCategories(prodCatService.getAllProductCategories());

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
		redirectAttributes.addFlashAttribute(SiteConstants.ALERT_MSG, messageSource.getMessage(
				SiteConstants.MSG_PRODUCT_CREATE_SUCCESS, new Object[] { product.getProductName() }, locale));

		String viewProductURL = String.format("%s/%s", SiteConstants.PAGE_URI_STOCK_PRODUCT_VIEW, productId);
		return "redirect:" + viewProductURL;
	}

	@RequestMapping(value = "/stock/product/view/{id}")
	public ModelAndView viewProduct(@PathVariable("id") Long productId, Locale locale) {

		ModelAndView modelView = new ModelAndView();
		modelView.setViewName(SiteConstants.VIEW_NAME_STOCK_PRODUCT_VIEW);
		modelView.addObject(MODEL_ATTRIB_PROD, this.productService.getProduct(productId));
		modelView.addObject(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE,
				messageSource.getMessage(SiteConstants.PAGE_TITLE_STOCK_PROD_VIEW, null, locale));

		return modelView;
	}

	@RequestMapping(value = "/stock/product/prodCat/{id}")
	public @ResponseBody List<Product> getProductsInProductCat(@PathVariable("id") Long productCat) {
		return this.getProductService().getAllProductsInCategory(productCat);
	}

	@RequestMapping(value = "/stock/product/matches/{q}")
	public @ResponseBody List<Product> getAllProductsJson(@PathVariable("q") String productName) {
		List<Product> allProducts = this.getProductService().getAllProducts();
		allProducts = allProducts.stream().filter(prod -> (prod.getProductName().toUpperCase()
				.indexOf(productName.toUpperCase())) >= 0).collect(Collectors.toList());
		return allProducts;
	}

	@RequestMapping(value = "/stock/product/list")
	public ModelAndView getAllProducts(Locale locale) {
		List<Product> allProducts = this.getProductService().getAllProducts();
		
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName(SiteConstants.VIEW_NAME_STOCK_PRODUCT_LIST);
		modelView.addObject(MODEL_ATTRIB_PROD, allProducts);
		modelView.addObject(SiteConstants.MODEL_ATTRIBUTE_PAGE_TITLE,
				messageSource.getMessage(SiteConstants.PAGE_TITLE_STOCK_PROD_LIST, null, locale));

		return modelView;
	}
}
