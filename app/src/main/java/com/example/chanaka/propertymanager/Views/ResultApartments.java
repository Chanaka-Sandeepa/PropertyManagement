package com.example.chanaka.propertymanager.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.chanaka.propertymanager.Models.Property;
import com.example.chanaka.propertymanager.R;

import layout.ViewApartments;

public class ResultApartments extends AppCompatActivity {

    private String[] properties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewApartments.isSearch=true;
        properties=getIntent().getStringArrayExtra("properties");
        setContentView(R.layout.activity_result_apartments);

        Button btnBack=(Button)findViewById(R.id.btnBachToSearch);
        btnBack.setOnClickListener(

                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked();
                    }
                }
        );
    }

    public String[] getProperties() {
        return properties;
    }

    private void buttonClicked() {

        startActivity(new Intent(this,Search_Apartment.class));
    }

}
