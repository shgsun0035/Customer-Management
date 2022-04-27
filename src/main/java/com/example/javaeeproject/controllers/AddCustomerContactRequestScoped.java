package com.example.javaeeproject.controllers;


import java.util.Date;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.example.javaeeproject.applicationscoped.CustomerContactApplicationScoped;
import com.example.javaeeproject.entities.Customer;
import com.example.javaeeproject.entities.CustomerContact;
import com.example.javaeeproject.mbeans.CustomerContactManagedBean;

@RequestScoped
@Named("addCustomerContact")
public class AddCustomerContactRequestScoped {

	@ManagedProperty(value = "#{customerContactManagedBean}")
	CustomerContactManagedBean customerContactManagedBean;
	
	private boolean showForm = true;
	
	private String customerContactName;
	private String customerContactPhoneNumber;
	private String customerContactGender;
	private Date customerContactDob;
	private double customerContactWorkYears;

	private int customerId;
	private Customer customer;
	
	CustomerContactApplicationScoped customerContactApplicationScoped;
	
	public AddCustomerContactRequestScoped() {
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		
		customerContactApplicationScoped = (CustomerContactApplicationScoped) FacesContext.getCurrentInstance()
																			.getApplication()
																			.getELResolver()
																			.getValue(context, null, "customerContactApplicationScoped");
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		customerContactManagedBean = (CustomerContactManagedBean) FacesContext.getCurrentInstance()
																		      .getApplication()
																		      .getELResolver()
																		      .getValue(elContext, null, "customerContactManagedBean");
		
//		CustomerContact customerContact 
	}
	
	public boolean isShowForm () {
		return showForm;
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
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	public void addCustomerContact (String customerContactName, String customerContactPhoneNumber, String customerContactGender, Date customerContactDob,
			double customerContactWorkYears, int customerId) {
		try {			
			CustomerContact customerContact = new CustomerContact();
			customerContact.setCustomerContactName(customerContactName);
			customerContact.setCustomerContactPhoneNumber(customerContactPhoneNumber);
			customerContact.setCustomerContactGender(customerContactGender);
			customerContact.setCustomerContactDob(customerContactDob);
			customerContact.setCustomerContactWorkYears(customerContactWorkYears);
			
			Customer customer = new Customer();
			customer.setCustomerId(customerId);
			customerContact.setCustomer(customer);
			
			customerContactManagedBean.addCustomerContact(customerContact);
			customerContactApplicationScoped.findAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer Contact has been added successfully"));
		} catch (Exception ex) {
			
		}
		showForm = true;
	}
	
}
