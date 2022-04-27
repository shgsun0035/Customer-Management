package com.example.javaeeproject.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "customer")
@NamedQueries ({@NamedQuery (name = Customer.GET_ALL_QUERY_NAME, query = "SELECT c FROM Customer c ORDER BY c.customerId desc")})
public class Customer implements Serializable {

	public static final String GET_ALL_QUERY_NAME = "Customer.getAll";
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int customerId;
	private String customerName;
	
	private String customerPhoneNumber;
	private String customerEmail;
	private String customerWebsite;
	private String customerDescription;
	
	private Address address;
	
	@ManyToOne
	private NormalUser normalUser;
	
	@ManyToOne
	private TypeOfIndustry typeOfIndustry;
	
	@OneToMany (mappedBy = "customer")
	private Set<CustomerContact> customerContacts;
	
	public Customer () {
		
	}

	public Customer(int customerId, String customerName, String customerPhoneNumber, String customerEmail,
			String customerWebsite, String customerDescription, Address address, NormalUser normalUser,
			TypeOfIndustry typeOfIndustry, Set<CustomerContact> customerContacts) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPhoneNumber = customerPhoneNumber;
		this.customerEmail = customerEmail;
		this.customerWebsite = customerWebsite;
		this.customerDescription = customerDescription;
		this.address = address;
		this.normalUser = normalUser;
		this.typeOfIndustry = typeOfIndustry;
		this.customerContacts = customerContacts;
	}



	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column (name = "customer_phone_number")
	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerWebsite() {
		return customerWebsite;
	}

	public void setCustomerWebsite(String customerWebsite) {
		this.customerWebsite = customerWebsite;
	}

	public String getCustomerDescription() {
		return customerDescription;
	}

	public void setCustomerDescription(String customerDescription) {
		this.customerDescription = customerDescription;
	}

	@Embedded
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public NormalUser getNormalUser() {
		return normalUser;
	}

	public TypeOfIndustry getTypeOfIndustry() {
		return typeOfIndustry;
	}

	public void setTypeOfIndustry(TypeOfIndustry typeOfIndustry) {
		this.typeOfIndustry = typeOfIndustry;
	}

	public void setNormalUser(NormalUser normalUser) {
		this.normalUser = normalUser;
	}

	public Set<CustomerContact> getCustomerContacts() {
		return customerContacts;
	}

	public void setCustomerContacts(Set<CustomerContact> customerContacts) {
		this.customerContacts = customerContacts;
	}

	/*
	 * @Override public int hashCode() { final int prime = 31; int result = 1;
	 * result = prime * result + customerId; return result; }
	 * 
	 * @Override public boolean equals(Object obj) { if (this == obj) return true;
	 * if (obj == null) return false; if (getClass() != obj.getClass()) return
	 * false; Customer other = (Customer) obj; if (customerId != other.customerId)
	 * return false; return true; }
	 */
	 
	 @Override public String toString() { 
		 return "Customer Name: " + customerName + " | Customer Address: " + address.toString() + " | Customer Industry: " +
	 typeOfIndustry.getTypeOfIndustryName() + " | Customer Phone Number: " + customerPhoneNumber +
	 " | Customer Email: " + customerEmail + " | Customer Website: " + customerWebsite +
	 " | Customer Description: " + customerDescription; 
		 }
	
}