package com.example.javaeeproject.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

@Entity
@NamedQueries({@NamedQuery(name = CustomerContact.GET_ALL_QUERY_NAME, query = "SELECT co FROM CustomerContact co order by co.customerContactId desc")})
public class CustomerContact implements Serializable{
	
	public static final String GET_ALL_QUERY_NAME = "CustomerContact.getAll";
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int customerContactId;
	private String customerContactName;
	private String customerContactPhoneNumber;
	private String customerContactGender;
	
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date customerContactDob;
	private double customerContactWorkYears;
	
	@ManyToOne
	private Customer customer;
	
	public CustomerContact () {
	}

	public CustomerContact(int customerContactId, String customerContactName, String customerContactPhoneNumber,
			String customerContactGender, Date customerContactDob, double customerContactWorkYears, Customer customer) {
		this.customerContactId = customerContactId;
		this.customerContactName = customerContactName;
		this.customerContactPhoneNumber = customerContactPhoneNumber;
		this.customerContactGender = customerContactGender;
		this.customerContactDob = customerContactDob;
		this.customerContactWorkYears = customerContactWorkYears;
		this.customer = customer;
	}

	public int getCustomerContactId() {
		return customerContactId;
	}

	public void setCustomerContactId(int customerContactId) {
		this.customerContactId = customerContactId;
	}

	public String getCustomerContactName() {
		return customerContactName;
	}

	public void setCustomerContactName(String customerContactName) {
		this.customerContactName = customerContactName;
	}

	public String getCustomerContactPhoneNumber() {
		return customerContactPhoneNumber;
	}

	public void setCustomerContactPhoneNumber(String customerContactPhoneNumber) {
		this.customerContactPhoneNumber = customerContactPhoneNumber;
	}

	public String getCustomerContactGender() {
		return customerContactGender;
	}

	public void setCustomerContactGender(String customerContactGender) {
		this.customerContactGender = customerContactGender;
	}

	public Date getCustomerContactDob() {
		return customerContactDob;
	}

	public void setCustomerContactDob(Date customerContactDob) {
		this.customerContactDob = customerContactDob;
	}

	public double getCustomerContactWorkYears() {
		return customerContactWorkYears;
	}

	public void setCustomerContactWorkYears(double customerContactWorkYears) {
		this.customerContactWorkYears = customerContactWorkYears;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "CustomerContact [customerContactId=" + customerContactId + ", customerContactName="
				+ customerContactName + ", customerContactPhoneNumber=" + customerContactPhoneNumber
				+ ", customerContactGender=" + customerContactGender + ", customerContactDob=" + customerContactDob
				+ ", customerContactWorkYears=" + customerContactWorkYears + ", customer=" + customer + "]";
	}
	
}