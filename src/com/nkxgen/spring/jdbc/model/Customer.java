package com.nkxgen.spring.jdbc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customerTable")
public class Customer {

	@Id
	private Long cust_id;

	private String cust_title;

	private String cust_type;

	private String cust_caddress;

	private String cust_capincode;

	private String cust_dob;

	private String cust_mobile1;

	private String cust_mobile2;

	private String cust_rphone;

	private String cust_raddress;

	// Constructors

	public Customer() {
		// Default constructor
	}

	// Getters and Setters

	public Long getCust_id() {
		return cust_id;
	}

	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}

	public String getCust_title() {
		return cust_title;
	}

	@Override
	public String toString() {
		return "Customer [cust_id=" + cust_id + ", cust_title=" + cust_title + ", cust_type=" + cust_type
				+ ", cust_caddress=" + cust_caddress + ", cust_capincode=" + cust_capincode + ", cust_dob=" + cust_dob
				+ ", cust_mobile1=" + cust_mobile1 + ", cust_mobile2=" + cust_mobile2 + ", cust_rphone=" + cust_rphone
				+ ", cust_raddress=" + cust_raddress + "]";
	}

	public void setCust_title(String cust_title) {
		this.cust_title = cust_title;
	}

	public String getCust_type() {
		return cust_type;
	}

	public void setCust_type(String cust_type) {
		this.cust_type = cust_type;
	}

	public String getCust_caddress() {
		return cust_caddress;
	}

	public void setCust_caddress(String cust_caddress) {
		this.cust_caddress = cust_caddress;
	}

	public String getCust_capincode() {
		return cust_capincode;
	}

	public void setCust_capincode(String cust_capincode) {
		this.cust_capincode = cust_capincode;
	}

	public String getCust_dob() {
		return cust_dob;
	}

	public void setCust_dob(String cust_dob) {
		this.cust_dob = cust_dob;
	}

	public String getCust_mobile1() {
		return cust_mobile1;
	}

	public void setCust_mobile1(String cust_mobile1) {
		this.cust_mobile1 = cust_mobile1;
	}

	public String getCust_mobile2() {
		return cust_mobile2;
	}

	public void setCust_mobile2(String cust_mobile2) {
		this.cust_mobile2 = cust_mobile2;
	}

	public String getCust_rphone() {
		return cust_rphone;
	}

	public void setCust_rphone(String cust_rphone) {
		this.cust_rphone = cust_rphone;
	}

	public String getCust_raddress() {
		return cust_raddress;
	}

	public void setCust_raddress(String cust_raddress) {
		this.cust_raddress = cust_raddress;
	}
}
