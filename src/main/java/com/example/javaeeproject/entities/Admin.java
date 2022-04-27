package com.example.javaeeproject.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue(value = "A")
@NamedQuery(name = Admin.GET_ALL_QUERY_NAME, query = "SELECT ad FROM Admin ad order by ad.clientId desc")
public class Admin extends Client implements Serializable {
		
	public static final String GET_ALL_QUERY_NAME = "Admin.getAll";
	
	private String adminTitle;
	
	public Admin () {
	}

	public Admin(int clientId, String adminTitle, String clientEmail, String clientPassword, String clientName, String clientRole) {
		super(clientId, clientEmail, clientPassword, clientName, clientRole);
		this.adminTitle = adminTitle;
	}

	public String getAdminTitle() {
		return adminTitle;
	}

	public void setAdminTitle(String adminTitle) {
		this.adminTitle = adminTitle;
	}

}
