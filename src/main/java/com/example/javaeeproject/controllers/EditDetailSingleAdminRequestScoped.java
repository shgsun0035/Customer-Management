package com.example.javaeeproject.controllers;

import java.util.List;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.example.javaeeproject.applicationscoped.AdminApplicationScoped;
import com.example.javaeeproject.entities.Admin;

@Named (value = "editDetailSingleAdmin")
@RequestScoped
public class EditDetailSingleAdminRequestScoped {

	private int frontEndAdminId;
	
	private Admin admin;
	
	public EditDetailSingleAdminRequestScoped () {
		
		frontEndAdminId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("frontEndAdminId"));
		
		admin = getAdmin();
	}
	
	public int getFrontEndAdminId() {
		return frontEndAdminId;
	}



	public void setFrontEndAdminId(int frontEndAdminId) {
		this.frontEndAdminId = frontEndAdminId;
	}



	public Admin getAdmin(){
		if (admin == null) {
			ELContext context = FacesContext.getCurrentInstance().getELContext();
			AdminApplicationScoped adminApplicationScoped = (AdminApplicationScoped) FacesContext.getCurrentInstance()
																												.getApplication()
																												.getELResolver()
																												.getValue(context, null, "adminApplicationScoped");
			int selectAdminId = --frontEndAdminId;
			List<Admin> admins = adminApplicationScoped.getAdmins();
			for (Admin admin : admins) {
				if(admin.getClientId() == selectAdminId) {
					return admin;
				}
			}
		}
		
		return admin;
	}
}
