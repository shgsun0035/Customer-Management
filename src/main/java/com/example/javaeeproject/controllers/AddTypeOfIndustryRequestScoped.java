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

@RequestScoped
@Named ("addTypeOfIndustry")
public class AddTypeOfIndustryRequestScoped {

	@ManagedProperty(value = "#{typeOfIndustryManagedBean}")
	TypeOfIndustryManagedBean typeOfIndustryManagedBean;
	
	private boolean showForm = true;
	
	private String typeOfIndustryName;
	
	TypeOfIndustryApplicationScoped typeOfIndustryApplicationScoped;
	
	public AddTypeOfIndustryRequestScoped () {
		ELContext context = FacesContext.getCurrentInstance().getELContext();
		
		typeOfIndustryApplicationScoped = (TypeOfIndustryApplicationScoped) FacesContext.getCurrentInstance()
																			.getApplication()
																			.getELResolver()
																			.getValue(context, null, "typeOfIndustryApplicationScoped");
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		typeOfIndustryManagedBean = (TypeOfIndustryManagedBean) FacesContext.getCurrentInstance()
																		      .getApplication()
																		      .getELResolver()
																		      .getValue(elContext, null, "typeOfIndustryManagedBean");
	}
	
	public boolean isShowForm () {
		return showForm;
	}

	public String getTypeOfIndustryName() {
		return typeOfIndustryName;
	}

	public void setTypeOfIndustryName(String typeOfIndustryName) {
		this.typeOfIndustryName = typeOfIndustryName;
	}
	
	public void addTypeOfIndustry (String typeOfIndustryName) {
		try {
			TypeOfIndustry typeOfIndustry = new TypeOfIndustry();
			typeOfIndustry.setTypeOfIndustryName(typeOfIndustryName);
			
			typeOfIndustryManagedBean.addTypeOfIndustry(typeOfIndustry);
			typeOfIndustryApplicationScoped.findAll();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Type Of Industry has been added successfully"));
		} catch (Exception ex) {
			
		}
		showForm = true;
	}
}
