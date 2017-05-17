package com.example.chanaka.propertymanager.Controllers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.chanaka.propertymanager.Models.Payment;
import com.example.chanaka.propertymanager.Models.Property;
import com.example.chanaka.propertymanager.Models.Tenant;
import com.example.chanaka.propertymanager.Views.Home;
import com.example.chanaka.propertymanager.Views.Login;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Created by chanaka on 5/3/17.
 */

public class User_Handler {

    Tenant tenant;
    Context context;
    DatabaseConnector dbCon;

    public User_Handler(Context context) {
        this.context = context;
        dbCon=DatabaseConnector.getInstance(context);
    }

    //Create a tenant
    public void createUser(String[] info,int contact){
        tenant=new Tenant(info[0], info[1], contact, info[2],info[3]);
        tenant.setOwner(SaveSharedPreferences.getUserName(Login.getCtx()));
        dbCon.addTenant(tenant);

        Toast.makeText(context, "Tenant Created Successfully",Toast.LENGTH_LONG).show();
    }

    //get the list of saved tenants
    public String[] viewTenants(){
        ArrayList<Tenant> a=dbCon.getTenants();
        String[] tenants=new String[a.size()];
        for (int i=0;i<a.size();i++){
            tenants[i]=a.get(i).getName();
        }
        return tenants;
    }

    //get a tenant by name
    public Tenant getTenantByName(String s){
        return dbCon.getTenant(dbCon.getTenantId(s));
    }

    //delete tenant
    public void deleteProperty(String name){
        dbCon.removeRecord("tenantsDetails" , "name=?", new String[]{name});
    }

    //get the list of notifications
    public String[] getNotifications(){
        ArrayList<Payment> a=dbCon.getPayments();
        String[] alerts=new String[3];
        float totalAmount=0;
        alerts[0]="no previous payments";
        if(a.size()>0)
        alerts[0]= "Last Payment             "+a.get(a.size()-1).getAmount()+".00";

        for (int i=0;  i<a.size();  i++) {
            totalAmount += a.get(i).getAmount();
        }

        alerts[1]= "Total amount payed       "+totalAmount+".00";

        alerts[2]= "Next due date            "+dbCon.getPropertyByAddress(dbCon.getUserApartment(SaveSharedPreferences.getUserName(Login.getCtx()))).getDueDate();;

        return alerts;

    }

    public String checkForNotifications(int i){
        if(i==1){
            ArrayList<Property> properties=dbCon.getApartments();
            int dueDate=31;
            for(int j=0;j<properties.size();j++){
                if(properties.get(j).getDueDate() != null){
                    if(Integer.parseInt(properties.get(j).getDueDate())<dueDate) {
                        dueDate = Integer.parseInt(properties.get(j).getDueDate());
                    }
                }
            }
            return "You have to collect payments on "+dueDate;
        }else{
            String dueDate=dbCon.getPropertyByAddress(dbCon.getUserApartment(SaveSharedPreferences.getUserName(Login.getCtx()))).getDueDate();
            String lastPay= "0";
            if(dbCon.getPayments().size()>0) {
                lastPay = dbCon.getPayments().get(dbCon.getPayments().size() - 1).getDate();
            }
            return checkRemainingDays(dueDate,lastPay);
        }
    }
    public String checkRemainingDays(String s,String l){
        if(s !=null) {
            int dueDate = Integer.parseInt(s);
            int lastPay=0;
            if(l.length()>8) {
                lastPay = Integer.parseInt(l.substring(8));
            }
            int currentDate = Integer.parseInt(Home.getDate().substring(8));
            if (dueDate >= currentDate && dueDate - currentDate < 4 && currentDate - lastPay >7) {
                return "Your next payment is due on '" + dueDate + "'";
            } else if(dueDate< currentDate && dueDate-lastPay >7){
                return "Your have not completed the payment due on '" + dueDate + "'";
            }
        }
        return null;
    }

}
