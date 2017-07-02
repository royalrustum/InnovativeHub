package com.innovatehub.inventorymgmt.site.config;

import java.util.Locale;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.innovatehub.inventorymgmt.site.util.convert.StringToCustomerConvert;
import com.innovatehub.inventorymgmt.site.util.convert.StringToDateConvert;
import com.innovatehub.inventorymgmt.site.util.convert.StringToProductCatConvert;
import com.innovatehub.inventorymgmt.site.util.convert.StringToProductConvert;
import com.innovatehub.inventorymgmt.site.util.convert.StringToSKUConvert;
import com.innovatehub.inventorymgmt.site.util.convert.StringToSaleDetailsConvert;
import com.innovatehub.inventorymgmt.site.util.convert.StringToStockConvert;

@Configuration
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {

	private static final String UTF8 = "UTF-8";

	private ApplicationContext applicationContext;

	@Autowired
	private StringToProductCatConvert stringToProdCatConvert;

	@Autowired
	private StringToProductConvert stringToProductConvert;

	@Autowired
	private StringToSKUConvert stringToSKUConvert;

	@Autowired
	private StringToCustomerConvert stringToCustomerConvert;

	@Autowired
	private StringToSaleDetailsConvert stringToSaleDetailsConvert;
	
	@Autowired
	private StringToDateConvert stringToDateConvert;
	
	@Autowired
	private StringToStockConvert stringToStockConvert;

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public StringToProductCatConvert getStringToProdCatConvert() {
		return stringToProdCatConvert;
	}

	public void setStringToProdCatConvert(StringToProductCatConvert stringToProdCatConvert) {
		this.stringToProdCatConvert = stringToProdCatConvert;
	}

	public StringToProductConvert getStringToProductConvert() {
		return stringToProductConvert;
	}

	public void setStringToProductConvert(StringToProductConvert stringToProductConvert) {
		this.stringToProductConvert = stringToProductConvert;
	}

	public StringToSKUConvert getStringToSKUConvert() {
		return stringToSKUConvert;
	}

	public void setStringToSKUConvert(StringToSKUConvert stringToSKUConvert) {
		this.stringToSKUConvert = stringToSKUConvert;
	}

	public StringToCustomerConvert getStringToCustomerConvert() {
		return stringToCustomerConvert;
	}

	public void setStringToCustomerConvert(StringToCustomerConvert stringToCustomerConvert) {
		this.stringToCustomerConvert = stringToCustomerConvert;
	}

	public StringToSaleDetailsConvert getStringToSaleDetailsConvert() {
		return stringToSaleDetailsConvert;
	}

	public void setStringToSaleDetailsConvert(StringToSaleDetailsConvert stringToSaleDetailsConvert) {
		this.stringToSaleDetailsConvert = stringToSaleDetailsConvert;
	}

	public StringToDateConvert getStringToDateConvert() {
		return stringToDateConvert;
	}

	public void setStringToDateConvert(StringToDateConvert stringToDateConvert) {
		this.stringToDateConvert = stringToDateConvert;
	}
	
	public StringToStockConvert getStringToStockConvert() {
		return stringToStockConvert;
	}

	public void setStringToStockConvert(StringToStockConvert stringToStockConvert) {
		this.stringToStockConvert = stringToStockConvert;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		registry.addResourceHandler("static/css/**").addResourceLocations("/static/css/",
				"/static/uiframeworks/bootstrap-3.3.7/css/", "/static/uiframeworks/jquery-ui-1.12.1/css/base/",
				"/static/uiframeworks/datatable-1.10.15/css/");
		registry.addResourceHandler("static/js/**").addResourceLocations("/static/js/", "/static/uiframeworks/jquery/",
				"/static/uiframeworks/jquery-ui-1.12.1/js/", "/static/uiframeworks/bootstrap-3.3.7/js/",
				"/static/uiframeworks/angular-1.6/js/", "/static/uiframeworks/typeahead-0.11.1/js/",
				"/static/uiframeworks/datatable-1.10.15/js/", "/static/uiframeworks/chartjs-2.6/js/",
				"/static/js/angular/**");
		registry.addResourceHandler("static/fonts/**").addResourceLocations("/static/js/",
				"/static/uiframeworks/bootstrap-3.3.7/fonts/");
		registry.addResourceHandler("static/images/**").addResourceLocations("/static/images/");

	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Bean
	ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
		registrationBean.addUrlMappings("/dbc/*");
		return registrationBean;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory() {
		return new HibernateJpaSessionFactoryBean();
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(this.getStringToProdCatConvert());
		registry.addConverter(this.getStringToProductConvert());
		registry.addConverter(this.getStringToSKUConvert());
		registry.addConverter(this.getStringToCustomerConvert());
		registry.addConverter(this.getStringToSaleDetailsConvert());
		registry.addConverter(this.getStringToDateConvert());
		registry.addConverter(this.getStringToStockConvert());
	}

	// @Bean
	// public ThymeleafViewResolver thymeleafViewResolver() {
	// ThymeleafViewResolver thymeleafViewResolver = new
	// ThymeleafViewResolver();
	// thymeleafViewResolver.setTemplateEngine(templateEngine());
	// thymeleafViewResolver.setOrder(1);
	// return thymeleafViewResolver;
	// }
	//
	// @Bean
	// public SpringTemplateEngine templateEngine() {
	// SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	// templateEngine.setTemplateResolver(defaultTemplateResolver());
	// templateEngine.addDialect(new LayoutDialect());
	// return templateEngine;
	// }
	//
	// @Bean
	// public ServletContextTemplateResolver defaultTemplateResolver() {
	// ServletContextTemplateResolver templateResolver = new
	// ServletContextTemplateResolver();
	// templateResolver.setPrefix("/WEB-INF/views/thymeleaf/");
	// templateResolver.setSuffix(".html");
	// templateResolver.setTemplateMode("HTML5");
	// templateResolver.setOrder(1);
	// templateResolver.setCacheable(false);
	// return templateResolver;
	// }
}