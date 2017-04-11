package com.innovatehub.inventorymgmt.site.util;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class GenericUtilHelper {
	public static byte[] getByteArrayFromMultiPart(MultipartFile multiPartFile) throws IOException {
		if ((multiPartFile !=null) && !multiPartFile.isEmpty()) {
			return multiPartFile.getBytes();
		} else {
			return null;
		}
	}
}
