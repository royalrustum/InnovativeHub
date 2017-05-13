package com.innovatehub.inventorymgmt.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ModelBase {

	private Date recordCreatedDate;

	private String recordCreatedBy;
	
	private String recordUpdatedBy;
	
	private Date recordUpdatedDate;

	public Date getRecordCreatedDate() {
		return recordCreatedDate;
	}

	public void setRecordCreatedDate(Date recordCreatedDate) {
		this.recordCreatedDate = recordCreatedDate;
	}

	public String getRecordCreatedBy() {
		return recordCreatedBy;
	}

	public void setRecordCreatedBy(String recordCreatedBy) {
		this.recordCreatedBy = recordCreatedBy;
	}

	public String getRecordUpdatedBy() {
		return recordUpdatedBy;
	}

	public void setRecordUpdatedBy(String recordUpdatedBy) {
		this.recordUpdatedBy = recordUpdatedBy;
	}
	
	public Date getRecordUpdatedDate() {
		return recordUpdatedDate;
	}

	public void setRecordUpdatedDate(Date recordUpdatedDate) {
		this.recordUpdatedDate = recordUpdatedDate;
	}
}
