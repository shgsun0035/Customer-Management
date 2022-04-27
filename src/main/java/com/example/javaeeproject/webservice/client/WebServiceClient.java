package com.example.javaeeproject.webservice.client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class WebServiceClient {

	private WebTarget webTarget;
	private Client client;
//	private static final String BASE_URL = "http://0.0.0.0:8080/Assignment-war/webresources";
	private static final String BASE_URL = "http://0.0.0.0:8080/webresources";
	
	// Initialize URL
	public WebServiceClient() {
		client = javax.ws.rs.client.ClientBuilder.newClient();
		webTarget = client.target(BASE_URL).path("industry");
	}
	
	// Initialize "GET" API
	public String getHtml() throws ClientErrorException {
		WebTarget resource = webTarget;
		return resource.request(javax.ws.rs.core.MediaType.TEXT_HTML).get(String.class);
	}
	
	// Initialize "POST" API
	public void setPost() throws ClientErrorException {
		webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).post(null);
	}
	
	// Initialize second "POST" API
	public void setPost2(String typeOfIndustryId, String typeOfIndustryName) throws ClientErrorException {
		
		Form form = new Form();
		form.param("typeOfIndustryId", typeOfIndustryId);
		form.param("typeOfIndustryName", typeOfIndustryName);
		
		String test = webTarget.toString();
		
		webTarget.request(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
	}
	
	// Initialize second "PUT" API
	public void putHtml (Object requestEntity) throws ClientErrorException{
		
		webTarget.request(javax.ws.rs.core.MediaType.TEXT_HTML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.TEXT_HTML));
	}
	
	public void close() {
		client.close();
	}
	
}
