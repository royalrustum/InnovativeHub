package com.innovatehub.inventorymgmt.common.util;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class CommonUtilHelper {
	public static byte[] getByteArrayFromBlob(Blob blob) throws SQLException {
		if (blob != null) {
			return blob.getBytes(1, (int) blob.length());
		} else {
			return null;
		}
	}

	public static String getBase64ImageString(byte[] imagesBytes) {
		if (imagesBytes != null)
			return Base64.getEncoder().encodeToString(imagesBytes);
		else
			return null;
	}
}
