package com.example.javaeeproject.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE", discriminatorType = DiscriminatorType.STRING, length = 1)
@NamedQueries({@NamedQuery(name = Client.GET_ALL_QUERY_NAME, query = "SELECT cl FROM Client cl order by cl.clientId desc")})

public class Client implements Serializable {

	public static final String GET_ALL_QUERY_NAME = "Client.getAll";
	/* public static final String GET_LAST_CLIENT = "getLastClient"; */
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int clientId;
	private String clientEmail;
	private String clientPassword;
	private String clientName;
	private String clientRole;
	
	
	public Client () {
		
	}
	
	public Client (int clientId, String clientEmail, String clientPassword, String clientName, String clientRole) {
		this.clientId = clientId;
		this.clientEmail = clientEmail;
		this.clientPassword = clientPassword;
		this.clientName = clientName;
		this.clientRole = clientRole;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	
	public String getClientPassword() {
		return clientPassword;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}

	public String getClientRole() {
		return clientRole;
	}

	public void setClientRole(String clientRole) {
		this.clientRole = clientRole;
	}

}
