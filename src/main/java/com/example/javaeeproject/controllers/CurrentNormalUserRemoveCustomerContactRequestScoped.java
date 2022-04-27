package com.example.javaeeproject.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.example.javaeeproject.applicationscoped.CurrentNormalUserApplicationScoped;
import com.example.javaeeproject.entities.CustomerContact;
import com.example.javaeeproject.mbeans.CustomerContactManagedBean;

@Named("currentNormalUserRemoveCustomerContact")
@RequestScoped
public class CurrentNormalUserRemoveCustomerContactRequestScoped {
	@ManagedProperty(value = "#{customerContactManagedBean}")
	CustomerContactManagedBean customerContactManagedBean;
	
	private boolean showForm = true;
	private CustomerContact customerContact;
	CurrentNormalUserApplicationScoped currentNormalUserApplicationScoped;
	
	public CurrentNormalUserRemoveCustomerContactRequestScoped () {
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		currentNormalUserApplicationScoped = (CurrentNormalUserApplicationScoped) FacesContext.getCurrentInstance()
																							  .getApplication()
																							  .getELResolver()
																							  .getValue(context, null, "currentNormalUserApplicationScoped");
		
		currentNormalUserApplicationScoped.updateCustomerContactList();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		customerContactManagedBean = (CustomerContactManagedBean) FacesContext.getCurrentInstance()
															    .getApplication()
															    .getELResolver()
															    .getValue(context, null, "customerContactManagedBean");
	}

	public CustomerContact getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(CustomerContact customerContact) {
		this.customerContact = customerContact;
	}
	
	public boolean isShowForm () {
		return showForm;
	}
	
	public void removeCustomerContact(int customerContactId) {
		try {
		    int a = customerContactId;
			customerContactManagedBean.removeCustomerContact(customerContactId);
			currentNormalUserApplicationScoped.findAllCustomerContactList();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage ("Customer Contact has been deleted successfully"));
		} catch (Exception ex) {
			
		}
		
		showForm = true;
	}

}
