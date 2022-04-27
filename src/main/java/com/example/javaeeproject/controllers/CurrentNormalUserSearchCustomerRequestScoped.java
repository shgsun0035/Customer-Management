package com.example.javaeeproject.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.example.javaeeproject.applicationscoped.CurrentNormalUserApplicationScoped;
import com.example.javaeeproject.entities.Customer;

@Named ("currentNormalUserSearchCustomer")
@RequestScoped
public class CurrentNormalUserSearchCustomerRequestScoped {
	
	private Customer customer;
	
	CurrentNormalUserApplicationScoped currentNormalUserApplicationScoped;
	
	private int typeOfIndustryId;
	
	private String customerName;
	
	public CurrentNormalUserSearchCustomerRequestScoped () {
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		
		currentNormalUserApplicationScoped = (CurrentNormalUserApplicationScoped) FacesContext.getCurrentInstance()
																							  .getApplication()
																							  .getELResolver()
																							  .getValue(context, null, "currentNormalUserApplicationScoped");
		
		currentNormalUserApplicationScoped.updateCustomerList();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
			currentNormalUserApplicationScoped.searchCustomerByTypeOfIndustryAndCustomerName(typeOfIndustryId, customerName);
		} catch (Exception e) {
			
		}
	}
	
}
