package com.example.javaeeproject.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.example.javaeeproject.applicationscoped.CustomerContactApplicationScoped;
import com.example.javaeeproject.entities.CustomerContact;
import com.example.javaeeproject.mbeans.CustomerContactManagedBean;

@Named("removeCustomerContact")
@RequestScoped
public class RemoveCustomerContactRequestScoped {
	@ManagedProperty(value = "#{customerContactManagedBean}")
	CustomerContactManagedBean customerContactManagedBean;
	
	private boolean showForm = true;
	private CustomerContact customerContact;
	CustomerContactApplicationScoped customerContactApplicationScoped;
	
	public RemoveCustomerContactRequestScoped () {
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		customerContactApplicationScoped = (CustomerContactApplicationScoped) FacesContext.getCurrentInstance()
																						  .getApplication()
																						  .getELResolver()
																						  .getValue(context, null, "customerContactApplicationScoped");
		
		customerContactApplicationScoped.updateCustomerContactList();
		
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
			customerContactApplicationScoped.findAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage ("Customer Contact has been deleted successfully"));
		} catch (Exception ex) {
			
		}
		
		showForm = true;
	}

}
