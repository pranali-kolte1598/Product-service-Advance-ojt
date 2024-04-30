
package com.jbk.daoimpl;


import java.util.List;

import javax.persistence.RollbackException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.dao.ProductDao;


import com.jbk.entity.ProductEntity;
import com.jbk.exception.ResourceAlreadyExistsException;
import com.jbk.exception.ResourceNotExistsException;
import com.jbk.exception.SomethingWentWrongException;
import com.jbk.model.ProductModel;
@Repository
public class Productdaoimpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addProduct(ProductEntity productEntity) {
		boolean isAdded = false;

		try (Session session = sessionFactory.openSession()) {
			Transaction transaction;

			// check its exists or not
			ProductEntity dbEntity = getProductByName(productEntity.getProductnm());

			if (dbEntity == null) {
				session.save(productEntity); // insert into
				transaction = session.beginTransaction();
				transaction.commit();
				isAdded = true;

				// session=null;
			} else {
				throw new ResourceAlreadyExistsException(
						"Product Already Exists with Id : " + productEntity.getProductid());
			}

		}

		catch (RollbackException e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong in during add product, check unique fields");
		}

		return isAdded;
	}

	@Override
	public ProductEntity getProductById(long productid) {
		ProductEntity productEntity = null;
		try {
			Session session = sessionFactory.openSession();

			productEntity = session.get(ProductEntity.class, productid); // select * from product where
																			// productId=10;
		} catch (HibernateException e) {
			throw new SomethingWentWrongException("Connection Failure");
		}
		return productEntity;
	}

	

	@Override
	public boolean deleteProductById(ProductEntity productEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateProduct(ProductEntity productEntity) {
		boolean isUpdated = false;
		try {
			Session session = sessionFactory.openSession();

			ProductEntity dbProduct = getProductById(productEntity.getProductid());

			if (dbProduct != null) {
				session.update(productEntity);
				session.beginTransaction().commit();
				isUpdated = true;

			} else {
				isUpdated = false;
				// throw new ResourceNotExistsException("Product Not Exists with ID : " +
				// productEntity.getProductId());
			}

		}
//		catch (ResourceNotExistsException e) {
//			throw new ResourceNotExistsException("Product Not Exists with ID : " + productEntity.getProductId());
//		}
		catch (Exception e) {
			throw new SomethingWentWrongException("Something Went Wrong During Update Product");
		}
		return isUpdated;
	}
	@Override
	public List<ProductEntity> sortProduct(String orderType, String property) {
		List<ProductEntity> list = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(ProductEntity.class);

			if (orderType.equalsIgnoreCase("desc")) {
				criteria.addOrder(Order.desc(property));
			} else {
				criteria.addOrder(Order.asc(property));
			}

			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ProductEntity> getAllProducts() {
		List<ProductEntity> list = null;
		try {
			Session session = sessionFactory.openSession();
			// criteria
			Criteria criteria = session.createCriteria(ProductEntity.class);

			list = criteria.list(); // select * from product

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something Went Wrong During retrive all Product");
		}
		return list;
	}

	@Override
	public List<ProductEntity> getMaxPriceProduct() {
		double maxProductprice = getMaxProductprice();
		List<ProductEntity> list = null;
		if (maxProductprice > 0) {

			// find max price product
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(ProductEntity.class);

			// select * from product where product_price=?
			criteria.add(Restrictions.eq("productprice", maxProductprice)); // filter query

			list = criteria.list();

		} else {
			throw new ResourceNotExistsException("Product Not Exists");
		}

		return list;

	

	}


	
	@Override
	public List<ProductEntity> getAllProducts(double low, double high) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductEntity> getproductStartWith(String experssion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double productPriceAverage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double countOfTotalProducts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Double getMaxProductprice() {
		double maxPrice = 0;
		try {
			Session session = sessionFactory.openSession();

			Criteria criteria = session.createCriteria(ProductEntity.class);

			Projection productPriceProjection = Projections.max("productprice");

			criteria.setProjection(productPriceProjection); /// select max(productPrice) from ProductEntity

			List list = criteria.list();
			if (!list.isEmpty()) {
				maxPrice = (double) list.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return maxPrice;
	}

	@Override
	public ProductEntity getProductByName(String productnm) {

		// select * from product where productnm=? // Restrictions

		// HQL: from ProductEntity where productnm= :parameternm (parameternm :
		// pname)
		List<ProductEntity> list = null;
		ProductEntity productEntity = null;
		try (Session session = sessionFactory.openSession()) {

			Query<ProductEntity> query = session.createQuery("FROM ProductEntity WHERE productnm= :name");

			query.setParameter("name", productnm);

			list = query.list();

			if (!list.isEmpty()) {
				productEntity = list.get(0);
			} else {
				return null;
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return productEntity;
	}

	

	
}
