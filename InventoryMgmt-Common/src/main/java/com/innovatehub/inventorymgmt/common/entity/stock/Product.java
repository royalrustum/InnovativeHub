package com.innovatehub.inventorymgmt.common.entity.stock;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_T")
public class Product {
	private Long productId;

	private String productName;

	private ProductCategory productCategory;

	private Blob productImage;
	
	private BigDecimal margin;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	@Column(name = "PROFIT_MARGIN")
	public BigDecimal getMargin() {
		return margin;
	}

	public void setMargin(BigDecimal margin) {
		this.margin = margin;
	}

}
