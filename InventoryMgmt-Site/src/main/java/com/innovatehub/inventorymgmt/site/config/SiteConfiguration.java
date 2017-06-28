package com.innovatehub.inventorymgmt.site.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:site.properties")
public class SiteConfiguration {
	@Value("${pos.receipt.companyImage.url}")
	private String posReceiptCompanyImgUrl;
	
	@Value("${pos.receipt.companyImage.scalePct}")
	private String posReceiptCompanyImgScalePct;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	public String getPosReceiptCompanyImgUrl() {
		return posReceiptCompanyImgUrl;
	}

	public void setPosReceiptCompanyImgUrl(String posReceiptCompanyImgUrl) {
		this.posReceiptCompanyImgUrl = posReceiptCompanyImgUrl;
	}
	
	public String getPosReceiptCompanyImgScalePct() {
		return posReceiptCompanyImgScalePct;
	}

	public void setPosReceiptCompanyImgScalePct(String posReceiptCompanyImgScalePct) {
		this.posReceiptCompanyImgScalePct = posReceiptCompanyImgScalePct;
	}
}
