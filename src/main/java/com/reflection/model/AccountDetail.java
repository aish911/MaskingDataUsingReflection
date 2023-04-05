package com.reflection.model;

public class AccountDetail {

	private String userId;
	private String firstName;
	private String lastName;
	private Address address;
	private CreditCardDetail creditCardDetail;
	
	public AccountDetail(String userId, String firstName, String lastName, Address address, CreditCardDetail creditCardDetail) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.creditCardDetail = creditCardDetail;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFname() {
		return firstName;
	}
	public void setFname(String fname) {
		this.firstName = fname;
	}
	public String getLname() {
		return lastName;
	}
	public void setLname(String lname) {
		this.lastName = lname;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public CreditCardDetail getCredCard() {
		return creditCardDetail;
	}
	public void setCredCard(CreditCardDetail creditCardDetail) {
		this.creditCardDetail = creditCardDetail;
	}
	
	
}
