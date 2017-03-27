package com.example.chanaka.propertymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Add_RentalInfo extends AppCompatActivity {
    String[] info =new String[4];

    Propert_Handler propertyHan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__rental_info);
        propertyHan = new Propert_Handler();
        info=getIntent().getExtras().getStringArray("info");

        Button btnAdd=(Button)findViewById(R.id.btnNext);
        btnAdd.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked();
                    }
                }
        );
    }

    public void buttonClicked(){
        propertyHan.createProperty()
    }
}
