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
	
	private String screenIconName;
	
	private Set<Role> roles;
	
	private ScreenCategory screenCategory;
	
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
	
	@Column(name="SCREEN_ICON_NAME")
	public String getScreenIconName() {
		return screenIconName;
	}

	public void setScreenIconName(String screenIconName) {
		this.screenIconName = screenIconName;
	}
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="screens")
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="SCREEN_CATEGORY_ID")
	public ScreenCategory getScreenCategory() {
		return screenCategory;
	}

	public void setScreenCategory(ScreenCategory screenCategory) {
		this.screenCategory = screenCategory;
	}
}
