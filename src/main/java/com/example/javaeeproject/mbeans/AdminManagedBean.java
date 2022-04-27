package com.example.javaeeproject.mbeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.example.javaeeproject.entities.Admin;
import com.example.javaeeproject.interfaceRepository.InterfaceRepository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean(name = "adminManagedBean")
@SessionScoped
public class AdminManagedBean implements Serializable {
	
	@EJB
	InterfaceRepository interfaceRepository;
	
	public AdminManagedBean () {
		
	}
	
	public Admin searchAdminById (int id) {
		try {
			return interfaceRepository.searchAdminById(id);
		} catch (Exception ex) {
			Logger.getLogger(AdminManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return null;
	}

	public List<Admin> getAllAdmins () {
		try {
			List<Admin> admins = interfaceRepository.getAllAdmins();
			return admins;
		} catch (Exception ex) {
			Logger.getLogger(AdminManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return null;
	}
	
	// Use Admin Object rather than Client Object for add functionality
	public void  addAdmin (Admin Admin) {
		try {
			interfaceRepository.addAdmin(Admin);
		} catch (Exception ex) {
			Logger.getLogger(AdminManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	
	public void editAdmin (Admin admin, String newPassword)  {
		try {
			if (!admin.getClientPassword().equals(newPassword)) {
				admin.setClientPassword(newPassword);
			}
			interfaceRepository.editAdmin(admin);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer Contact has been updated successfully"));
		} catch (Exception ex) {
			Logger.getLogger(AdminManagedBean.class.getName()).log(Level.SEVERE,null,ex);
		}
	}
	
	public void removeAdmin (int clientId) {
		try {
			interfaceRepository.removeAdmin(clientId);
		} catch (Exception ex) {
			Logger.getLogger(AdminManagedBean.class.getName()).log(Level.SEVERE,null,ex);
		}
	}
	
}
