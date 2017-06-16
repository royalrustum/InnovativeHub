package com.innovatehub.inventorymgmt.services.pos;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.innovatehub.inventorymgmt.common.model.pos.Sale;
import com.itextpdf.text.DocumentException;

public interface PrintService {
	public void printSaleReceipt(Sale sale);
}
