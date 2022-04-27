package com.example.javaeeproject.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.example.javaeeproject.applicationscoped.NormalUserApplicationScoped;
import com.example.javaeeproject.entities.NormalUser;
import com.example.javaeeproject.mbeans.NormalUserManagedBean;

@RequestScoped
@Named ("addNormalUser")
public class AddNormalUserRequestScoped {

	@ManagedProperty(value = "#{normalUserManagedBean}")
	NormalUserManagedBean normalUserManagedBean;
	
	private boolean showForm = true;
	
	private String clientEmail;
	private String clientName;
	private String clientPassword;
	private String clientRole;
	private String normalUserWorkNumber;
	
	NormalUserApplicationScoped normalUserApplicationScoped;
	
	public AddNormalUserRequestScoped () {
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		normalUserApplicationScoped = (NormalUserApplicationScoped) FacesContext.getCurrentInstance()
																			    .getApplication()
																			    .getELResolver()
																			    .getValue(context, null, "normalUserApplicationScoped");
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		normalUserManagedBean = (NormalUserManagedBean) FacesContext.getCurrentInstance()
																	.getApplication()
																	.getELResolver()
																	.getValue(elContext, null, "normalUserManagedBean");
	}
	
	public boolean isShowForm () {
		return showForm;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientPassword() {
		return clientPassword;
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

	public String getNormalUserWorkNumber() {
		return normalUserWorkNumber;
	}

	public void setNormalUserWorkNumber(String normalUserWorkNumber) {
		this.normalUserWorkNumber = normalUserWorkNumber;
	}
	
	public void addNormalUser (String clientEmail, String clientName, String clientPassword, String normalUserWorkNumber) {
		try {
			NormalUser normalUser = new NormalUser ();
			normalUser.setClientEmail(clientEmail);
			normalUser.setClientName(clientName);
			normalUser.setClientPassword(clientPassword);
			normalUser.setClientRole("normalUser");
			normalUser.setNormalUserWorkNumber(normalUserWorkNumber);
			
			normalUserManagedBean.addNormalUser(normalUser);
			normalUserApplicationScoped.findAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Normal User has been added successfully"));
		} catch (Exception ex) {
			
		}
		showForm = true;
	}
	
	
	
}
