package com.example.javaeeproject.applicationscoped;

import java.util.ArrayList;

import javax.el.ELContext;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.example.javaeeproject.entities.Admin;
import com.example.javaeeproject.mbeans.AdminManagedBean;

@Named (value = "adminApplicationScoped")
@ApplicationScoped
public class AdminApplicationScoped {

	@ManagedProperty(value = "#{adminManagedBean}")
	AdminManagedBean adminManagedBean;
	
	private ArrayList<Admin> admins;
	
	private boolean showForm = true;
	
	public boolean isShowForm () {
		return showForm;
	}
	
	public AdminApplicationScoped () throws Exception {
		admins = new ArrayList<>();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		adminManagedBean  = (AdminManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "adminManagedBean");
		
		updateAdminList();
	}
	
	public ArrayList<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(ArrayList<Admin> admins) {
		this.admins = admins;
	}

	public void updateAdminList () {
		if (admins != null && admins.size() > 0) {
			
		} else {
			admins.clear();
			for (Admin admin : adminManagedBean.getAllAdmins()) {
				admins.add(admin);
			}
		}
	}
	
	public void findAll () {
		admins.clear();
		
		for (Admin admin : adminManagedBean.getAllAdmins()) {
			admins.add(admin);
		}
	}
	
}
