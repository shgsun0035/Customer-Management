package com.example.javaeeproject.controllers;

import java.util.List;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.example.javaeeproject.applicationscoped.CurrentNormalUserApplicationScoped;
import com.example.javaeeproject.entities.Customer;

@Named(value = "currentNormalUserEditDetailSingleCustomer")
@RequestScoped
public class CurrentNormalUserEditDetailSingleCustomerRequestScoped {
	
	private int frontEndCustomerId;
	
	private Customer customer;
	
	public CurrentNormalUserEditDetailSingleCustomerRequestScoped () {
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
			
			CurrentNormalUserApplicationScoped currentNormalUserApplicationScoped = (CurrentNormalUserApplicationScoped) FacesContext.getCurrentInstance()
																										  							 .getApplication()
																										  							 .getELResolver()
																										  							 .getValue(context, null, "currentNormalUserApplicationScoped");
			int selectCustomerId = --frontEndCustomerId;
			List<Customer> customers = currentNormalUserApplicationScoped.getCustomers();
			for (Customer customer : customers) {
				if (customer.getCustomerId() == selectCustomerId) {
					return customer;
				}
			}
		}
		
		return customer;
	}
}
