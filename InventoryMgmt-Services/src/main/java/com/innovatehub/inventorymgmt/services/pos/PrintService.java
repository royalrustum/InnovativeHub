package com.innovatehub.inventorymgmt.services.pos;

import com.innovatehub.inventorymgmt.common.model.pos.Sale;

public interface PrintService {
	public byte[] generateSaleReceipt(Sale sale);
}
