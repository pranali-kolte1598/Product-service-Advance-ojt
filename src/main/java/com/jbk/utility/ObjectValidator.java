package com.jbk.utility;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbk.exception.ResourceNotExistsException;
import com.jbk.exception.SomethingWentWrongException;
import com.jbk.model.ProductModel;



@Component
public class ObjectValidator {

	
	
	
	
	
	public  Map<String,String> validateProduct(ProductModel productModel){
		Map<String,String>validationMap=new HashMap<String,String>();
		String productName=productModel.getProductnm();
		double productPrice=productModel.getProductprice();
		int productQty=productModel.getProductqty();
		int deliveryCharges=productModel.getDeliverycharges();
		long supplierId=productModel.getSupplierid();
		
		long categoryId=productModel.getCategoryid();
		
		if(productName==null || productName.trim().equals("")) {
		validationMap.put("productName", "invalid productName");	
		}
		
		if(productPrice<=0 ) {
			validationMap.put("product price", "product price must be greater than zero");	
			}
		if(productQty<=0) {
			validationMap.put("product qty", "product qty must be greater than zero");	
		}
		if(deliveryCharges<0) {
			validationMap.put("charges", "delivery charges should not be nigative");
		}
		if(supplierId>0) {
			try {
			// supplierService.getSupplierById(supplierId);
			
			
			}catch(ResourceNotExistsException e) {
				validationMap.put("Supplier", e.getMessage());
			}catch(SomethingWentWrongException e) {
				validationMap.put("Supplier", e.getMessage());
			}
		
		}else {
			validationMap.put("Supplier", "Invalid supplier Id");
		}
		
		
		if(categoryId>0) {
			try {
			// categoryService.getCategoryById(categoryId);
			
			
			}catch(ResourceNotExistsException e) {
				validationMap.put("category", e.getMessage());
			}catch(SomethingWentWrongException e) {
				validationMap.put("category", e.getMessage());
			}
		
		}else {
			validationMap.put("category", "Invalid category Id");
		}
		
		
		
		
		
		
		
		
		
		return validationMap;
	}
}
