package com.innovatehub.inventorymgmt.common.entity.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.innovatehub.inventorymgmt.common.entity.EntityBase;

@Entity
@Table(name = "ROLE_SCREEN_T")
public class Role_Screen extends EntityBase {
	private Long roleScreenId;
	
	private Role role;
	
	private Screen screen;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROLE_SCREEN_ID")
	public Long getRoleScreenId() {
		return roleScreenId;
	}

	public void setRoleScreenId(Long roleScreenId) {
		this.roleScreenId = roleScreenId;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE_ID")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SCREEN_ID")
	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}
}
