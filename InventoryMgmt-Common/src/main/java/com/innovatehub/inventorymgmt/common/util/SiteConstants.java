package com.innovatehub.inventorymgmt.common.util;

public class SiteConstants {

	// ---------------- Session Constants --------------------
	public static final String SESSION_AUTHORIZED_PAGES = "SESSION_AUTHORIZED_PAGES";

	// ---------------- Page URI -----------------------------
	public static final String PAGE_URI_HOME = "/home";
	public static final String PAGE_URI_LOGIN = "/login";
	public static final String PAGE_URI_DEFAULT_SUCCESS_URL = "/home";
	
	public static final String PAGE_URI_STOCK_PRODUCT_CATEGORY_CREATE= "/stock/category/create";
	public static final String PAGE_URI_STOCK_PRODUCT_CREATE= "/stock/product/create";
	public static final String PAGE_URI_STOCK_PRODUCT_VIEW= "/stock/product/view";
	public static final String PAGE_URI_STOCK_SKU_CREATE= "/stock/sku/create";
	public static final String PAGE_URI_STOCK_SKU_VIEW= "/stock/sku/view";
	public static final String PAGE_URI_STOCK_STOCK_CREATE= "/stock/stock/create";
	
	// -----------------View Names----------------------------
	public static final String VIEW_NAME_HOME = "dashboard/home";
	public static final String VIEW_NAME_LOGIN = "landing/login";
	
	public static final String VIEW_NAME_STOCK_PRODUCT_CATEGORY_CREATE = "dashboard/stock/category_create";
	public static final String VIEW_NAME_STOCK_PRODUCT_CATEGORY_VIEW = "dashboard/stock/category_view";
	public static final String VIEW_NAME_STOCK_PRODUCT_CREATE = "dashboard/stock/product_create";
	public static final String VIEW_NAME_STOCK_PRODUCT_VIEW = "dashboard/stock/product_view";
	public static final String VIEW_NAME_STOCK_SKU_CREATE = "dashboard/stock/sku_create";
	public static final String VIEW_NAME_STOCK_SKU_VIEW = "dashboard/stock/sku_view";
	public static final String VIEW_NAME_STOCK_STOCK_CREATE = "dashboard/stock/stock_create";
	
	//------------------- Page Titles -------------------------
	public static final String PAGE_TITLE_HOME = "pageTitle.home";
	public static final String PAGE_TITLE_STOCK_PROD_CATEGORY_CREATE = "pageTitle.stock.category.create";
	public static final String PAGE_TITLE_STOCK_PROD_CATEGORY_VIEW = "pageTitle.stock.category.view";
	public static final String PAGE_TITLE_STOCK_PROD_CREATE = "pageTitle.stock.product.create";
	public static final String PAGE_TITLE_STOCK_PROD_VIEW = "pageTitle.stock.product.view";
	public static final String PAGE_TITLE_STOCK_SKU_CREATE = "pageTitle.stock.sku.create";
	public static final String PAGE_TITLE_STOCK_SKU_VIEW = "pageTitle.stock.sku.view";
	public static final String PAGE_TITLE_STOCK_STOCK_CREATE = "pageTitle.stock.stock.create";
	
	//------------------- Model Attributes ---------------------
	public static final String MODEL_ATTRIBUTE_PAGE_TITLE = "pageTitle";
	public static final String MODEL_ATTRIBUTE_FORM_ERRORS = "formErrors";
	
	//-------------------- CSS Attributes -----------------------
	public static final String CSS_ALERT = "alertCSS";
	public static final String CSS_MSG_SUCCESS = "alert-success";
	
	//--------------------- Alerts ------------------------------
	public static final String ALERT_MSG = "alertMsg";
	
	//--------------------- Messages -----------------------------
	public static final String MSG_PROD_CAT_CREATE_SUCCESS = "stock.prodCategory.alert.success";
	public static final String MSG_PRODUCT_CREATE_SUCCESS = "stock.product.alert.success";
	public static final String MSG_SKU_CREATE_SUCCESS = "stock.sku.alert.success";
	
	//--------------------- Thymeleaf constants -----------------
	public static final String THYMELEAF_FRAGMENTS_HOME = "/views/thymeleaf/layout/fragments";
	

	
}
