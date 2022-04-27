package com.example.javaeeproject.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.example.javaeeproject.entities.Admin;
import com.example.javaeeproject.interfaceRepository.InterfaceRepository;

@ManagedBean(name = "currentAdminManagedBean")
@SessionScoped
public class CurrentAdminManagedBean implements Serializable {

	@EJB
	InterfaceRepository interfaceRepository;
	
	private Admin currentAdmin = new Admin();

	public Admin getCurrentAdmin() {
		currentAdmin();
		return currentAdmin;
	}

	public void setCurrentAdmin(Admin currentAdmin) {
		this.currentAdmin = currentAdmin;
	}
	
	public void currentAdmin(){
		try {
			List<Admin> allAdmins = new ArrayList<>();
			allAdmins = interfaceRepository.getAllAdmins();
				
			// Get the normal user login email
			String currentUserEmail = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
			
			for (Admin admin : allAdmins) {
				if (admin.getClientEmail().equals(currentUserEmail)) {
					setCurrentAdmin(admin);
				}
			}
		} catch (Exception ex) {
			Logger.getLogger(TypeOfIndustryManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
