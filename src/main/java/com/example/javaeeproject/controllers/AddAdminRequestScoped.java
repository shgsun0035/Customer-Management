package com.example.javaeeproject.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.example.javaeeproject.applicationscoped.AdminApplicationScoped;
import com.example.javaeeproject.entities.Admin;
import com.example.javaeeproject.mbeans.AdminManagedBean;

@RequestScoped
@Named ("addAdmin")
public class AddAdminRequestScoped {

	@ManagedProperty(value = "#{adminManagedBean}")
	AdminManagedBean adminManagedBean;
	
	private boolean showForm = true;
	
	private String clientEmail;
	private String clientName;
	private String clientPassword;
	private String clientRole;
	private String adminTitle;
	
	AdminApplicationScoped adminApplicationScoped;
	
	public AddAdminRequestScoped () {
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		adminApplicationScoped = (AdminApplicationScoped) FacesContext.getCurrentInstance()
																			    .getApplication()
																			    .getELResolver()
																			    .getValue(context, null, "adminApplicationScoped");
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		adminManagedBean = (AdminManagedBean) FacesContext.getCurrentInstance()
																	.getApplication()
																	.getELResolver()
																	.getValue(elContext, null, "adminManagedBean");
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
	
	public String getAdminTitle() {
		return adminTitle;
	}

	public void setAdminTitle(String adminTitle) {
		this.adminTitle = adminTitle;
	}

	public void addAdmin (String clientEmail, String clientName, String clientPassword, String adminTitle) {
		try {
			Admin admin = new Admin ();
			admin.setClientEmail(clientEmail);
			admin.setClientName(clientName);
			admin.setClientPassword(clientPassword);
			admin.setClientRole("admin");
			admin.setAdminTitle(adminTitle);
			
			adminManagedBean.addAdmin (admin);
			adminApplicationScoped.findAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Admin has been added successfully"));
		} catch (Exception ex) {
			
		}
		showForm = true;
	}
	
	
	
}
