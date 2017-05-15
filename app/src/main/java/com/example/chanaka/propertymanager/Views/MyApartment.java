package com.example.chanaka.propertymanager.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.chanaka.propertymanager.Controllers.Property_Handler;
import com.example.chanaka.propertymanager.R;

public class MyApartment extends AppCompatActivity {
    private AutoCompleteTextView txtAutoSearch;
    private EditText myAddress;
    private EditText myApartmentType;
    private EditText myFootage;
    private EditText myRental;
    private EditText myDeposit;
    private EditText Desc;

    String[] apartments;
    Property_Handler pHan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_apartment);

        ArrayAdapter<String> adapter;
        pHan=new Property_Handler(getBaseContext());
        apartments = pHan.viewApartments();
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,apartments);
        txtAutoSearch=(AutoCompleteTextView)findViewById(R.id.txtAutoSearch);
        txtAutoSearch.setAdapter(adapter);


    }
}
