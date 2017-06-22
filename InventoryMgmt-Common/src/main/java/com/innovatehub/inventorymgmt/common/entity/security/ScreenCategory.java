package com.innovatehub.inventorymgmt.common.entity.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.innovatehub.inventorymgmt.common.entity.EntityBase;

@Entity
@Table(name = "SCREEN_CATEGORY_T")
@SequenceGenerator(name="ID_GEN_SEQ", sequenceName="SCREEN_CATEGORY_ID_SEQ", initialValue=1, allocationSize=1)
public class ScreenCategory extends EntityBase {
	private Long id;

	private String name;

	private String iconName;
	
	private Integer order;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ID_GEN_SEQ")
	@Column(name="SCREEN_CATEGORY_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "ICON_NAME")
	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	@Column(name = "SEQUENCE_NUMBER")
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
}
