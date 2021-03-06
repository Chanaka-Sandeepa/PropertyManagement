package com.example.chanaka.propertymanager.Controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.chanaka.propertymanager.Models.Property;
import com.example.chanaka.propertymanager.Views.Login;

import java.util.ArrayList;

/**
 * Created by chanaka on 3/27/17.
 */

public class Property_Handler {
    Property property;
    Context context;
    DatabaseConnector dbCon;

    public Property_Handler(Context context) {
        this.context = context;
        dbCon=DatabaseConnector.getInstance(context);
    }

    //Create a property
    public void createProperty(String[] basicInf, Double[] rentalInf, String[] otherInf){
        property=new Property(basicInf[0],basicInf[3],basicInf[1],basicInf[2],rentalInf[0],
                rentalInf[1],otherInf[0],otherInf[2]);
        property.setDueDate(otherInf[1]);
        property.setOwner(SaveSharedPreferences.getUserName(Login.getCtx()));
        dbCon.addProperty(property);

        Toast.makeText(context, "Property Created Successfully",Toast.LENGTH_LONG).show();
    }

    //get the list of saved apartments
    public String[] viewApartments(){
        ArrayList<Property> a=dbCon.getApartments();
        String[] apartments=new String[a.size()];
        for (int i=0;i<a.size();i++){
            apartments[i]=a.get(i).getAddress();
        }
        return apartments;

    }

    //get all the apartments in database
    public String[] viewAllApartments(){
        ArrayList<Property> a=dbCon.getAllApartments();
        String[] apartments=new String[a.size()];
        for (int i=0;i<a.size();i++){
            apartments[i]=a.get(i).getAddress();
        }
        return apartments;

    }

    public void deleteProperty(String address){
        dbCon.removeRecord("apartmentDetails" , "address=?", new String[]{address});
    }

    public Property[] sortApartments(Property[] p){

        int n = p.length;
        Property temp = null;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (p[j - 1].getRating() < p[j].getRating()) {
                    temp = p[j - 1];
                    p[j - 1]= p[j];
                    p[j] = temp;
                }

            }
        }
        return p;
    }
}
