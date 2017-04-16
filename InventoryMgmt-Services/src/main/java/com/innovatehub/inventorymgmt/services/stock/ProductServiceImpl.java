package com.innovatehub.inventorymgmt.services.stock;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovatehub.inventorymgmt.common.model.stock.Product;
import com.innovatehub.inventorymgmt.common.repository.stock.ProductRepository;
import com.innovatehub.inventorymgmt.common.util.CommonUtilHelper;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepo;

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public ProductRepository getProductRepo() {
		return productRepo;
	}

	@Autowired
	public void setProductRepo(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}

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

	public Long saveProduct(Product product) {
		com.innovatehub.inventorymgmt.common.entity.stock.Product productEntity = new com.innovatehub.inventorymgmt.common.entity.stock.Product();
		BeanUtils.copyProperties(product, productEntity);

		if (product.getProductImage() != null) {
			productEntity.setProductImage(Hibernate.getLobCreator(sessionFactory.getCurrentSession())
					.createBlob(product.getProductImage()));
		}
		
		return this.getProductRepo().save(productEntity).getProductId();
	}

}
