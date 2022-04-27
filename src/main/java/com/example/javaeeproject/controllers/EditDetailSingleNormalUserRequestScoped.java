package com.example.javaeeproject.controllers;

import java.util.List;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.example.javaeeproject.applicationscoped.NormalUserApplicationScoped;
import com.example.javaeeproject.entities.NormalUser;

@Named (value = "editDetailSingleNormalUser")
@RequestScoped
public class EditDetailSingleNormalUserRequestScoped {

	private int frontEndNormalUserId;
	
	private NormalUser normalUser;
	
	public EditDetailSingleNormalUserRequestScoped () {
			
		frontEndNormalUserId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("frontEndNormalUserId"));
		
		normalUser = getNormalUser();
			
	}

	public int getFrontEndNormalUserId() {
		return frontEndNormalUserId;
	}

	public void setFrontEndNormalUserId(int frontEndNormalUserId) {
		this.frontEndNormalUserId = frontEndNormalUserId;
	}

	public NormalUser getNormalUser(){
		
		if (normalUser == null) {
			ELContext context = FacesContext.getCurrentInstance().getELContext();
			NormalUserApplicationScoped normalUserApplicationScoped = (NormalUserApplicationScoped) FacesContext.getCurrentInstance()
																												.getApplication()
																												.getELResolver()
																												.getValue(context, null, "normalUserApplicationScoped");

			int selectNormalUserId = --frontEndNormalUserId;
			List<NormalUser> normalUsers = normalUserApplicationScoped.getNormalUsers();
			for (NormalUser normalUser : normalUsers) {
				if (normalUser.getClientId() == selectNormalUserId) {
					return normalUser;
				}
			}
			
			return normalUser;
		}
		
		return normalUser;
	}
}
