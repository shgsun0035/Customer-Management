package com.example.javaeeproject.controllers;

import java.util.List;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.example.javaeeproject.applicationscoped.TypeOfIndustryApplicationScoped;
import com.example.javaeeproject.entities.TypeOfIndustry;

@Named (value = "editDetailSingleTypeOfIndustry")
@RequestScoped
public class EditDetailSingleTypeOfIndustryRequestScoped {
	
	private int frontEndTypeOfIndustryId;
	
	private TypeOfIndustry typeOfIndustry;
	
	public EditDetailSingleTypeOfIndustryRequestScoped () {
		
		frontEndTypeOfIndustryId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("frontEndTypeOfIndustryId"));
		
		typeOfIndustry = getTypeOfIndustry();
	}

	public int getFrontEndTypeOfIndustryId() {
		return frontEndTypeOfIndustryId;
	}

	public void setFrontEndTypeOfIndustryId(int frontEndTypeOfIndustryId) {
		this.frontEndTypeOfIndustryId = frontEndTypeOfIndustryId;
	}
	
	public TypeOfIndustry getTypeOfIndustry() {
		if (typeOfIndustry == null) {
			ELContext context = FacesContext.getCurrentInstance().getELContext();
			TypeOfIndustryApplicationScoped typeOfIndustryApplicationScoped = (TypeOfIndustryApplicationScoped) FacesContext.getCurrentInstance()
																														.getApplication()
																														.getELResolver()
																														.getValue(context, null, "typeOfIndustryApplicationScoped");
			int selectTypeOfIndustryId = --frontEndTypeOfIndustryId;
			List<TypeOfIndustry> typeOfIndustries = typeOfIndustryApplicationScoped.getTypeOfIndustries();
			for (TypeOfIndustry typeOfIndustry : typeOfIndustries) {
				if (typeOfIndustry.getTypeOfIndustryId() == selectTypeOfIndustryId) {
					return typeOfIndustry;
				}
			}
		}
		
		return typeOfIndustry;
	}

}
