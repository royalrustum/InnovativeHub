package com.innovatehub.inventorymgmt.common.entity.security;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.innovatehub.inventorymgmt.common.entity.EntityBase;

@Entity
@Table(name = "SCREEN_T")
@SequenceGenerator(name="ID_GEN_SEQ", sequenceName="SCREEN_ID_SEQ", initialValue=1, allocationSize=1)
public class Screen extends EntityBase {
	private Long id;

	private String url;

	private String screenName;

	private String screenIconName;

	private List<Role_Screen> roleScreens;

	private ScreenCategory screenCategory;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ID_GEN_SEQ")
	@Column(name = "SCREEN_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Column(name = "SCREEN_ICON_NAME")
	public String getScreenIconName() {
		return screenIconName;
	}

	public void setScreenIconName(String screenIconName) {
		this.screenIconName = screenIconName;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "screen")
	public List<Role_Screen> getRoleScreens() {
		return roleScreens;
	}

	public void setRoleScreens(List<Role_Screen> roleScreens) {
		this.roleScreens = roleScreens;
	}

	@ManyToOne(cascade = { CascadeType.REMOVE, CascadeType.DETACH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "SCREEN_CATEGORY_ID")
	public ScreenCategory getScreenCategory() {
		return screenCategory;
	}

	public void setScreenCategory(ScreenCategory screenCategory) {
		this.screenCategory = screenCategory;
	}
}
