package com.jbk.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class SupplierModel {

	@Min(value=1, message="Invalid Supplier Id")
	private long supplierid;
	
	@NotBlank(message="Supplier name should not be blank")
	@Pattern(regexp="^[a-zA-Z ]+$",message="Supplier name should only contain alphabets and spces")
	private String suppliernm;
	
	
	@Pattern(regexp="^[a-zA-Z ]+$",message="city name should only contain alphabets and spaces")
	@NotBlank(message="City Name should not be Blank")
	private String city;
	
	//887898
	@Min(value=100000,message="Invalid postalcode")
	@Max(value=999999,message="Invalid postalcode")
	private int postalcode;
	
	@NotBlank(message="Country Name Should Not Be  Blank")
	@Pattern(regexp="^[a-zA-Z ]+$",message="country name should only contain alphabets and spaces")
	private String country;
	

	
	@Pattern(regexp = "^[1-9][0-9]{9}$", message = "Mobile number should only contain digits, not start with 0, and be 10 digits long")
	private String mobile;

	public SupplierModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SupplierModel(long supplierid, String suppliernm, String city, int postalcode, String country,
			String mobile) {
		super();
		this.supplierid = supplierid;
		this.suppliernm = suppliernm;
		this.city = city;
		this.postalcode = postalcode;
		this.country = country;
		this.mobile = mobile;
	}

	public long getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(long supplierid) {
		this.supplierid = supplierid;
	}

	public String getSuppliernm() {
		return suppliernm;
	}

	public void setSuppliernm(String suppliernm) {
		this.suppliernm = suppliernm;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(int postalcode) {
		this.postalcode = postalcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "SupplierModel [supplierid=" + supplierid + ", suppliernm=" + suppliernm + ", city=" + city
				+ ", postalcode=" + postalcode + ", country=" + country + ", mobile=" + mobile + "]";
	}
	
	
}
