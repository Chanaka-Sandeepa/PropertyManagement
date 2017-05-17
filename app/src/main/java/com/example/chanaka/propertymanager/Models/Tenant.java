package com.example.chanaka.propertymanager.Models;

/**
 * Created by chanaka on 3/27/17.
 */

public class Tenant extends User {
    private String apartment;
    private String image;
    private String owner;

    public Tenant(String name,String address,int contactNo,String apartment, String image) {
        this.name=name;
        this.address=address;
        this.contactNo=contactNo;
        this.apartment = apartment;
        this.image = image;
    }

    public String getApartment() {
        return apartment;
    }

    public String getImage() {
        return image;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
