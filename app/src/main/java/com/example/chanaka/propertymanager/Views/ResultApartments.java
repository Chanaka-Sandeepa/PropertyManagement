package com.example.chanaka.propertymanager.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chanaka.propertymanager.Models.Property;
import com.example.chanaka.propertymanager.R;

import layout.ViewApartments;

public class ResultApartments extends AppCompatActivity {
    public String[] getProperties() {
        return properties;
    }

    private String[] properties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewApartments.isSearch=true;
        properties=getIntent().getStringArrayExtra("properties");
        setContentView(R.layout.activity_result_apartments);
    }


}
