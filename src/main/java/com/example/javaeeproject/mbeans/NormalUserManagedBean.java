package com.example.javaeeproject.mbeans;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.javaeeproject.entities.Customer;
import com.example.javaeeproject.entities.NormalUser;
import com.example.javaeeproject.interfaceRepository.InterfaceRepository;

@ManagedBean(name = "normalUserManagedBean")
@SessionScoped
public class NormalUserManagedBean implements Serializable {
	
	@EJB
	InterfaceRepository interfaceRepository;
	
	public NormalUserManagedBean () {
		
	}
	
	public NormalUser searchNormalUserById (int id) {
		try {
			return interfaceRepository.searchNormalUserById(id);
		} catch (Exception ex) {
			Logger.getLogger(NormalUserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return null;
	}

	public List<NormalUser> getAllNormalUsers () {
		try {
			List<NormalUser> normalUsers = interfaceRepository.getAllNormalUsers();
			return normalUsers;
		} catch (Exception ex) {
			Logger.getLogger(NormalUserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return null;
	}
	
	public List<Customer> getAllCustomers () {
		try {
			List<Customer> customers = interfaceRepository.getAllCustomers();
			return customers;
		} catch (Exception ex) {
			Logger.getLogger(NormalUserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return null;
	}
	
	// Use NormalUser Object rather than Client Object for add functionality
	public void  addNormalUser (NormalUser normalUser) {
		try {
			interfaceRepository.addNormalUser(normalUser);
		} catch (Exception ex) {
			Logger.getLogger(NormalUserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	
	public void editNormalUser (NormalUser normalUser, String newPassword)  {
		try {
			// To prevent admin edit normal user
			String a = normalUser.getClientPassword();
			String b = newPassword;
			if (!normalUser.getClientPassword().equals(newPassword)) {
				normalUser.setClientPassword(newPassword);
			}
			interfaceRepository.editNormalUser(normalUser);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer Contact has been updated successfully"));
		} catch (Exception ex) {
			Logger.getLogger(NormalUserManagedBean.class.getName()).log(Level.SEVERE,null,ex);
		}
	}
	
	public void removeNormalUser (int clientId) {
		try {
			interfaceRepository.removeNormalUser(clientId);
		} catch (Exception ex) {
			Logger.getLogger(NormalUserManagedBean.class.getName()).log(Level.SEVERE,null,ex);
		}
	}
	
}
