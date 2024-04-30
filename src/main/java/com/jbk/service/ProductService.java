package com.jbk.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.jbk.model.ProductModel;
import com.jbk.model.Product_Supplier_Category;

public interface ProductService {

	
	
	public boolean addProduct(ProductModel product);
	
	public ProductModel getProductById(long productid);
	public boolean deleteProductById(long productid);
	public boolean updateProduct(ProductModel product);
	
	public List< ProductModel>getAllProducts ();
	public List<ProductModel>getAllProducts(double low,double high);
	public List<ProductModel>getProductStartWith(String expression);
	public double productPriceAverage();

	public double countOfTotalProducts();
	
	public List<ProductModel>getAllProducts(long category,long supplier);
	public List<ProductModel>getAllProducts(String supplier);
	public List<ProductModel> getMaxPriceProduct();
	public double getMaxProductPrice();
	public List<ProductModel>sortProduct(String orderType,String property);

	public ProductModel getProductByName(String productnm);
	
	public Map<String,Object> uploadedSheet(MultipartFile file);
	
	public Product_Supplier_Category getProductWithSCByPId(long productid);
	
}
