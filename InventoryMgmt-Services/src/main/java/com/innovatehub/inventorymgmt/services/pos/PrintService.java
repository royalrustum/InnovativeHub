package com.innovatehub.inventorymgmt.services.pos;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.innovatehub.inventorymgmt.common.model.pos.Sale;
import com.itextpdf.text.DocumentException;

public interface PrintService {
	public byte[] generateSaleReceipt(Sale sale);
}
