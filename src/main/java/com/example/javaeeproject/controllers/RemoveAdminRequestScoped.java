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

@Named("removeAdmin")
@RequestScoped
public class RemoveAdminRequestScoped {

	@ManagedProperty(value = "#{adminManagedBean}")
	AdminManagedBean adminManagedBean;
	
	private boolean showForm = true;
	private Admin admin;
	AdminApplicationScoped adminApplicationScoped;
	
	public RemoveAdminRequestScoped() {
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		adminApplicationScoped = (AdminApplicationScoped) FacesContext.getCurrentInstance()
																				.getApplication()
																				.getELResolver()
																				.getValue(context, null, "adminApplicationScoped");
		
		adminApplicationScoped.updateAdminList();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		adminManagedBean = (AdminManagedBean) FacesContext.getCurrentInstance()
																	.getApplication()
																	.getELResolver()
																	.getValue(elContext, null, "adminManagedBean");
	}

	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public boolean isShowForm() {
		return showForm;
	}
	
	public void removeAdmin (int adminClientId) {
		try {
			adminManagedBean.removeAdmin(adminClientId);
			adminApplicationScoped.findAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Admin has been deleted successfully"));
		} catch (Exception ex) {
			
		}
	}
}
