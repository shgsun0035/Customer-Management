package com.example.javaeeproject.webservice.api;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.example.javaeeproject.entities.TypeOfIndustry;
import com.example.javaeeproject.interfaceRepository.InterfaceRepository;

@Path("industry")
public class WebServiceAPI {
	
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    @EJB
    InterfaceRepository interfaceRepository;
    
    public WebServiceAPI() {
        
    }

    @GET
    @Produces("text/html")
    public String getHtml()  throws Exception {
    	List<TypeOfIndustry> industries = interfaceRepository.getAllTypeOfIndustries();
		String content = "";
		for (TypeOfIndustry industry : industries) {
			content += String.valueOf(industry.getTypeOfIndustryId()) + "-";
			content += industry.getTypeOfIndustryName() + ",";		
		}
		return content;
    }
    
    @GET
    @Path("getindustry")
    @Produces("text/html")
    public String getHtml2() throws Exception {
    	List<TypeOfIndustry> industries = interfaceRepository.getAllTypeOfIndustries();
		String content = "";
		for (TypeOfIndustry industry : industries) {
			content += String.valueOf(industry.getTypeOfIndustryId()) + " ";
			content += industry.getTypeOfIndustryName();
			content += "<br>";
			
		}
		return "<html><body><p>" + content + "</p></body></html>";
    }


    @POST
    @Consumes("application/x-www-form-urlencoded")
    public void setPost(@FormParam("typeOfIndustryId") String typeOfIndustryId, @FormParam("typeOfIndustryName") String typeOfIndustryName ) throws Exception {
    	
    	TypeOfIndustry typeOfIndustry = new TypeOfIndustry();
    	
    	typeOfIndustry.setTypeOfIndustryId(Integer.parseInt(typeOfIndustryId));
    	typeOfIndustry.setTypeOfIndustryName(typeOfIndustryName);
    	
    	interfaceRepository.addTypeOfIndustry(typeOfIndustry);
    }
    
    @PUT
    @Consumes("text/html")
    public void putHtml(String content) {
    }

}