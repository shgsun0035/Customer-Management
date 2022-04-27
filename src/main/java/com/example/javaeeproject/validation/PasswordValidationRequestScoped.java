package com.example.javaeeproject.validation;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.example.javaeeproject.applicationscoped.AdminApplicationScoped;
import com.example.javaeeproject.applicationscoped.NormalUserApplicationScoped;
import com.example.javaeeproject.entities.Admin;
import com.example.javaeeproject.entities.NormalUser;

@Named (value = "passwordValidation")
@RequestScoped
public class PasswordValidationRequestScoped {

	private String newPassword;
	private String confirmPassword;
	
	public PasswordValidationRequestScoped() {
		
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public String checkAdminOriginalPassword(String newPassword){ 
		
		// get the login user email
		String currentUserEmail = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
		
		String originalPassword = "";
		
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		AdminApplicationScoped adminApplicationScoped = (AdminApplicationScoped) FacesContext.getCurrentInstance()
																							 .getApplication()
																							 .getELResolver()
																							 .getValue(context, null, "adminApplicationScoped");

		List<Admin> admins = adminApplicationScoped.getAdmins();
		for (Admin admin : admins) {
			if (admin.getClientEmail().equals(currentUserEmail)) {
				originalPassword = admin.getClientPassword();
				break;
			}
		}
	
			
		 try {
			 
			 MessageDigest digest = MessageDigest.getInstance("SHA-256");
			 byte[] hash = digest.digest(newPassword.trim().getBytes(StandardCharsets.UTF_8));
			 String hashPassword = bytesToHex(hash);
			 
		     if (originalPassword.equals(hashPassword)) { 
		    	 
		    	 return "this password is same as before"; 
			} 
		 } catch (Exception e) {
			 
		 }	 
		 return ""; 
	}

	public String checkNormalUserOriginalPassword(String newPassword){ 
		
			// get the login user email
//			String currentUserEmail = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
			int frontEndNormalUserId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("frontEndNormalUserId"));
			
			String originalPassword = "";
			
			ELContext context = FacesContext.getCurrentInstance().getELContext();
			NormalUserApplicationScoped normalUserApplicationScoped = (NormalUserApplicationScoped) FacesContext.getCurrentInstance()
																												.getApplication()
																												.getELResolver()
																												.getValue(context, null, "normalUserApplicationScoped");

			int selectNormalUserId = --frontEndNormalUserId;
			List<NormalUser> normalUsers = normalUserApplicationScoped.getNormalUsers();
			for (NormalUser normalUser : normalUsers) {
				if (normalUser.getClientId() == selectNormalUserId) {
					originalPassword = normalUser.getClientPassword();
					break;
				}
			}
		
				
		 try {
			 
			 MessageDigest digest = MessageDigest.getInstance("SHA-256");
			 byte[] hash = digest.digest(newPassword.trim().getBytes(StandardCharsets.UTF_8));
			 String hashPassword = bytesToHex(hash);
			 
		     if (originalPassword.equals(hashPassword)) { 
		    	 
		    	 return "this password is same as before"; 
			} 
		 } catch (Exception e) {
			 
		 }	 
		 return ""; 
	}
	
	public String checkSamePassword(String newPassword, String confirmPassword) {
		
		String a = newPassword;
		String b = confirmPassword;
		
		if (!newPassword.equals(confirmPassword)) {
			return "two password is not the same";
		}
		
		return "";
		
	}
	 
	// SHA-256 Hash Function
	private String bytesToHex(byte[] hash) {
	    StringBuilder hexString = new StringBuilder(2 * hash.length);
	    for (int i = 0; i < hash.length; i++) {
	        String hex = Integer.toHexString(0xff & hash[i]);
	        if(hex.length() == 1) {
	            hexString.append('0');
	        }
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
	
}
