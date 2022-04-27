package com.example.javaeeproject.applicationscoped;

import java.util.ArrayList;

import javax.el.ELContext;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.example.javaeeproject.entities.TypeOfIndustry;
import com.example.javaeeproject.mbeans.TypeOfIndustryManagedBean;

@Named (value = "typeOfIndustryApplicationScoped")
@ApplicationScoped
public class TypeOfIndustryApplicationScoped {

	@ManagedProperty(value = "#{typeOfIndustryManagedBean}")
	TypeOfIndustryManagedBean typeOfIndustryManagedBean;
	
	private ArrayList<TypeOfIndustry> typeOfIndustries;
	
	private boolean showForm = true;
	
	public boolean isShowForm() {
		return showForm;
	}
	
	public TypeOfIndustryApplicationScoped () {
		typeOfIndustries = new ArrayList<>();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		typeOfIndustryManagedBean = (TypeOfIndustryManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "typeOfIndustryManagedBean");
		
		updateTypeOfIndustryList();
	}

	public ArrayList<TypeOfIndustry> getTypeOfIndustries() {
		return typeOfIndustries;
	}

	public void setTypeOfIndustries(ArrayList<TypeOfIndustry> typeOfIndustries) {
		this.typeOfIndustries = typeOfIndustries;
	}
	
	public void updateTypeOfIndustryList () {
		if (typeOfIndustries != null && typeOfIndustries.size() > 0) {
			
		} else {
			typeOfIndustries.clear();
			
			for (TypeOfIndustry typeOfIndustry : typeOfIndustryManagedBean.getAllTypeOfIndustries()) {
				typeOfIndustries.add(typeOfIndustry);
			}
		}
	}
	
	public void findAll () {
		typeOfIndustries.clear();
		
		for (TypeOfIndustry typeOfIndustry : typeOfIndustryManagedBean.getAllTypeOfIndustries()) {
			typeOfIndustries.add(typeOfIndustry);
		}
	}	
}
