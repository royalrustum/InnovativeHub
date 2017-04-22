package com.innovatehub.inventorymgmt.services.stock;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovatehub.inventorymgmt.common.entity.stock.ProductCategory;
import com.innovatehub.inventorymgmt.common.repository.stock.ProductCategoryRepository;
import com.innovatehub.inventorymgmt.common.util.CommonUtilHelper;
import com.innovatehub.inventorymgmt.services.ServiceBase;

@Service
@Transactional
public class ProductCategoryServiceImpl extends ServiceBase implements ProductCategoryService {

	@Autowired
	ProductCategoryRepository productCategoryRepo;

	@Autowired
	SessionFactory sessionFactory;

	public ProductCategoryRepository getProductCategoryRepo() {
		return productCategoryRepo;
	}

	public void setProductCategoryRepo(ProductCategoryRepository productCategoryRepo) {
		this.productCategoryRepo = productCategoryRepo;
	}

	@Override
	public int saveProductCategory(com.innovatehub.inventorymgmt.common.model.stock.ProductCategory productCategory) {
		ProductCategory prodCategoryEntity = new ProductCategory();
		BeanUtils.copyProperties(productCategory, prodCategoryEntity);

		if (productCategory.getUploadedFileBytes() != null) {
			prodCategoryEntity.setCategoryImage(Hibernate.getLobCreator(sessionFactory.getCurrentSession())
					.createBlob(productCategory.getUploadedFileBytes()));
		}

		this.populateAuditInfo(prodCategoryEntity);
		return this.getProductCategoryRepo().save(prodCategoryEntity).getProductCategoryId();
	}

	@Override
	public com.innovatehub.inventorymgmt.common.model.stock.ProductCategory getProductCategory(int productCategoryId) {
		ProductCategory prodCategoryEntity = this.getProductCategoryRepo().findOne(productCategoryId);

		if (prodCategoryEntity != null) {
			com.innovatehub.inventorymgmt.common.model.stock.ProductCategory productCategory = new com.innovatehub.inventorymgmt.common.model.stock.ProductCategory();
			BeanUtils.copyProperties(prodCategoryEntity, productCategory);

			if (prodCategoryEntity.getCategoryImage() != null) {
				productCategory.setUploadedFileBytes(
						CommonUtilHelper.getByteArrayFromBlob(prodCategoryEntity.getCategoryImage()));
			}
			
			return productCategory;
		} else {
			return null;
		}
	}

	@Override
	public List<com.innovatehub.inventorymgmt.common.model.stock.ProductCategory> getAllProductCategories() {
		List<ProductCategory> prodCategoryEntities = this.getProductCategoryRepo().findAll();

		List<com.innovatehub.inventorymgmt.common.model.stock.ProductCategory> prodCategories = new ArrayList<com.innovatehub.inventorymgmt.common.model.stock.ProductCategory>();

		for (ProductCategory productCategoryEntity : prodCategoryEntities) {
			com.innovatehub.inventorymgmt.common.model.stock.ProductCategory productCategory = new com.innovatehub.inventorymgmt.common.model.stock.ProductCategory();
			BeanUtils.copyProperties(productCategoryEntity, productCategory);

			if (productCategoryEntity.getCategoryImage() != null) {
				productCategory.setUploadedFileBytes(
						CommonUtilHelper.getByteArrayFromBlob(productCategoryEntity.getCategoryImage()));
			}

			prodCategories.add(productCategory);
		}
		return prodCategories;
	}

}
