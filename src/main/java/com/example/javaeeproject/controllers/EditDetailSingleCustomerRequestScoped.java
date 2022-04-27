package com.example.javaeeproject.controllers;

import java.util.List;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.example.javaeeproject.applicationscoped.CustomerApplicationScoped;
import com.example.javaeeproject.entities.Customer;

@Named(value = "editDetailSingleCustomer")
@RequestScoped
public class EditDetailSingleCustomerRequestScoped {
	
	private int frontEndCustomerId;
	
	private Customer customer;
	
	public EditDetailSingleCustomerRequestScoped () {
		frontEndCustomerId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("frontEndCustomerId"));
		
		
		
		customer = getCustomer();
		
	}

	public int getFrontEndCustomerId() {
		return frontEndCustomerId;
	}

	public void setFrontEndCustomerId(int frontEndCustomerId) {
		this.frontEndCustomerId = frontEndCustomerId;
	}
	
	public Customer getCustomer () {
		if (customer == null) {
			ELContext context = FacesContext.getCurrentInstance().getELContext();
			
			CustomerApplicationScoped customerApplicationScoped = (CustomerApplicationScoped) FacesContext.getCurrentInstance()
																										  .getApplication()
																										  .getELResolver()
																										  .getValue(context, null, "customerApplicationScoped");
			int selectCustomerId = --frontEndCustomerId;
			List<Customer> customers = customerApplicationScoped.getCustomers();
			for (Customer customer : customers) {
				if (customer.getCustomerId() == selectCustomerId) {
					return customer;
				}
			}
		}
		
		return customer;
	}
}
