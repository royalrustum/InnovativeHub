package com.innovatehub.inventorymgmt.site.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.innovatehub.inventorymgmt.common.model.security.Screen;
import com.innovatehub.inventorymgmt.common.model.security.ScreenCategory;
import com.innovatehub.inventorymgmt.site.model.LeftNavSection;

public class GUIUtilHelper {
	public static List<LeftNavSection> PopulateLeftNavigation(Set<Screen> authorizedScreens) {
		// Get all the screen categories.
		List<ScreenCategory> screenCategories = authorizedScreens.stream().map(screen -> screen.getScreenCategory())
				.collect(Collectors.toList());

		// Remove duplicates.
		HashSet<String> screenCategoryNames = new HashSet<String>(
				screenCategories.stream().map(screenCat -> screenCat.getName()).collect(Collectors.toList()));

		List<LeftNavSection> leftNav = new ArrayList<LeftNavSection>();

		// Populate all screens in each category.
		for (String screenCategory : screenCategoryNames) {
			LeftNavSection navSection = new LeftNavSection();

			ScreenCategory category = screenCategories.stream().filter(cat -> cat.getName().equals(screenCategory))
					.findFirst().get();
			navSection.setHeader(category);

			navSection.setLeftNavItems(authorizedScreens.stream()
					.filter(screen -> screen.getScreenCategory().getName().equals(screenCategory)).collect(Collectors.toList()));

			leftNav.add(navSection);
		}

		return leftNav;
	}
}
