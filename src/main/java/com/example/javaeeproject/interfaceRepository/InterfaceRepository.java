package com.example.javaeeproject.interfaceRepository;

import java.util.List;

import com.example.javaeeproject.entities.*;

public interface InterfaceRepository {
	
	// Customer Entity Interface
	public Customer searchCustomerById(int id) throws Exception;
	
	public List<Customer> getAllCustomers() throws Exception;
	
	public void addCustomer(Customer customer) throws Exception;
	
	public void editCustomer(Customer customer) throws Exception;
	
	public void removeCustomer(int customerId) throws Exception;
	
	public List<Customer> searchCustomerByTypeOfIndustryAndCustomerName(int typeOfIndustryId, String customerName) throws Exception;
	
	public Customer searchCustomerByCustomerContact(CustomerContact customerContact) throws Exception;
	
	
	// CustomerContact Entity Interface
	public CustomerContact searchCustomerContactById (int id) throws Exception;
	
	public List<CustomerContact> getAllCustomerContacts () throws Exception;
	
	public void addCustomerContact (CustomerContact customerContact) throws Exception;
	
	public void editCustomerContact (CustomerContact customerContact) throws Exception;
	
	public void removeCustomerContact (int customerContactId) throws Exception;
	
	// NormalUser Entity Interface
	public NormalUser searchNormalUserById (int id) throws Exception;
	
	public List<NormalUser> getAllNormalUsers () throws Exception;
	
	public void addNormalUser (Client normalUserClient) throws Exception;
	
	public void editNormalUser (NormalUser normalUser) throws Exception;
	
	public void removeNormalUser (int clientId) throws Exception;
	
	// Admin Entity Interface
	public Admin searchAdminById (int id) throws Exception;
	
	public List<Admin> getAllAdmins () throws Exception;
	
	public void addAdmin (Client adminClient) throws Exception;
	
	public void editAdmin (Admin admin) throws Exception;
	
	public void removeAdmin (int clientId) throws Exception;
	
	// TypeOfIndustry Entity Interface
	public TypeOfIndustry searchTypeOfIndustryById (int id) throws Exception;
	
	public List<TypeOfIndustry> getAllTypeOfIndustries () throws Exception;
	
	public void addTypeOfIndustry (TypeOfIndustry typeOfIndustry) throws Exception;
	
	public void editTypeOfIndustry (TypeOfIndustry typeOfIndustry) throws Exception;
	
	public void removeTypeOfIndustry (int typeOfIndustryId) throws Exception;
}
