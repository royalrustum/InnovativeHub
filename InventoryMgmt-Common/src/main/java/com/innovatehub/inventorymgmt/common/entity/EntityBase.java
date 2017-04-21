package com.innovatehub.inventorymgmt.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class EntityBase {

	private Date recordCreatedDate;

	private Date recordCreatedBy;
	
	private Date recordUpdatedBy;
	
	private Date recordUpdatedDate;

	@Column(name="CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getRecordCreatedDate() {
		return recordCreatedDate;
	}

	public void setRecordCreatedDate(Date recordCreatedDate) {
		this.recordCreatedDate = recordCreatedDate;
	}

	@Column(name="CREATED_BY")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getRecordCreatedBy() {
		return recordCreatedBy;
	}

	public void setRecordCreatedBy(Date recordCreatedBy) {
		this.recordCreatedBy = recordCreatedBy;
	}

	@Column(name="UPDATED_BY")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getRecordUpdatedBy() {
		return recordUpdatedBy;
	}

	public void setRecordUpdatedBy(Date recordUpdatedBy) {
		this.recordUpdatedBy = recordUpdatedBy;
	}
	
	@Column(name="UPDATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getRecordUpdatedDate() {
		return recordUpdatedDate;
	}

	public void setRecordUpdatedDate(Date recordUpdatedDate) {
		this.recordUpdatedDate = recordUpdatedDate;
	}
}
