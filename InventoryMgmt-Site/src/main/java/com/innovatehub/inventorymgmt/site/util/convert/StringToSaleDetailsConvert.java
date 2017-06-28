package com.innovatehub.inventorymgmt.site.util.convert;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.innovatehub.inventorymgmt.common.model.pos.SaleDetail;
import com.innovatehub.inventorymgmt.services.stock.SKUService;

@Component
public class StringToSaleDetailsConvert implements Converter<String, List<SaleDetail>> {

	SKUService skuService;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	public SKUService getSkuService() {
		return skuService;
	}

	@Autowired
	public void setSkuService(SKUService skuService) {
		this.skuService = skuService;
	}
	
	@Override
	public List<SaleDetail> convert(String saleDetailsString) {
		TypeReference<List<SaleDetail>> saleDetailsType = new TypeReference<List<SaleDetail>>() {};
		List<SaleDetail> saleDetails = null;
		try {
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			saleDetails = objectMapper.readValue(saleDetailsString, saleDetailsType);
			
			for(SaleDetail saleDetail : saleDetails) {
				// Always take the price details from DB, not from UI.
				saleDetail.setSku(this.getSkuService().getSKU(saleDetail.getSku().getSkuId()));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return saleDetails;
	}

}
