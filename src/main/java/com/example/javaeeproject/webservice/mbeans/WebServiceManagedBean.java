package com.example.javaeeproject.webservice.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import com.example.javaeeproject.webservice.client.WebServiceClient;

@Named(value = "webServiceManagedBean")
@SessionScoped
public class WebServiceManagedBean {

	private int typeOfIndustryId;
	private String typeOfIndustryName;
	
	// ---------------------- Initialize the Client ----------------------
	private WebServiceClient webServiceClient;
	
	public WebServiceManagedBean() {
		
	}

	public int getTypeOfIndustryId() {
		return typeOfIndustryId;
	}

	public void setTypeOfIndustryId(int typeOfIndustryId) {
		this.typeOfIndustryId = typeOfIndustryId;
	}

	public String getTypeOfIndustryName() {
		return typeOfIndustryName;
	}

	public void setTypeOfIndustryName(String typeOfIndustryName) {
		this.typeOfIndustryName = typeOfIndustryName;
	}
	
	public void setWebServiceClient() {
		webServiceClient = new WebServiceClient();
		String a = String.valueOf(getTypeOfIndustryId());
		String b = getTypeOfIndustryName();
		webServiceClient.setPost2(String.valueOf(getTypeOfIndustryId()),getTypeOfIndustryName());
	}
	
	public String checkIndustryIdWebServiceClient(String inputId) {
		webServiceClient = new WebServiceClient();
		String c = webServiceClient.getHtml();
		String[] industryList = c.split(",");
		
		List<String> industryIdList = new ArrayList<>();

		for (String item : industryList) {
			String[] itemList = item.split("-");
			industryIdList.add(itemList[0]);
		}
		
		for (String each : industryIdList) {
			if (each.equals(inputId.trim())) {
				return "Input Id has been existed";
			}
		}
		
		return null;
	}
	
	public String checkIndustryNameWebServiceClient(String inputName) {
		webServiceClient = new WebServiceClient();
		String c = webServiceClient.getHtml();
		String[] industryList = c.split(",");
		
		List<String> industryNameList = new ArrayList<>();

		for (String item : industryList) {
			String[] itemList = item.split("-");
			industryNameList.add(itemList[1]);
		}

		for (String each : industryNameList) {
			if (inputName.trim().equals(each)) {
				return "Input Name has been existed";
			}
		}
		return null;
	}
}
