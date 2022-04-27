package com.example.javaeeproject.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.example.javaeeproject.applicationscoped.CustomerApplicationScoped;
import com.example.javaeeproject.entities.Customer;

@Named ("searchCustomer")
@RequestScoped
public class SearchCustomerRequestScoped {
	
	private Customer customer;
	
	CustomerApplicationScoped customerApplicationScoped;
	
	private int typeOfIndustryId;
	
	private String customerName;
	
	public SearchCustomerRequestScoped () {
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		
		customerApplicationScoped = (CustomerApplicationScoped) FacesContext.getCurrentInstance()
																			.getApplication()
																			.getELResolver()
																			.getValue(context, null, "customerApplicationScoped");
		
		customerApplicationScoped.updateCustomerList();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomerApplicationScoped getCustomerApplicationScoped() {
		return customerApplicationScoped;
	}

	public void setCustomerApplicationScoped(CustomerApplicationScoped customerApplicationScoped) {
		this.customerApplicationScoped = customerApplicationScoped;
	}
	
	public int getTypeOfIndustryId() {
		return typeOfIndustryId;
	}

	public void setTypeOfIndustryId(int typeOfIndustryId) {
		this.typeOfIndustryId = typeOfIndustryId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void searchCustomerByTypeOfIndustryAndCustomerName (int typeOfIndustryId, String customerName) {
		
		try {
			int a = typeOfIndustryId;
			String b = customerName;
			String c = "";
			customerApplicationScoped.searchCustomerByTypeOfIndustryAndCustomerName(typeOfIndustryId, customerName);
		} catch (Exception e) {
			
		}
	}
	
}
