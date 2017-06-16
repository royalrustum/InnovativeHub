package com.innovatehub.inventorymgmt.services.pos;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.text.DocumentException;

public interface PrintService {
	public void printSaleReceipt() throws IOException, FileNotFoundException, DocumentException;
}
