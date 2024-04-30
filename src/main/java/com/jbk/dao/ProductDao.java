package com.jbk.dao;

import java.util.List;

import com.jbk.entity.ProductEntity;


public interface ProductDao {
	public boolean addProduct(ProductEntity product);

	public ProductEntity getProductById(long productId);

	public boolean deleteProductById(ProductEntity  productEntity);

	public boolean updateProduct(ProductEntity productEntity );

	public List<ProductEntity> getMaxPriceProduct();
	public List<ProductEntity>getAllProducts();
	public List<ProductEntity> sortProduct(String orderType,String property);
	public List<ProductEntity>getAllProducts(double low,double high);
	public List<ProductEntity>getproductStartWith(String experssion);
	public double productPriceAverage();
	public double countOfTotalProducts();
	public Double getMaxProductprice();
	public ProductEntity getProductByName(String productName);
}


