package com.example.chanaka.propertymanager.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chanaka.propertymanager.R;

public class TenantHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_home);

        Button btnApartment=(Button)findViewById(R.id.btnApartment);
        btnApartment.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        apartmentButtonClicked();
                    }
                }
        );

        Button btnPayment=(Button)findViewById(R.id.btnPayment);
        btnPayment.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        paymentButtonClicked();
                    }
                }
        );

        Button btnContact=(Button)findViewById(R.id.btnContact);
        btnContact.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        contactButtonClicked();
                    }
                }
        );


    }

    public void apartmentButtonClicked(){
        startActivity(new Intent(TenantHome.this,AddProperty.class));
    }

    public void paymentButtonClicked(){
        startActivity(new Intent(TenantHome.this,Payment_info.class));
    }

    public void contactButtonClicked(){
        startActivity(new Intent(TenantHome.this,AddProperty.class));
    }
}
