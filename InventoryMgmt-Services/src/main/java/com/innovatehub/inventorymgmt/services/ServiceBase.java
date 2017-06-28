package com.innovatehub.inventorymgmt.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.innovatehub.inventorymgmt.common.entity.EntityBase;
import com.innovatehub.inventorymgmt.services.security.SecurityService;
import javax.servlet.ServletContext;

@SuppressWarnings("unused")
public class ServiceBase {

	private SecurityService securityService;
	
	@Value("${server.contextPath}")
	private String contextRoot;
	
	private String contextRootUrl;
	
	@Autowired
	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public String getContextRoot() {
		return contextRoot;
	}

	public void setContextRoot(String contextRoot) {
		this.contextRoot = contextRoot;
	}

	public SecurityService getSecurityService() {
		return securityService;
	}
	
	public String getContextRootUrl() {
		return contextRootUrl;
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
