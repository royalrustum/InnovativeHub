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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.innovatehub.inventorymgmt.common.entity.EntityBase;

@Entity
@Table(name = "PRODUCT_CATEGORY_T")
@SequenceGenerator(name="ID_GEN_SEQ", sequenceName="PRODUCT_CATEGORY_ID_SEQ", initialValue=1, allocationSize=1)
public class ProductCategory extends EntityBase {
	private Long productCategoryId;
	
	private String categoryName;

	private BigDecimal taxPercent;
	
	private Blob categoryImage;
	
	private List<Product> products;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ID_GEN_SEQ")
	@Column(name = "PRODUCT_CATEGORY_ID")
	public Long getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	
	@Column(name = "CATEGORY_NAME")
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Column(name = "TAX_PERCENT")
	@NotNull
	public BigDecimal getTaxPercent() {
		return taxPercent;
	}

	public void setTaxPercent(BigDecimal taxPercent) {
		this.taxPercent = taxPercent;
	}
	
	@Lob
	@Column(name="CATEGORY_IMAGE")
	public Blob getCategoryImage() {
		return categoryImage;
	}

	public void setCategoryImage(Blob categoryImage) {
		this.categoryImage = categoryImage;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "productCategory")
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
