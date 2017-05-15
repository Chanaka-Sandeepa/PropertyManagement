package com.example.chanaka.propertymanager.Views;

import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;

import com.example.chanaka.propertymanager.Controllers.DatabaseConnector;
import com.example.chanaka.propertymanager.Models.Property;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chanaka on 3/30/17.
 */
public class AddPropertyTest {

    @Test
    public void addNewProperty() throws Exception {
        DatabaseConnector dbCon=DatabaseConnector.getInstance(InstrumentationRegistry.getTargetContext());

        //create and add a dummy property to test
        Property property=new Property("aa","bb","cc","dd",100.00,100.00,"ee","ff");
        dbCon.addProperty(property);

        String property2=dbCon.getProperty(-1).getAddress();

        //check the results
        assertEquals(property.getAddress(),property2);
    }

}