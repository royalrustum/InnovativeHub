package com.innovatehub.inventorymgmt.services.stock;

import java.sql.SQLException;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovatehub.inventorymgmt.common.entity.stock.ProductCategory;
import com.innovatehub.inventorymgmt.common.repository.stock.ProductCategoryRepository;
import com.innovatehub.inventorymgmt.common.util.CommonUtilHelper;

@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService {

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

		return this.getProductCategoryRepo().save(prodCategoryEntity).getProductCategoryId();
	}

	@Override
	public com.innovatehub.inventorymgmt.common.model.stock.ProductCategory getProductCategory(int productCategoryId)
			throws SQLException {
		ProductCategory prodCategoryEntity = this.getProductCategoryRepo().findOne(productCategoryId);

		com.innovatehub.inventorymgmt.common.model.stock.ProductCategory productCategory = new com.innovatehub.inventorymgmt.common.model.stock.ProductCategory();
		BeanUtils.copyProperties(prodCategoryEntity, productCategory);

		if (prodCategoryEntity.getCategoryImage() != null) {
			productCategory
					.setUploadedFileBytes(CommonUtilHelper.getByteArrayFromBlob(prodCategoryEntity.getCategoryImage()));
		}

		return productCategory;

	}

}
