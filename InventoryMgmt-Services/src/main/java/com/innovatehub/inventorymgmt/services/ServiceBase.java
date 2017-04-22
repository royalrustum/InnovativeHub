package com.innovatehub.inventorymgmt.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.innovatehub.inventorymgmt.common.entity.EntityBase;
import com.innovatehub.inventorymgmt.services.security.SecurityService;

public class ServiceBase {

	private SecurityService securityService;
	
	public SecurityService getSecurityService() {
		return securityService;
	}

	@Autowired
	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public  <T extends EntityBase> void populateCreateAuditInfo(T entity) {
		entity.setRecordCreatedBy(this.getSecurityService().getCurrentUserName());
		entity.setRecordCreatedDate(new Date());
	}

	public <T extends EntityBase> void populateUpdateAuditInfo(T entity) {
		entity.setRecordUpdatedBy(this.getSecurityService().getCurrentUserName());
		entity.setRecordUpdatedDate(new Date());
	}

	public <T extends EntityBase> void populateAuditInfo(T entity) {
		this.populateCreateAuditInfo(entity);
		this.populateUpdateAuditInfo(entity);
	}
}
