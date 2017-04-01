package com.innovatehub.inventorymgmt.site.model;

import java.util.List;

import com.innovatehub.inventorymgmt.common.model.security.Screen;
import com.innovatehub.inventorymgmt.common.model.security.ScreenCategory;

public class LeftNavSection {

	private ScreenCategory header;

	private List<Screen> leftNavItems;
	
	public ScreenCategory getHeader() {
		return header;
	}

	public void setHeader(ScreenCategory header) {
		this.header = header;
	}

	public List<Screen> getLeftNavItems() {
		return leftNavItems;
	}

	public void setLeftNavItems(List<Screen> leftNavItems) {
		this.leftNavItems = leftNavItems;
	}
}
