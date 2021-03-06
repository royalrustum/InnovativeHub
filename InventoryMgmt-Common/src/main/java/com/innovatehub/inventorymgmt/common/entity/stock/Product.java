package com.innovatehub.inventorymgmt.common.entity.stock;

import java.sql.Blob;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.innovatehub.inventorymgmt.common.entity.EntityBase;

@Entity
@Table(name = "PRODUCT_T")
@SequenceGenerator(name="ID_GEN_SEQ", sequenceName="PRODUCT_ID_SEQ", initialValue=1, allocationSize=1)
public class Product extends EntityBase {
	private Long productId;

	private String productName;

	private ProductCategory productCategory;

	private Blob productImage;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ID_GEN_SEQ")
	@Column(name = "PRODUCT_ID")
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "PRODUCT_NAME")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT_CATEGORY_ID")
	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	@Lob
	@Column(name = "PRODUCT_IMAGE")
	public Blob getProductImage() {
		return productImage;
	}

	public void setProductImage(Blob categoryImage) {
		this.productImage = categoryImage;
	}

}
