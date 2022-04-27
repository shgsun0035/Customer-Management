package com.example.javaeeproject.applicationscoped;

import javax.inject.Named;

import com.example.javaeeproject.entities.Customer;
import com.example.javaeeproject.mbeans.CustomerManagedBean;

import java.util.ArrayList;

import javax.el.ELContext;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

@Named(value = "customerApplicationScoped")
@ApplicationScoped

public class CustomerApplicationScoped {
	@ManagedProperty(value="#{customerManagedBean}")
	CustomerManagedBean customerManagedBean;

	private ArrayList<Customer> customers;
	
	private boolean showForm = true;
	
	public boolean isShowForm() {
		return showForm;
	}
	
	public CustomerApplicationScoped () throws Exception {
		customers = new ArrayList<>();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		customerManagedBean = (CustomerManagedBean)FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "customerManagedBean");
		
		updateCustomerList();
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}
	
	public void updateCustomerList() {
		if (customers != null && customers.size() > 0) {
			
		} else {
			customers.clear();
			
			for (Customer customer : customerManagedBean.getAllCustomers()) {
				customers.add(customer);
			}
		}
	}
	
	public void searchCustomerById (int customerId) {
		customers.clear();
		customers.add(customerManagedBean.searchCustomerById(customerId));
	}
	
	public void searchCustomerByTypeOfIndustryAndCustomerName (int typeOfIndustryId, String customerName) {
		customers.clear();
		
		for (Customer customer : customerManagedBean.searchCustomerByTypeOfIndustryAndCustomerName(typeOfIndustryId, customerName)) {
			customers.add(customer);
		}
		
		setCustomers(customers);
		
	}
	
	public void findAll () {
		customers.clear();
		
		for (Customer customer : customerManagedBean.getAllCustomers()) {
			customers.add(customer);
		}
		
		setCustomers(customers);
	}
	
}
