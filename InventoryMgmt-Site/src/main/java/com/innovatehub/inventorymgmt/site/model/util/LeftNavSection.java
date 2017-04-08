package com.innovatehub.inventorymgmt.site.model.util;

import java.util.List;

import com.innovatehub.inventorymgmt.common.entity.security.Screen;
import com.innovatehub.inventorymgmt.common.entity.security.ScreenCategory;

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
