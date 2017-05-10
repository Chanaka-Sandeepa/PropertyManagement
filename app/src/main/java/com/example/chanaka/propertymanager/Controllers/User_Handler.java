package com.example.chanaka.propertymanager.Controllers;

import android.content.Context;
import android.widget.Toast;

import com.example.chanaka.propertymanager.Models.Payment;
import com.example.chanaka.propertymanager.Models.Tenant;

import java.util.ArrayList;

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
    public void createUser(String[] info,int contact,String apartment){
        tenant=new Tenant(info[0], info[1], contact, apartment,info[2]);
        dbCon.addTenant(tenant);

        Toast.makeText(context, "Tenant Created Successfully",Toast.LENGTH_LONG).show();
    }

    //get the list of saved tenants
    public String[] viewTenants(){
        ArrayList<Tenant> a=dbCon.getTenants();
        String[] tenants=new String[a.size()];
        String[] sPayments=new String[a.size()];
        for (int i=0;i<a.size();i++){
            tenants[i]=a.get(i).getName();
        }
        for (int i=0;  i<tenants.length;  i++) {
            sPayments[i] = "" + tenants[i];
        }

        return tenants;

    }


}
