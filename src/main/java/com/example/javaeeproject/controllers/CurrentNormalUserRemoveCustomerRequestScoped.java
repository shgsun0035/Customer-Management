package com.example.javaeeproject.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.example.javaeeproject.applicationscoped.CurrentNormalUserApplicationScoped;
import com.example.javaeeproject.entities.Customer;
import com.example.javaeeproject.mbeans.CustomerManagedBean;

@Named ("currentNormalUserRemoveCustomer")
@RequestScoped
public class CurrentNormalUserRemoveCustomerRequestScoped {
	@ManagedProperty(value = "#{customerManagedBean}")
	CustomerManagedBean customerManagedBean;
	
	private boolean showForm = true;
	private Customer customer;
	CurrentNormalUserApplicationScoped currentNormalUserApplicationScoped;
	
	public CurrentNormalUserRemoveCustomerRequestScoped () {
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		currentNormalUserApplicationScoped = (CurrentNormalUserApplicationScoped) FacesContext.getCurrentInstance()
																						      .getApplication()
																						      .getELResolver()
																						      .getValue(context, null, "currentNormalUserApplicationScoped");
		currentNormalUserApplicationScoped.updateCustomerList();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance()
															  .getApplication()
															  .getELResolver()
															  .getValue(context, null, "customerManagedBean");
		
	}
	
	public Customer getCustomer () {
		return customer;
	}
	
	public void setCustomer (Customer customer) {
		this.customer = customer;
	}
	
	public boolean isShowForm() {
		return showForm;
	}
	
	public void removeCustomer (int customerId) {
		try {
		
			int a = customerId;
			customerManagedBean.removeCustomer(customerId);
			
			currentNormalUserApplicationScoped.findAllCustomerList();;
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage ("Customer has been deleted successfully"));
		} catch (Exception e) {
			
		}
		
		showForm = true;
	}
	
}
