package com.example.chanaka.propertymanager.Models;

import com.example.chanaka.propertymanager.Controllers.DatabaseConnector;
import com.example.chanaka.propertymanager.Views.Login;

/**
 * Created by chanaka on 3/27/17.
 */

public class Property {
    private String id;
    private String address;
    private String propertyType;
    private String sqFootage;
    private String desc;
    private double rental;
    private double deposit;
    private String date;
    private String image;
    private String dueDate;
    private String owner;
    private float rating;

    public Property(String address, String propertyType, String sqFootage, String desc, double rental, double deposit, String date, String image) {
        this.address = address;
        this.propertyType = propertyType;
        this.sqFootage = sqFootage;
        this.desc = desc;
        this.rental = rental;
        this.deposit = deposit;
        this.date = date;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public void setSqFootage(String sqFootage) {
        this.sqFootage = sqFootage;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setRental(double rental) {
        this.rental = rental;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public String getSqFootage() {
        return sqFootage;
    }

    public String getDesc() {
        return desc;
    }

    public double getRental() {
        return rental;
    }

    public double getDeposit() {
        return deposit;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public float getRating() {
        DatabaseConnector d=DatabaseConnector.getInstance(Login.getCtx());
        return d.getRatings(address)[0];
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
