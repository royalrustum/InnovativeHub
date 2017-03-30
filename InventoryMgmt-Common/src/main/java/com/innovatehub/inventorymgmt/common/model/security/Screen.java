package com.innovatehub.inventorymgmt.common.model.security;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Screen_T")
public class Screen {
	private int id;
	
	private String url;
	
	private String screenName;
	
	private String screenCategory;
	
	private String screenIconName;
	
	private String screenCategoryIconName;

	private Set<Role> roles;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "SCREEN_ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "SCREEN_URL")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "SCREEN_NAME")
	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	
	@Column(name = "SCREEN_CATEGORY")
	public String getScreenCategory() {
		return screenCategory;
	}

	public void setScreenCategory(String screenCategory) {
		this.screenCategory = screenCategory;
	}
	
	@Column(name="SCREEN_ICON_NAME")
	public String getScreenIconName() {
		return screenIconName;
	}

	public void setScreenIconName(String screenIconName) {
		this.screenIconName = screenIconName;
	}
	
	@Column(name="SCREEN_CATEGORY_ICON_NAME")
	public String getScreenCategoryIconName() {
		return screenCategoryIconName;
	}

	public void setScreenCategoryIconName(String screenCategoryIconName) {
		this.screenCategoryIconName = screenCategoryIconName;
	}
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="screens")
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
