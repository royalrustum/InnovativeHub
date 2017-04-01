package com.innovatehub.inventorymgmt.site.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.innovatehub.inventorymgmt.site.util.GUIUtilHelper;
import com.innovatehub.inventorymgmt.site.util.SessionCache;
import com.innovatehub.inventorymgmt.site.util.SiteConstants;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	SessionCache sessionCache;
	
	public SessionCache getSessionCache() {
		return sessionCache;
	}

	@Autowired
	public void setSessionCache(SessionCache sessionCache) {
		this.sessionCache = sessionCache;
	}
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {
		handle(request, response, authentication);

	}

	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		
		// Initialize SessionCache.
		this.sessionCache.initialize();

		redirectStrategy.sendRedirect(request, response, SiteConstants.DEFAULT_SUCCESS_URL);
	}
}
