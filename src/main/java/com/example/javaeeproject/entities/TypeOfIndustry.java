package com.example.javaeeproject.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "typeOfIndustry")
@NamedQuery (name = TypeOfIndustry.GET_ALL_QUERY_NAME, query = "SELECT t FROM TypeOfIndustry t order by t.typeOfIndustryId desc")
public class TypeOfIndustry implements Serializable {
	
	public static final String GET_ALL_QUERY_NAME = "TypeOfIndustry.getAll";
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int typeOfIndustryId;
	private String typeOfIndustryName;
	
	@OneToMany (mappedBy = "typeOfIndustry")
	private Set<Customer> customers;
	
	public TypeOfIndustry () {
		
	}

	public TypeOfIndustry(int typeOfIndustryId, String typeOfIndustryName) {
		this.typeOfIndustryId = typeOfIndustryId;
		this.typeOfIndustryName = typeOfIndustryName;
//		this.customers = customers;
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

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}	
	
}
