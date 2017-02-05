package com.innovatehub.inventorymgmt.site.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {

	private static final String UTF8 = "UTF-8";

	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
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

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		registry.addResourceHandler("/css/**").addResourceLocations("/static/css/", "/static/uiframeworks/bootstrap-3.3.7/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("/static/js/", "/static/uiframeworks/bootstrap-3.3.7/js/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("/static/js/", "/static/uiframeworks/bootstrap-3.3.7/fonts/");

	}

}