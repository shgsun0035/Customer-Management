package com.example.javaeeproject.applicationscoped;

import java.util.ArrayList;

import javax.el.ELContext;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.example.javaeeproject.entities.CustomerContact;
import com.example.javaeeproject.mbeans.CustomerContactManagedBean;

@Named (value = "customerContactApplicationScoped")
@ApplicationScoped
public class CustomerContactApplicationScoped {

	@ManagedProperty(value = "#{customerContactManagedBean}")
	CustomerContactManagedBean customerContactManagedBean;
	
	private ArrayList<CustomerContact> customerContacts;
	
	private boolean showForm = true;
	
	public boolean isShowForm () {
		return showForm;
	}
	
	public CustomerContactApplicationScoped() throws Exception {
		customerContacts = new ArrayList<>();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		customerContactManagedBean = (CustomerContactManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "customerContactManagedBean");
		
		updateCustomerContactList();
	}

	public ArrayList<CustomerContact> getCustomerContacts() {
		return customerContacts;
	}

	public void setCustomerContacts(ArrayList<CustomerContact> customerContacts) {
		this.customerContacts = customerContacts;
	}
	
	public void updateCustomerContactList() {
		if (customerContacts != null && customerContacts.size() > 0) {
			
		} else {
			customerContacts.clear();
			
			for (CustomerContact customerContact : customerContactManagedBean.getAllCustomerContacts()) {
				customerContacts.add(customerContact);
			}
		}
	}
	
	public void findAll () {
		customerContacts.clear();
		
		for (CustomerContact customerContact : customerContactManagedBean.getAllCustomerContacts()) {
			customerContacts.add(customerContact);
		}
	}
}
