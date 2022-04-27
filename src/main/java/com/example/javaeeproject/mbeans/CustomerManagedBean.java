package com.example.javaeeproject.mbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.javaeeproject.entities.*;
import com.example.javaeeproject.interfaceRepository.InterfaceRepository;
import com.example.javaeeproject.localentities.LocalCustomer;

@ManagedBean(name = "customerManagedBean")
@SessionScoped
public class CustomerManagedBean implements Serializable {
	
	@EJB
	InterfaceRepository interfaceRepository;
	
	public CustomerManagedBean () {
		
	}
	
	public Customer searchCustomerById (int id) {
		try {
			return interfaceRepository.searchCustomerById(id);
		} catch (Exception ex) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return null;
	}
	
	public List<Customer> getAllCustomers () {
		try {
			List<Customer> customers = interfaceRepository.getAllCustomers();
			return customers;
		} catch (Exception e) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE,null,e);
		}
		
		return null;
	}
	
	public List<CustomerContact> getAllCustomerContacts () {
		try {
			return interfaceRepository.getAllCustomerContacts();
		} catch (Exception e) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE,null,e);
		}
		
		return null;
	}
	
	public List<NormalUser> getAllNormalUsers () {
		try {
			return interfaceRepository.getAllNormalUsers();
		} catch (Exception e) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE,null,e);
		}
		
		return null;
	}
	
	public List<TypeOfIndustry> getAllTypeOfIndustry () {
		try {
			return interfaceRepository.getAllTypeOfIndustries();
		} catch (Exception e) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE,null,e);
		}
		
		return null;
	}
	
	public void addCustomer (LocalCustomer localCustomer) {
		
		Customer convertedCustomer = convertPropertyToEntity(localCustomer);
		
		try {
			interfaceRepository.addCustomer(convertedCustomer);	
		} catch (Exception e) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE,null,e);
		}
	}
	
	public void editCustomer (Customer customer) {
		try {
			String streetNumber = customer.getAddress().getStreetNumber();
			String streetAddress = customer.getAddress().getStreetAddress();
			String suburb = customer.getAddress().getSuburb();
			String state = customer.getAddress().getState();
			String postcode = customer.getAddress().getPostcode();
			Address address = new Address(streetNumber, streetAddress, suburb, state, postcode);
			customer.setAddress(address);
			
			int clientId = customer.getNormalUser().getClientId();
			NormalUser normalUser = new NormalUser();
			normalUser.setClientId(clientId);
			customer.setNormalUser(normalUser);	
			
			int typeOfIndustryId = customer.getTypeOfIndustry().getTypeOfIndustryId();
			TypeOfIndustry typeOfIndustry = new TypeOfIndustry();
			typeOfIndustry.setTypeOfIndustryId(typeOfIndustryId);
			customer.setTypeOfIndustry(typeOfIndustry);
			
			interfaceRepository.editCustomer(customer);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer has been updated successfully"));
			
		} catch (Exception e) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE,null,e);
		}
	}
	
	public void removeCustomer (int customerId) {
		try {
			interfaceRepository.removeCustomer(customerId);
		} catch (Exception e) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE,null,e);
		}
	}
	
	public List<Customer> searchCustomerByTypeOfIndustryAndCustomerName (int typeOfIndustryId, String customerName) {
		try {
			return interfaceRepository.searchCustomerByTypeOfIndustryAndCustomerName(typeOfIndustryId, customerName);
		} catch (Exception e) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE,null,e);
		}
		
		return null;
	}
	
	public Customer searchCustomerByCustomerContact (CustomerContact customerContact) {
		try {
			return interfaceRepository.searchCustomerByCustomerContact(customerContact);
		} catch (Exception e) {
			Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE,null,e);
		}
		
		return null;
	}
	
	//=================================================================================================================
	private Customer convertPropertyToEntity (LocalCustomer localCustomer) {
		Customer customer = new Customer(); //entity
		String streetNumber = localCustomer.getStreetNumber();
		String streetAddress = localCustomer.getStreetAddress();
		String suburb = localCustomer.getSuburb();
		String postcode = localCustomer.getPostcode();
		String state = localCustomer.getState();
		Address address = new Address (streetNumber, streetAddress, suburb, state, postcode);
		
		customer.setAddress(address);
		customer.setCustomerName(localCustomer.getCustomerName());
		customer.setCustomerPhoneNumber(localCustomer.getCustomerPhoneNumber());
		customer.setCustomerEmail(localCustomer.getCustomerEmail());
		customer.setCustomerWebsite(localCustomer.getCustomerWebsite());
		customer.setCustomerDescription(localCustomer.getCustomerDescription());
		
		int clientId = localCustomer.getClientId();
		String clientEmail = localCustomer.getClientEmail();
		String clientPassword = localCustomer.getClientPassword();
		String clientName = localCustomer.getClientName();
	    String clientRole = localCustomer.getClientRole();
	    char user_type = localCustomer.getUser_type();
	    String normalUserPhoneNumber = localCustomer.getNormalUserPhoneNumber();
	    NormalUser normalUser = new NormalUser(clientId, clientEmail, clientPassword, clientName, clientRole, normalUserPhoneNumber);
	    
	    int typeOfIndustryId = localCustomer.getTypeOfIndustryId();
	    String typeOfIndustryName = localCustomer.getTypeOfIndustryName();
	    TypeOfIndustry typeOfIndustry = new TypeOfIndustry(typeOfIndustryId, typeOfIndustryName);
	    
	    if (normalUser.getClientId() == 0) {
	    	normalUser = null;
	    }
	    
	    customer.setNormalUser(normalUser);
	    customer.setTypeOfIndustry(typeOfIndustry);
	    
		/* customer.setCustomerContacts(customerContacts); */
	    
	    return customer;
	    
	}
}

