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

@Named("removeNormalUser")
@RequestScoped
public class RemoveNormalUserRequestScoped {

	@ManagedProperty(value = "#{normalUserManagedBean}")
	NormalUserManagedBean normalUserManagedBean;
	
	private boolean showForm = true;
	private NormalUser normalUser;
	NormalUserApplicationScoped normalUserApplicationScoped;
	
	public RemoveNormalUserRequestScoped() {
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		normalUserApplicationScoped = (NormalUserApplicationScoped) FacesContext.getCurrentInstance()
																				.getApplication()
																				.getELResolver()
																				.getValue(context, null, "normalUserApplicationScoped");
		
		normalUserApplicationScoped.updateNormalUserList();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		normalUserManagedBean = (NormalUserManagedBean) FacesContext.getCurrentInstance()
																	.getApplication()
																	.getELResolver()
																	.getValue(elContext, null, "normalUserManagedBean");
	}

	public NormalUser getNormalUser() {
		return normalUser;
	}

	public void setNormalUser(NormalUser normalUser) {
		this.normalUser = normalUser;
	}
	
	public boolean isShowForm() {
		return showForm;
	}
	
	public void removeNormalUser(int normalUserClientId) {
		try {
			normalUserManagedBean.removeNormalUser(normalUserClientId);
			normalUserApplicationScoped.findAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Normal User has been deleted successfully"));
		} catch (Exception ex) {
			
		}
	}
}
