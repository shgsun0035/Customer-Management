package com.example.javaeeproject.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.example.javaeeproject.entities.Customer;
import com.example.javaeeproject.entities.CustomerContact;
import com.example.javaeeproject.entities.NormalUser;
import com.example.javaeeproject.interfaceRepository.InterfaceRepository;

@ManagedBean(name = "currentNormalUserManagedBean")
@SessionScoped
public class CurrentNormalUserManagedBean implements Serializable{

	@EJB
	InterfaceRepository interfaceRepository;
	
	private NormalUser currentNormalUser;
	
	public CurrentNormalUserManagedBean () {

	}
	
	public NormalUser getCurrentNormalUser() {
		 currentNormalUser();
		return currentNormalUser;
	}

	public void setCurrentNormalUser(NormalUser currentNormalUser) {
		this.currentNormalUser = currentNormalUser;
	}
	
	// ----------------  current normal user ---------------------
	public void currentNormalUser(){
		try {
			List<NormalUser> allNormalUsers = new ArrayList<>();
			allNormalUsers = interfaceRepository.getAllNormalUsers();
				
			// Get the normal user login email
			String currentUserEmail = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
			
			for (NormalUser normalUser : allNormalUsers) {
				if (normalUser.getClientEmail().equals(currentUserEmail)) {
					setCurrentNormalUser(normalUser);
				}
			}
		} catch (Exception ex) {
			Logger.getLogger(TypeOfIndustryManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	// ----------------  current normal user customer ---------------------	
	public List<Customer> getAllCurrentNormalUserCustomerList() {
		try {
			// Get the normal user login email
			String currentNormalUserEmail = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName(); 
			
			List<Customer> allCustomers = interfaceRepository.getAllCustomers();
			List<Customer> currentCustomers = new ArrayList<>();
			
			for (Customer customer : allCustomers) {
				if (customer.getNormalUser().getClientEmail().equals(currentNormalUserEmail)) {
					currentCustomers.add(customer);
				}
			}
			
			return currentCustomers;
			
		} catch (Exception ex) {
			Logger.getLogger(CurrentNormalUserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return null;
	}
	
	public List<Customer> searchCustomerForCurrentNormalUserByTypeOfIndustryAndCustomerName (int typeOfIndustryId, String customerName) {
		try {
			// Get the normal user login email
			String currentNormalUserEmail = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
			
			List<Customer> currentSearchedCustomers = interfaceRepository.searchCustomerByTypeOfIndustryAndCustomerName(typeOfIndustryId, customerName);			
			List<Customer> searchedCustomers = new ArrayList<>();
			
			for (Customer customer : currentSearchedCustomers) {
				if (customer.getNormalUser().getClientEmail().equals(currentNormalUserEmail)) {
					searchedCustomers.add(customer);
				}
			}
			
			return searchedCustomers;
			
		} catch (Exception ex) {
			Logger.getLogger(CurrentNormalUserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
		
	//  ---------------- current normal user customer contact ----------------
	public List<CustomerContact> getAllCurrentNormalUserCustomerContactList () {
		try {	
			// Get the normal user login email
			String currentNormalUserEmail = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName(); 
			
			List<CustomerContact> allCustomerContacts = interfaceRepository.getAllCustomerContacts();
			List<CustomerContact> currentCustomerContacts = new ArrayList<>();
			
			for (CustomerContact customerContact : allCustomerContacts) {
				if (customerContact.getCustomer().getNormalUser().getClientEmail().equals(currentNormalUserEmail)) {
						currentCustomerContacts.add(customerContact);
					}
				}

			return currentCustomerContacts;
			

		} catch (Exception ex) {
			Logger.getLogger(CurrentNormalUserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return null;
	}
	

}
