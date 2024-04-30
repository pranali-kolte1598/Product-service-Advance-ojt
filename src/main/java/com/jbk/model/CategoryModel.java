package com.jbk.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CategoryModel {

	@Min(value = 1, message = "Invalid category Id")
	private long categoryid;
	
	@NotBlank(message = "Category Name Should Not be Blank")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "category name should only contain alphabets and spaces")
	private String categorynm;
	
	@NotBlank(message = "Description Name Should Not be Blank")
	private String description;
	
	@Min(value = 1, message = "Invalid category Id")
	private int discount;
	
	@Min(value = 1, message = "Invalid category Id")
	private int gst;
	
	public CategoryModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CategoryModel(long categoryid, String categorynm, String description, int discount, int gst) {
		super();
		this.categoryid = categoryid;
		this.categorynm = categorynm;
		this.description = description;
		this.discount = discount;
		this.gst = gst;
	}
	public long getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(long categoryid) {
		this.categoryid = categoryid;
	}
	public String getCategorynm() {
		return categorynm;
	}
	public void setCategorynm(String categorynm) {
		this.categorynm = categorynm;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getGst() {
		return gst;
	}
	public void setGst(int gst) {
		this.gst = gst;
	}
	@Override
	public String toString() {
		return "CategoryModel [categoryid=" + categoryid + ", categorynm=" + categorynm + ", description=" + description
				+ ", discount=" + discount + ", gst=" + gst + "]";
	}
	
}
