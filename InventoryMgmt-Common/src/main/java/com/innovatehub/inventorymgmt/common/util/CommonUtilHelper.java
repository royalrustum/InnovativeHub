package com.innovatehub.inventorymgmt.common.util;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class CommonUtilHelper {
	public static byte[] getByteArrayFromBlob(Blob blob) throws SQLException {
		return blob.getBytes(1, (int) blob.length());
	}
	
	public static String getBase64ImageString(byte[] imagesBytes) {
		return Base64.getEncoder().encodeToString(imagesBytes);
	}
}
