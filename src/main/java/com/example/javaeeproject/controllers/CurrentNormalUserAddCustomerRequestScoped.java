package com.example.javaeeproject.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.example.javaeeproject.applicationscoped.CurrentNormalUserApplicationScoped;
import com.example.javaeeproject.localentities.LocalCustomer;
import com.example.javaeeproject.mbeans.CustomerManagedBean;

@RequestScoped
@Named("currentNormalUserAddCustomer")
public class CurrentNormalUserAddCustomerRequestScoped {
	@ManagedProperty(value = "#{currentNormalUserManagedBean}")
	CustomerManagedBean customerManagedBean;
	
	private boolean showForm = true;
	
	private LocalCustomer localCustomer;
	
	CurrentNormalUserApplicationScoped currentNormalUserApplicationScoped;
	
	public CurrentNormalUserAddCustomerRequestScoped () {
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		
		currentNormalUserApplicationScoped = (CurrentNormalUserApplicationScoped) FacesContext.getCurrentInstance()
																			.getApplication()
																			.getELResolver()
																			.getValue(context, null, "currentNormalUserApplicationScoped");
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance()
															  .getApplication()
															  .getELResolver()
															  .getValue(elContext, null, "customerManagedBean");
	}
	
	public boolean isShowForm () {
		return showForm;
	}
	
	
	public LocalCustomer getLocalCustomer() {
		return localCustomer;
	}

	public void setLocalCustomer(LocalCustomer localCustomer) {
		this.localCustomer = localCustomer;
	}

	public void addCustomer (LocalCustomer localCustomer) {
		try {
			customerManagedBean.addCustomer(localCustomer);
			currentNormalUserApplicationScoped.findAllCustomerList();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer has been added successfully"));
		} catch (Exception e) {
			
		}
		showForm = true;
	}

	
}
