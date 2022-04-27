package com.example.javaeeproject.localentities;

import java.io.Serializable;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import com.example.javaeeproject.entities.Address;
import com.example.javaeeproject.entities.Customer;
import com.example.javaeeproject.entities.NormalUser;
import com.example.javaeeproject.entities.TypeOfIndustry;

@Named(value = "localCustomer")
@RequestScoped
public class LocalCustomer implements Serializable{
	private int customerId;
	private String customerName;
	
	@Pattern(regexp = "^\\({0,1}((0|\\+61)(2|4|3|7|8)){0,1}\\){0,1}(\\ |-){0,1}[0-9]{2}(\\ |-){0,1}[0-9]{2}(\\ |-){0,1}[0-9]{1}(\\ |-){0,1}[0-9]{3}$", message="Only accept Australian phone number")
	private String customerPhoneNumber;
	
	@Pattern(regexp = "[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]", message="Please enter a valid customer email")
	private String customerEmail;
	
	@Pattern(regexp = "^((https?|ftp|smtp):\\/\\/)?(www.)?[a-z0-9]+\\.[a-z]+(\\/[a-zA-Z0-9#]+\\/?)*$", message="Please enter a valid customer website")
	private String customerWebsite;
	private String customerDescription;

	private Address address;
	private NormalUser normalUser;
	private TypeOfIndustry typeOfIndustry;

	private String streetNumber;
	private String streetAddress;
	private String suburb;
	private String postcode;
	private String state;

	private int clientId;
	private String clientEmail;
	private String clientPassword;
	private String clientName;
	private String clientRole = "NormalUser";
	private char user_type = 'N';
	private String normalUserPhoneNumber;
	
	private int typeOfIndustryId;
	private String typeOfIndustryName;

	private Set<Customer> customers;


	public LocalCustomer() {
	 
	}
	
	public LocalCustomer(int customerId, Address address, String customerName,
			String customerPhoneNumber, String customerEmail, String customerWebsite, String customerDescription,
			NormalUser normalUser, TypeOfIndustry typeOfIndustry) {
		this.customerId = customerId;
		this.address = address;
		this.customerName = customerName;
		this.customerPhoneNumber = customerPhoneNumber;
		this.customerEmail = customerEmail;
		this.customerWebsite = customerWebsite;
		this.customerDescription = customerDescription;
//		this.clientId = clientId;
		this.normalUser = normalUser;
		this.typeOfIndustry = typeOfIndustry;
	}
	

	// Address=========================================
	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	// NormalUser=================================
	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getClientPassword() {
		return clientPassword;
	}

	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientRole() {
		return clientRole;
	}

	public void setClientRole(String clientRole) {
		this.clientRole = clientRole;
	}

	public char getUser_type() {
		return user_type;
	}

	public void setUser_type(char user_type) {
		this.user_type = user_type;
	}

	public String getNormalUserPhoneNumber() {
		return normalUserPhoneNumber;
	}

	public void setNormalUserPhoneNumber(String normalUserPhoneNumber) {
		this.normalUserPhoneNumber = normalUserPhoneNumber;
	}
	
	// TypeOfIndustry=================================
	public int getTypeOfIndustryId() {
		return typeOfIndustryId;
	}

	public void setTypeOfIndustryId(int typeOfIndustryId) {
		this.typeOfIndustryId = typeOfIndustryId;
	}

	public String getTypeOfIndustryName() {
		return typeOfIndustryName;
	}

	public void setTypeOfIndustryName(String typeOfIndustryName) {
		this.typeOfIndustryName = typeOfIndustryName;
	}	

	// Customers==========================
	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	// Customer===========================
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

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

	public NormalUser getNormalUser() {
		return normalUser;
	}

	public void setNormalUser(NormalUser normalUser) {
		this.normalUser = normalUser;
	}

	public TypeOfIndustry getTypeOfIndustry() {
		return typeOfIndustry;
	}

	public void setTypeOfIndustry(TypeOfIndustry typeOfIndustry) {
		this.typeOfIndustry = typeOfIndustry;
	}

}
