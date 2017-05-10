package com.example.chanaka.propertymanager.Controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
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

    public Property_Handler(Context context) {
        this.context = context;
        dbCon=DatabaseConnector.getInstance(context);
    }

    //Create a property
    public void createProperty(String[] basicInf, Double[] rentalInf, String[] otherInf){
        property=new Property(basicInf[0],basicInf[3],basicInf[1],basicInf[2],rentalInf[0],
                rentalInf[1],otherInf[0],otherInf[1]);
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
}
