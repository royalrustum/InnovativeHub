package com.innovatehub.inventorymgmt.common.model.stock;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.innovatehub.inventorymgmt.common.util.CommonUtilHelper;

public class ProductCategory {
	
	@NotEmpty
	private String categoryName;

	@NotNull
	@DecimalMin(value = "0.0")
	private BigDecimal taxPercent;

	private byte[] uploadedFileBytes;
	
	public String getBase64FileBytesString() {
		return CommonUtilHelper.getBase64ImageString(this.getUploadedFileBytes());
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public BigDecimal getTaxPercent() {
		return taxPercent;
	}

	public void setTaxPercent(BigDecimal taxPercent) {
		this.taxPercent = taxPercent;
	}
	
	public byte[] getUploadedFileBytes() {
		return uploadedFileBytes;
	}

	public void setUploadedFileBytes(byte[] uploadedFileBytes) {
		this.uploadedFileBytes = uploadedFileBytes;
	}
}
