package com.example.chanaka.propertymanager.Views;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chanaka.propertymanager.Controllers.DatabaseConnector;
import com.example.chanaka.propertymanager.Controllers.Property_Handler;
import com.example.chanaka.propertymanager.Controllers.SaveSharedPreferences;
import com.example.chanaka.propertymanager.Models.Property;
import com.example.chanaka.propertymanager.R;

public class MyApartment extends AppCompatActivity {
    private AutoCompleteTextView txtAutoSearch;
    private EditText myAddress;
    private EditText myApartmentType;
    private EditText myFootage;
    private EditText myRental;
    private EditText myDeposit;
    private EditText Desc;

    AlertDialog alertChange;

    DatabaseConnector dbCon;

    String[] apartments;
    Property_Handler pHan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_apartment);
        dbCon=DatabaseConnector.getInstance(getBaseContext());

        myAddress=(EditText) findViewById(R.id.txtMyAddress);
        myApartmentType=(EditText) findViewById(R.id.txtMyApartmentType);
        myFootage=(EditText) findViewById(R.id.txtMyFootage);
        myRental=(EditText) findViewById(R.id.txtMyRental);
        myDeposit=(EditText) findViewById(R.id.txtMyDeposit);
        Desc=(EditText) findViewById(R.id.txtMyDesc);

        ArrayAdapter<String> adapter;
        pHan=new Property_Handler(getBaseContext());
        apartments = pHan.viewApartments();
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,apartments);
        txtAutoSearch=(AutoCompleteTextView)findViewById(R.id.txtAutoSearch);
        txtAutoSearch.setAdapter(adapter);

        if(checkApartment().equals(null)){

        }else if (getIntent().getExtras() != null) {
            fillInformation(getIntent().getStringArrayExtra("searchRes")[1]);
        }else{
            fillInformation(checkApartment());
        }

        txtAutoSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                fillInformation(txtAutoSearch.getText().toString());

            }
        });

        Button btnSaveApartment=(Button)findViewById(R.id.btnSaveApartment);
        btnSaveApartment.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        saveButtonClicked();
                    }
                }
        );

        createAlert();
        setInputsDisable();
    }

    private void saveButtonClicked() {
        if(checkApartment().equals(null)){

        }else {
            alertChange.show();
        }
    }

    private void fillInformation(String s) {
        Property p=dbCon.getPropertyByAddress(s);
        myAddress.setText(p.getAddress());
        myApartmentType.setText(p.getPropertyType());
        myFootage.setText(p.getSqFootage());
        myRental.setText(Double.toString( p.getRental()));
        myDeposit.setText(Double.toString( p.getDeposit()));
        Desc.setText(p.getDesc());
    }

    public String checkApartment(){
        return dbCon.getUserApartment(SaveSharedPreferences.getUserName(Login.getCtx()));
    }

    public void createAlert(){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Do you want to change your apartment?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dbCon.setApartment(myAddress.getText().toString());
                        Toast.makeText(getBaseContext(),"Apartment assigned succeffully...!",Toast.LENGTH_LONG).show();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        alertChange= builder1.create();

    }

    public void setInputsDisable(){
        myAddress.setFocusable(false);
        myApartmentType.setFocusable(false);
        myFootage.setFocusable(false);
        myRental.setFocusable(false);
        myDeposit.setFocusable(false);
        Desc.setFocusable(false);
    }
}
