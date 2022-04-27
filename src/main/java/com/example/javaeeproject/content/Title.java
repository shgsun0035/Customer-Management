package com.example.javaeeproject.content;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "title")
@RequestScoped
public class Title {

	private String pageTitle;
	
	public Title () {
		pageTitle = "AUSPrintings Pty Ltd";
	}
	
	public String getPageTitle() {
		return pageTitle;
	}
	
	public void setPageTitle (String pageTitle) {
		this.pageTitle = pageTitle;
	}
}
