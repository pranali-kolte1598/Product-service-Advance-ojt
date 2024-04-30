package com.jbk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="product")
public class ProductEntity {

	@Id
	private long productid;
	@Column(nullable=false,unique=true)
	private String productnm;
	
	
	
	private long supplierid;
	
	
	private long  categoryid ;
	
	
	private int productqty;
	
	
	private double productprice;
	
	
	private int deliverycharges;
	
	public ProductEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductEntity(long productid, String productnm, long supplierid, long categoryid, int productqty,
			double productprice, int deliverycharges) {
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
		return "ProductEntity [productid=" + productid + ", productnm=" + productnm + ", supplierid=" + supplierid
				+ ", categoryid=" + categoryid + ", productqty=" + productqty + ", productprice=" + productprice
				+ ", deliverycharges=" + deliverycharges + "]";
	}

	
	
}
