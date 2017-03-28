package com.example.chanaka.propertymanager.Controllers;

import android.content.Context;
import android.widget.Toast;

import com.example.chanaka.propertymanager.Models.Property;

/**
 * Created by chanaka on 3/27/17.
 */

public class Propert_Handler {
    Property property;
    Context context;

    public Propert_Handler(Context context) {
        this.context = context;
    }

    public void createProperty(String[] basicInf, Double[] rentalInf, String[] otherInf){
        property=new Property(basicInf[0],basicInf[3],basicInf[1],basicInf[2],rentalInf[0],
                rentalInf[1],otherInf[0],otherInf[1]);

        Toast.makeText(context, property.getDesc(),Toast.LENGTH_LONG).show();
    }
}
