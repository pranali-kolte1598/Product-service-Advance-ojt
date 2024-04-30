package com.jbk.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;



public class ProductModel {

	private long productid;
	
	@NotBlank(message="product nm should not be blank")
	@Pattern(regexp="^[a-zA-Z ]+$",message="product name should only contain alphabets and spaces")
	private String productnm;
	
	
	private long supplierid;
	
	private long  categoryid ;
	
	@Min(value=1)
	private int productqty;
	
	@Min(value=1,message="product price should be greater than zero")
	private double productprice;
	
	@Min(value=0)
	private int deliverycharges;
	
	public ProductModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductModel(long productid,
			@NotBlank(message = "product nm should not be blank") @Pattern(regexp = "^[a-zA-Z ]+$", message = "product name should only contain alphabets and spaces") String productnm,
			long supplier, long category, @Min(1) int productqty,
			@Min(value = 1, message = "product price should be greater than zero") double productprice,
			@Min(0) int deliverycharges) {
		super();
		this.productid = productid;
		this.productnm = productnm;
		this.supplierid = supplierid;
		this.categoryid = categoryid;
		this.productqty = productqty;
		this.productprice = productprice;
		this.deliverycharges = deliverycharges;
	}

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	public String getProductnm() {
		return productnm;
	}

	public void setProductnm(String productnm) {
		this.productnm = productnm;
	}

	public long getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(long supplierid) {
		this.supplierid = supplierid;
	}

	public long getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(long categoryid) {
		this.categoryid = categoryid;
	}

	public int getProductqty() {
		return productqty;
	}

	public void setProductqty(int productqty) {
		this.productqty = productqty;
	}

	public double getProductprice() {
		return productprice;
	}

	public void setProductprice(double productprice) {
		this.productprice = productprice;
	}

	public int getDeliverycharges() {
		return deliverycharges;
	}

	public void setDeliverycharges(int deliverycharges) {
		this.deliverycharges = deliverycharges;
	}

	@Override
	public String toString() {
		return "ProductModel [productid=" + productid + ", productnm=" + productnm + ", supplierid=" + supplierid
				
				+ ", categoryid=" + categoryid + ", productqty=" + productqty + ", productprice=" + productprice
				+ ", deliverycharges=" + deliverycharges + "]";
	}
	
	
	
}
