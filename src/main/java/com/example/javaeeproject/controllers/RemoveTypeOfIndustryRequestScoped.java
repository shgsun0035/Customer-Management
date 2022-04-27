package com.example.javaeeproject.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.example.javaeeproject.applicationscoped.TypeOfIndustryApplicationScoped;
import com.example.javaeeproject.entities.TypeOfIndustry;
import com.example.javaeeproject.mbeans.TypeOfIndustryManagedBean;

@Named ("removeTypeOfIndustry")
@RequestScoped
public class RemoveTypeOfIndustryRequestScoped {
	
	@ManagedProperty(value = "#{typeOfIndustryManagedBean}")
	TypeOfIndustryManagedBean typeOfIndustryManagedBean;
	
	private boolean showForm = true;
	private TypeOfIndustry typeOfIndustry;
	
	TypeOfIndustryApplicationScoped typeOfIndustryApplicationScoped;
	
	public RemoveTypeOfIndustryRequestScoped () {
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		typeOfIndustryApplicationScoped = (TypeOfIndustryApplicationScoped) FacesContext.getCurrentInstance()
																						  .getApplication()
																						  .getELResolver()
																						  .getValue(context, null, "typeOfIndustryApplicationScoped");
		
		typeOfIndustryApplicationScoped.updateTypeOfIndustryList();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		typeOfIndustryManagedBean = (TypeOfIndustryManagedBean) FacesContext.getCurrentInstance()
															    .getApplication()
															    .getELResolver()
															    .getValue(context, null, "typeOfIndustryManagedBean");
	}

	public TypeOfIndustry getTypeOfIndustry() {
		return typeOfIndustry;
	}

	public void setTypeOfIndustry(TypeOfIndustry typeOfIndustry) {
		this.typeOfIndustry = typeOfIndustry;
	}
	
	public boolean isShowForm () {
		return showForm;
	}
	
	public void removeTypeOfIndustry (int typeOfIndustryId) {
		try {
			typeOfIndustryManagedBean.removeTypeOfIndustry(typeOfIndustryId);
			typeOfIndustryApplicationScoped.findAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Industry has been deleted successfully"));
		} catch (Exception ex) {
			
		}
		
		showForm = true;
	}

}
