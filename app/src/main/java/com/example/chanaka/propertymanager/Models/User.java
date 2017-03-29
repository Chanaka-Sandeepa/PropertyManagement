package com.example.chanaka.propertymanager.Models;

/**
 * Created by chanaka on 3/27/17.
 */

public class User {
     String name;
     String address;
     int contactNo;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getContactNo() {
        return contactNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }
}
