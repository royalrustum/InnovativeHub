package com.innovatehub.inventorymgmt.common.entity.security;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.innovatehub.inventorymgmt.common.entity.EntityBase;

@Entity
@Table(name = "ROLE_T")
@SequenceGenerator(name="ID_GEN_SEQ", sequenceName="ROLE_ID_SEQ", initialValue=1, allocationSize=1)
public class Role extends EntityBase {
	private String name;

	private Set<User> users;

	private List<Role_Screen> roleScreens;

	private Long roleId;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ID_GEN_SEQ")
	@Column(name = "ROLE_ID")
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy="role")
	//@JoinTable(name = "ROLE_SCREEN_T", joinColumns = @JoinColumn(name = "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "SCREEN_ID"))
	public List<Role_Screen> getRoleScreens() {
		return roleScreens;
	}

	public void setRoleScreens(List<Role_Screen> roleScreens) {
		this.roleScreens = roleScreens;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
