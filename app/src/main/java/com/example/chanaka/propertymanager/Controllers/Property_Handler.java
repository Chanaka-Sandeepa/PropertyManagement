package com.example.chanaka.propertymanager.Controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.chanaka.propertymanager.Models.Property;

import java.util.ArrayList;

/**
 * Created by chanaka on 3/27/17.
 */

public class Property_Handler {
    Property property;
    Context context;
    DatabaseConnector dbCon;
    SQLiteDatabase db;
    Cursor cursor;

    public Property_Handler(Context context) {
        this.context = context;
        //dbCon=new DatabaseConnector(context);
    }

    public void createProperty(String[] basicInf, Double[] rentalInf, String[] otherInf){
        property=new Property(basicInf[0],basicInf[3],basicInf[1],basicInf[2],rentalInf[0],
                rentalInf[1],otherInf[0],otherInf[1]);
        dbCon=new DatabaseConnector(context);
        dbCon.addProperty(property);


        Toast.makeText(context, property.getDesc(),Toast.LENGTH_LONG).show();
    }
    public String[] viewApartments(){
        dbCon=new DatabaseConnector(context);
        ArrayList<String> a=dbCon.viewAllApartments();
        String[] apartments=a.toArray(new String[a.size()]);
        return apartments;

    }
}
