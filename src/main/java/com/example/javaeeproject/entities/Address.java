package com.example.javaeeproject.entities;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.PROPERTY)
public class Address implements Serializable {
    
    private String streetNumber;
    private String streetAddress;
    private String suburb;
    private String postcode;
    private String state;

    public Address() {
    }

    public Address(String streetNumber, String streetAddress, String suburb, String state, String postcode) {
        this.streetNumber = streetNumber;
        this.streetAddress = streetAddress;
        this.suburb = suburb;
        this.state = state;
        this.postcode = postcode;
    }

    @Column(name = "street_number")
    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Column(name = "street_address")
    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    @Column(name = "postcode")
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    @Override
    public String toString() {
        return streetNumber + " " + streetAddress + ", " + suburb + ", " + state + " " + postcode;
    } 
    
    
}

