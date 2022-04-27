package com.example.javaeeproject.applicationscoped;

import java.util.ArrayList;

import javax.el.ELContext;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.example.javaeeproject.entities.NormalUser;
import com.example.javaeeproject.mbeans.NormalUserManagedBean;
@Named (value = "normalUserApplicationScoped")
@ApplicationScoped
public class NormalUserApplicationScoped {

	@ManagedProperty(value = "#{normalUserManagedBean}")
	NormalUserManagedBean normalUserManagedBean;
	
	private ArrayList<NormalUser> normalUsers;
	
	private boolean showForm = true;
	
	public boolean isShowForm () {
		return showForm;
	}
	
	public NormalUserApplicationScoped () throws Exception {
		normalUsers = new ArrayList<>();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		normalUserManagedBean  = (NormalUserManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "normalUserManagedBean");
		
		updateNormalUserList();
	}
	
	public ArrayList<NormalUser> getNormalUsers () {
		return normalUsers;
	}
	
	public void setNormalUsers (ArrayList<NormalUser> normalUsers) {
		this.normalUsers = normalUsers;
	}
	
	public void updateNormalUserList () {
		if (normalUsers != null && normalUsers.size() > 0) {
			
		} else {
			normalUsers.clear();
			for (NormalUser normalUser : normalUserManagedBean.getAllNormalUsers()) {
				normalUsers.add(normalUser);
			}
		}
	}
	
	public void findAll () {
		normalUsers.clear();
		
		for (NormalUser normalUser : normalUserManagedBean.getAllNormalUsers()) {
			normalUsers.add(normalUser);
		}
	}
	
}
