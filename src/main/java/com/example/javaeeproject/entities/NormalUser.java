package com.example.javaeeproject.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@Entity
@DiscriminatorValue(value = "N")
@NamedQuery(name = NormalUser.GET_ALL_QUERY_NAME, query = "SELECT nu FROM NormalUser nu order by nu.clientId desc")
public class NormalUser extends Client implements Serializable {
		
	public static final String GET_ALL_QUERY_NAME = "NormalUser.getAll";
	
	private String normalUserWorkNumber;
	
	@OneToMany (mappedBy = "normalUser")
	private Set<Customer> customers;

	public NormalUser () {
		
	}
	
	public NormalUser(int clientId, String clientEmail, String clientPassword, String clientName, String clientRole, String normalUserWorkNumber) {
		super(clientId, clientEmail, clientPassword, clientName, clientRole);
		this.normalUserWorkNumber = normalUserWorkNumber;
	}

	public String getNormalUserWorkNumber() {
		return normalUserWorkNumber;
	}

	public void setNormalUserWorkNumber(String normalUserWorkNumber) {
		this.normalUserWorkNumber = normalUserWorkNumber;
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "NormalUser WorkNumber: " + normalUserWorkNumber + " | NormalUser Email: " + getClientEmail()
				+ " | NormalUser Name: " + getClientName() + " | NormalUser Role: " + getClientRole();
	}

	
}
