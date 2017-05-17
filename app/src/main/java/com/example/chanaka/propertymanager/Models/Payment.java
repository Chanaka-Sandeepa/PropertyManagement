package com.example.chanaka.propertymanager.Models;

/**
 * Created by chanaka on 3/27/17.
 */

public class Payment {
    private float amount;
    private String type;
    private int tenant;
    private int property;
    private String date;
    private int id;
    private String user;

    public Payment(float amount, String type, int tenant, int property, String date) {
        this.amount = amount;
        this.type = type;
        this.tenant = tenant;
        this.property = property;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public int getTenant() {
        return tenant;
    }

    public int getProperty() {
        return property;
    }

    public String getDate() {
        return date;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTenant(int tenant) {
        this.tenant = tenant;
    }

    public void setProperty(int property) {
        this.property = property;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String owner) {
        this.user = owner;
    }
}
