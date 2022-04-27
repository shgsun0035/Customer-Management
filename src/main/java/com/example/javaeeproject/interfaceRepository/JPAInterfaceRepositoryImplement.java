package com.example.javaeeproject.interfaceRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.example.javaeeproject.entities.*;

@Stateless
public class JPAInterfaceRepositoryImplement implements InterfaceRepository {

	@PersistenceContext(unitName = "javaee-ejbPU")
	private EntityManager entityManager;

	
	// Customer Entity Interface Implementation
	@Override
	public Customer searchCustomerById(int id) throws Exception {
		Customer specificCustomer = entityManager.find(Customer.class, id);
		return specificCustomer;
	}

	@Override
	public List<Customer> getAllCustomers() throws Exception {
		return entityManager.createNamedQuery(Customer.GET_ALL_QUERY_NAME).getResultList();
	}

	@Override
	public void addCustomer(Customer customer) throws Exception {
		List<Customer> customers = entityManager.createNamedQuery(Customer.GET_ALL_QUERY_NAME).getResultList();
		customer.setCustomerId(customers.get(0).getCustomerId() + 1);
		entityManager.persist(customer);
	}

	@Override
	public void editCustomer(Customer customer) throws Exception {
		try {
			entityManager.merge(customer);
		} catch (Exception ex) {

		}
	}

	@Override
	public void removeCustomer(int customerId) throws Exception {
		Customer customer = this.searchCustomerById(customerId);
		if (customer != null) {
			entityManager.remove(customer);
		}
	}

	@Override
	public List<Customer> searchCustomerByTypeOfIndustryAndCustomerName(int typeOfIndustryId, String customerName) throws Exception {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
		Root<Customer> p = query.from(Customer.class);
		
		List<Customer> customers = new ArrayList<>();
		
		if (typeOfIndustryId != 0 && !customerName.equals("")) {
			
			query.select(p).where(builder.and(builder.equal(p.get("typeOfIndustry").get("typeOfIndustryId"), typeOfIndustryId), builder.equal(p.get("customerName"), customerName)));

			customers = entityManager.createQuery(query).getResultList();

			return customers;
		} else if (typeOfIndustryId != 0 && customerName.equals("")) {

			query.select(p).where(builder.equal(p.get("typeOfIndustry").get("typeOfIndustryId"), typeOfIndustryId));
			
			customers = entityManager.createQuery(query).getResultList();

			return customers;
		} else if (typeOfIndustryId == 0 && !customerName.equals("")) {

			query.select(p).where(builder.equal(p.get("customerName"), customerName));
			
			customers = entityManager.createQuery(query).getResultList();
			
			return customers;
		} else {

			query.select(p);

			customers = entityManager.createQuery(query).getResultList();
			
			return customers;
		}

	}

	@Override
	public Customer searchCustomerByCustomerContact(CustomerContact customerContact) throws Exception {
		customerContact = entityManager.find(CustomerContact.class, customerContact.getCustomerContactId());
		customerContact.getCustomer();
		entityManager.refresh(customerContact);
		return customerContact.getCustomer();

	}
	
	// CustomerContact Entity Interface
	@Override
	public CustomerContact searchCustomerContactById (int id) throws Exception {
		CustomerContact specificCustomerContact = entityManager.find(CustomerContact.class, id);
		return specificCustomerContact;
	}
	
	@Override
	public List<CustomerContact> getAllCustomerContacts () throws Exception {
		return entityManager.createNamedQuery(CustomerContact.GET_ALL_QUERY_NAME).getResultList();
	}
	
	@Override
	public void addCustomerContact (CustomerContact customerContact) throws Exception {
		List<CustomerContact> customerContacts = entityManager.createNamedQuery(CustomerContact.GET_ALL_QUERY_NAME).getResultList();
		customerContact.setCustomerContactId(customerContacts.get(0).getCustomerContactId() + 1);
		entityManager.persist(customerContact);
	}
	
	@Override
	public void editCustomerContact (CustomerContact customerContact) throws Exception {
		try {
			entityManager.merge(customerContact);
		} catch (Exception ex) {
			
		}
	}
	
	@Override
	public void removeCustomerContact (int customerContactId) throws Exception {
		CustomerContact customerContact = this.searchCustomerContactById(customerContactId);
		if (customerContact != null) {
			entityManager.remove(customerContact);
		}
	}
	
	// NormalUser Entity Interface
	@Override
	public NormalUser searchNormalUserById (int id) throws Exception {
		NormalUser specificNormalUser = entityManager.find(NormalUser.class, id);
		return specificNormalUser;
	}
	
	@Override
	public List<NormalUser> getAllNormalUsers () throws Exception {
		List<NormalUser> normalUsers = new ArrayList<>();
		normalUsers = entityManager.createNamedQuery(NormalUser.GET_ALL_QUERY_NAME).getResultList();
		return normalUsers;
	}
	
	@Override
	public void addNormalUser (Client normalUserClient) throws Exception {
		List<Client> clients = entityManager.createNamedQuery(Client.GET_ALL_QUERY_NAME).getResultList();
		normalUserClient.setClientId(clients.get(0).getClientId() + 1);
		
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(normalUserClient.getClientPassword().getBytes(StandardCharsets.UTF_8));
		String hashPassword = bytesToHex(hash);

		normalUserClient.setClientPassword(hashPassword);
		
		entityManager.persist(normalUserClient);
	}
	
	@Override
	public void editNormalUser (NormalUser normalUser) throws Exception {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(normalUser.getClientPassword().getBytes(StandardCharsets.UTF_8));
			String hashPassword = bytesToHex(hash);

			normalUser.setClientPassword(hashPassword);
			
			entityManager.merge(normalUser);
		} catch (Exception ex) {
			
		}
	}
	
	@Override
	public void removeNormalUser (int clientId) throws Exception {
		NormalUser normalUser = this.searchNormalUserById(clientId);
		if (normalUser != null) {
			entityManager.remove(normalUser);
		}
	}
	
	// Admin Entity Interface
	@Override
	public Admin searchAdminById (int id) throws Exception {
		Admin specificAdmin = entityManager.find(Admin.class, id);
		return specificAdmin;
	}
	
	@Override
	public List<Admin> getAllAdmins () throws Exception {
		List<Admin> admins = new ArrayList<>();
		admins = entityManager.createNamedQuery(Admin.GET_ALL_QUERY_NAME).getResultList();
		return admins;
	}
	
	@Override
	public void addAdmin (Client adminClient) throws Exception {
		List<Client> clients = entityManager.createNamedQuery(Client.GET_ALL_QUERY_NAME).getResultList();
		adminClient.setClientId(clients.get(0).getClientId() + 1);
		
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(adminClient.getClientPassword().getBytes(StandardCharsets.UTF_8));
		String hashPassword = bytesToHex(hash);
		
		adminClient.setClientPassword(hashPassword);
		
		entityManager.persist(adminClient);
	}
	
	@Override
	public void editAdmin (Admin admin) throws Exception {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(admin.getClientPassword().getBytes(StandardCharsets.UTF_8));
			String hashPassword = bytesToHex(hash);
			
			admin.setClientPassword(hashPassword);
			
			entityManager.merge(admin);
		} catch (Exception ex) {
			
		}
	}
	
	@Override
	public void removeAdmin (int clientId) throws Exception {
		Admin admin = this.searchAdminById(clientId);
		if (admin != null) {
			entityManager.remove(admin);
		}
	}
	
	// TypeOfIndustry Entity Interface
	@Override
	public TypeOfIndustry searchTypeOfIndustryById (int id) throws Exception {
		TypeOfIndustry specificTypeOfIndustry = entityManager.find(TypeOfIndustry.class, id);
		return specificTypeOfIndustry;
	}
	
	@Override
	public List<TypeOfIndustry> getAllTypeOfIndustries () throws Exception {
		List<TypeOfIndustry> typeOfIndutries = new ArrayList<>();
		typeOfIndutries = entityManager.createNamedQuery(TypeOfIndustry.GET_ALL_QUERY_NAME).getResultList();
		return typeOfIndutries;
	}
	
	@Override
	public void addTypeOfIndustry (TypeOfIndustry typeOfIndustry) throws Exception {
		List<TypeOfIndustry> typeOfIndutries = entityManager.createNamedQuery(TypeOfIndustry.GET_ALL_QUERY_NAME).getResultList();
		typeOfIndustry.setTypeOfIndustryId(typeOfIndutries.get(0).getTypeOfIndustryId() + 1);
		entityManager.persist(typeOfIndustry);
	}
	
	@Override
	public void editTypeOfIndustry (TypeOfIndustry typeOfIndustry) throws Exception {
		try {
			entityManager.merge(typeOfIndustry);
		} catch (Exception ex) {
			
		}
	}
	
	@Override
	public void removeTypeOfIndustry (int typeOfIndustryId) throws Exception {
		TypeOfIndustry typeOfIndustry = this.searchTypeOfIndustryById(typeOfIndustryId);
		if (typeOfIndustry != null) {
			entityManager.remove(typeOfIndustry);
		}
	}
	
	// SHA-256 Hash Function
	private String bytesToHex(byte[] hash) {
	    StringBuilder hexString = new StringBuilder(2 * hash.length);
	    for (int i = 0; i < hash.length; i++) {
	        String hex = Integer.toHexString(0xff & hash[i]);
	        if(hex.length() == 1) {
	            hexString.append('0');
	        }
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
}
