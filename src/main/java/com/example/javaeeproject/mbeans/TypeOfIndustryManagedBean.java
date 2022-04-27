package com.example.javaeeproject.mbeans;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.example.javaeeproject.entities.TypeOfIndustry;
import com.example.javaeeproject.interfaceRepository.InterfaceRepository;

@ManagedBean(name = "typeOfIndustryManagedBean")
@SessionScoped
public class TypeOfIndustryManagedBean implements Serializable {
	
	@EJB
	InterfaceRepository interfaceRepository;
	
	public TypeOfIndustryManagedBean () {
		
	}
	
	public TypeOfIndustry searchTypeOfIndustryById (int id) {
		try {
			return interfaceRepository.searchTypeOfIndustryById(id);
		} catch (Exception ex) {
			Logger.getLogger(TypeOfIndustryManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null; 
	}
	
	public List<TypeOfIndustry> getAllTypeOfIndustries () {
		try {
			List<TypeOfIndustry> typeOfIndustries = interfaceRepository.getAllTypeOfIndustries();
			return typeOfIndustries;
		} catch (Exception ex) {
			Logger.getLogger(TypeOfIndustryManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null; 
	}
	
	public void addTypeOfIndustry (TypeOfIndustry typeOfIndustry) {
		try {
			interfaceRepository.addTypeOfIndustry(typeOfIndustry);
		} catch (Exception ex) {
			Logger.getLogger(TypeOfIndustryManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void editTypeOfIndustry (TypeOfIndustry typeOfIndustry) {
		try {
			interfaceRepository.editTypeOfIndustry(typeOfIndustry);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Type Of Industry has been updated successfully"));
		} catch (Exception ex) {
			Logger.getLogger(TypeOfIndustryManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void removeTypeOfIndustry (int typeOfIndustryId) {
		try {
			interfaceRepository.removeTypeOfIndustry(typeOfIndustryId);
		} catch (Exception ex) {
			Logger.getLogger(TypeOfIndustryManagedBean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
