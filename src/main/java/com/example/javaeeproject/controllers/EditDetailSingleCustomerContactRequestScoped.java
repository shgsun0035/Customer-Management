package com.example.javaeeproject.controllers;

import java.util.List;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.example.javaeeproject.applicationscoped.CustomerContactApplicationScoped;
import com.example.javaeeproject.entities.CustomerContact;

@Named (value = "editDetailSingleCustomerContact")
@RequestScoped
public class EditDetailSingleCustomerContactRequestScoped {

	private int frontEndCustomerContactId;
	
	private CustomerContact customerContact;
	
	public EditDetailSingleCustomerContactRequestScoped () {
		
		frontEndCustomerContactId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("frontEndCustomerContactId"));
		
		customerContact = getCustomerContact();
	}

	public int getFrontEndCustomerContactId() {
		return frontEndCustomerContactId;
	}

	public void setFrontEndCustomerContactId(int frontEndCustomerContactId) {
		this.frontEndCustomerContactId = frontEndCustomerContactId;
	}

	public CustomerContact getCustomerContact() {
		if (customerContact == null) {
			ELContext context = FacesContext.getCurrentInstance().getELContext();
			CustomerContactApplicationScoped customerContactApplicationScoped = (CustomerContactApplicationScoped) FacesContext.getCurrentInstance()
																														.getApplication()
																														.getELResolver()
																														.getValue(context, null, "customerContactApplicationScoped");
			int selectCustomerContactId = --frontEndCustomerContactId;
			List<CustomerContact> customerContacts = customerContactApplicationScoped.getCustomerContacts();
			for (CustomerContact customerContact : customerContacts) {
				if (customerContact.getCustomerContactId() == selectCustomerContactId) {
					return customerContact;
				}
			}
		}
		
		return customerContact;
	}
	
}
