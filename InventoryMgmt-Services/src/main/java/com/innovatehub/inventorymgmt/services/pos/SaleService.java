package com.innovatehub.inventorymgmt.services.pos;

import com.innovatehub.inventorymgmt.common.model.pos.Sale;

public interface SaleService {
	public Sale getSale(Long saleId);
	public Long saveSale(Sale sale);
}
