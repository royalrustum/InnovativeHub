package com.innovatehub.inventorymgmt.services.stock;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovatehub.inventorymgmt.common.model.stock.Product;
import com.innovatehub.inventorymgmt.common.repository.stock.ProductCategoryRepository;
import com.innovatehub.inventorymgmt.common.repository.stock.ProductRepository;
import com.innovatehub.inventorymgmt.common.util.CommonUtilHelper;
import com.innovatehub.inventorymgmt.services.ServiceBase;

@Service
@Transactional
public class ProductServiceImpl extends ServiceBase implements ProductService {

	private ProductRepository productRepo;

	private ProductCategoryRepository prodCatRepo;

	public ProductRepository getProductRepo() {
		return productRepo;
	}

	@Autowired
	public void setProductRepo(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}

	public ProductCategoryRepository getProdCatRepo() {
		return prodCatRepo;
	}

	@Autowired
	public void setProdCatRepo(ProductCategoryRepository prodCatRepo) {
		this.prodCatRepo = prodCatRepo;
	}

	@Override
	public Product getProduct(Long productId) {
		com.innovatehub.inventorymgmt.common.entity.stock.Product productEntity = this.getProductRepo()
				.findOne(productId);

		Product product = new Product();
		BeanUtils.copyProperties(productEntity, product);

		if (productEntity.getProductImage() != null) {
			product.setProductImage(CommonUtilHelper.getByteArrayFromBlob(productEntity.getProductImage()));
		}

		return product;
	}

	@Override
	public Long saveProduct(Product product) {
		com.innovatehub.inventorymgmt.common.entity.stock.Product productEntity = new com.innovatehub.inventorymgmt.common.entity.stock.Product();
		BeanUtils.copyProperties(product, productEntity);

		if (product.getProductImage() != null) {
			productEntity.setProductImage(
					Hibernate.getLobCreator(this.getSessionFactory().getObject().getCurrentSession()).createBlob(product.getProductImage()));
		}

		if (product.getSelectedProdCategory() != null) {
			// Get the session tracked object from hibernate. Product Category
			// will exist prior to product creation
			com.innovatehub.inventorymgmt.common.entity.stock.ProductCategory prodCategoryEntity = this.getProdCatRepo()
					.findOne(product.getSelectedProdCategory().getProductCategoryId());

			productEntity.setProductCategory(prodCategoryEntity);
		}

		this.populateAuditInfo(productEntity);
		return this.getProductRepo().save(productEntity).getProductId();
	}

	@Override
	public List<Product> getAllProducts() {
		List<com.innovatehub.inventorymgmt.common.entity.stock.Product> productEntities = this.getProductRepo()
				.findAll();

		List<com.innovatehub.inventorymgmt.common.model.stock.Product> products = convertToProductModel(
				productEntities);
		return products;
	}

	public List<Product> getAllProductsInCategory(Long categoryId) {
		List<com.innovatehub.inventorymgmt.common.entity.stock.Product> productEntities = this.getProductRepo()
				.findByProductCategoryProductCategoryId(categoryId);
		
		return this.convertToProductModel(productEntities);
	}

	private List<com.innovatehub.inventorymgmt.common.model.stock.Product> convertToProductModel(
			List<com.innovatehub.inventorymgmt.common.entity.stock.Product> productEntities) {
		List<com.innovatehub.inventorymgmt.common.model.stock.Product> products = new ArrayList<com.innovatehub.inventorymgmt.common.model.stock.Product>();

		for (com.innovatehub.inventorymgmt.common.entity.stock.Product productEntity : productEntities) {
			com.innovatehub.inventorymgmt.common.model.stock.Product product = new com.innovatehub.inventorymgmt.common.model.stock.Product();
			
			BeanUtils.copyProperties(productEntity, product);

			if (productEntity.getProductImage() != null) {
				product.setProductImage(CommonUtilHelper.getByteArrayFromBlob(productEntity.getProductImage()));
			}

			com.innovatehub.inventorymgmt.common.model.stock.ProductCategory productCategoryModel = new com.innovatehub.inventorymgmt.common.model.stock.ProductCategory();
			BeanUtils.copyProperties(productEntity.getProductCategory(), productCategoryModel);
			product.setSelectedProdCategory(productCategoryModel);
			
			products.add(product);
		}
		return products;
	}
}
