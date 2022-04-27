package com.example.javaeeproject.applicationscoped;

import javax.inject.Named;

import com.example.javaeeproject.entities.Customer;
import com.example.javaeeproject.entities.CustomerContact;
import com.example.javaeeproject.mbeans.CurrentNormalUserManagedBean;

import java.util.ArrayList;

import javax.el.ELContext;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

@Named(value = "currentNormalUserApplicationScoped")
@ApplicationScoped

public class CurrentNormalUserApplicationScoped {
	@ManagedProperty(value="#{currentNormalUserManagedBean}")
	CurrentNormalUserManagedBean currentNormalUserManagedBean;

	private ArrayList<Customer> customers;
	private ArrayList<CustomerContact> customerContacts;
	
	private boolean showForm = true;
	
	public boolean isShowForm() {
		return showForm;
	}
	
	public CurrentNormalUserApplicationScoped () throws Exception {
		customers = new ArrayList<>();
		customerContacts = new ArrayList<>();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		currentNormalUserManagedBean = (CurrentNormalUserManagedBean)FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "currentNormalUserManagedBean");
		
		updateCustomerList();
		updateCustomerContactList();
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}
	
	public ArrayList<CustomerContact> getCustomerContacts() {
		return customerContacts;
	}

	public void setCustomerContacts(ArrayList<CustomerContact> customerContacts) {
		this.customerContacts = customerContacts;
	}

	// --------------- Current Normal User Customer --------------------
	public void updateCustomerList() {
		if (customers != null && customers.size() > 0) {
			
		} else {
			customers.clear();
			
			for (Customer customer : currentNormalUserManagedBean.getAllCurrentNormalUserCustomerList()) {
				customers.add(customer);
			}
		}
	}
	
	public void findAllCustomerList () {
		customers.clear();
		
		for (Customer customer : currentNormalUserManagedBean.getAllCurrentNormalUserCustomerList()) {
			customers.add(customer);
		}
		
		setCustomers(customers);
	}
	
	public void searchCustomerByTypeOfIndustryAndCustomerName (int typeOfIndustryId, String customerName) {
		customers.clear();
		
		for (Customer customer : currentNormalUserManagedBean.searchCustomerForCurrentNormalUserByTypeOfIndustryAndCustomerName(typeOfIndustryId, customerName)) {
			customers.add(customer);
		}
		
		setCustomers(customers);
		
	}
	
	// --------------- Current Normal User Customer Contact --------------------
	public void updateCustomerContactList() {
		if (customerContacts != null && customerContacts.size() > 0) {
			
		} else {
			customerContacts.clear();
			
			for (CustomerContact customerContact : currentNormalUserManagedBean.getAllCurrentNormalUserCustomerContactList()) {
				customerContacts.add(customerContact);
			}
		}
	}
	
	public void findAllCustomerContactList () {
		customerContacts.clear();
		
		for (CustomerContact customerContact : currentNormalUserManagedBean.getAllCurrentNormalUserCustomerContactList()) {
			customerContacts.add(customerContact);
		}
		
		setCustomerContacts(customerContacts);
	}
	
}
