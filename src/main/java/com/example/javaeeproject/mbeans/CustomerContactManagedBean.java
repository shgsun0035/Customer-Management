package com.example.javaeeproject.mbeans;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.javaeeproject.entities.Customer;
import com.example.javaeeproject.entities.CustomerContact;
import com.example.javaeeproject.interfaceRepository.InterfaceRepository;

@ManagedBean(name = "customerContactManagedBean")
@SessionScoped
public class CustomerContactManagedBean implements Serializable {
	
	@EJB
	InterfaceRepository interfaceRepository;
	
	public CustomerContactManagedBean () {
		
	}
	
	public CustomerContact searchCustomerContactById (int id) {
		try {
			return interfaceRepository.searchCustomerContactById(id);
		} catch (Exception ex) {
			Logger.getLogger(CustomerContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return null;
	}

	public List<CustomerContact> getAllCustomerContacts () {
		try {
			List<CustomerContact> customerContacts = interfaceRepository.getAllCustomerContacts();
			return customerContacts;
		} catch (Exception ex) {
			Logger.getLogger(CustomerContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return null;
	}
	
	public List<Customer> getAllCustomers () {
		try {
			List<Customer> customers = interfaceRepository.getAllCustomers();
			return customers;
		} catch (Exception ex) {
			Logger.getLogger(CustomerContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return null;
	}
	
	public void addCustomerContact (CustomerContact customerContact) {
		try {
			interfaceRepository.addCustomerContact(customerContact);
		} catch (Exception ex) {
			Logger.getLogger(CustomerContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	
	public void editCustomerContact (CustomerContact customerContact) {
		try {
			int customerId = customerContact.getCustomer().getCustomerId();
			Customer customer = new Customer();
			customer.setCustomerId(customerId);
			customerContact.setCustomer(customer);
			
			interfaceRepository.editCustomerContact(customerContact);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer Contact has been updated successfully"));
		} catch (Exception ex) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE,null,ex);
		}
	}
	
	public void removeCustomerContact (int customerContactId) {
		try {
			interfaceRepository.removeCustomerContact(customerContactId);
		} catch (Exception ex) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE,null,ex);
		}
	}
	
}
