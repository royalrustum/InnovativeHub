package com.innovatehub.inventorymgmt.common.model.security;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="USER_T")
public class User {

	private String userName;
	
	private String password;
	
	private Long userId;

	private Set<Role> roles;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@ManyToMany
	@JoinTable(name="USER_ROLE_T", 
		joinColumns=@JoinColumn(name="USER_ID"),
		inverseJoinColumns=@JoinColumn(name="ROLE_ID"))
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@Column(name="USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
