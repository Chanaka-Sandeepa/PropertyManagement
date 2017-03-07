package com.example.chanaka.propertymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Add_BasicInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add__basic_info);

        Button btnNext=(Button)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked();
                    }
                }
        );
    }

    public void buttonClicked(){
        startActivity(new Intent(Add_BasicInfo.this,Add_RentalInfo.class));
    }
}
