package com.example.chanaka.propertymanager.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.chanaka.propertymanager.R;

public class Home extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //Set listner to create button
        Button btnCreate=(Button)findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        createButtonClicked();
                    }
                }
        );

        //Set listner to view Tenants
        Button btnTenants=(Button)findViewById(R.id.btnTenant);
        btnTenants.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        tenantButtonClicked();
                    }
                }
        );

        //Set listner to view Payments
        Button btnPayment=(Button)findViewById(R.id.btnPayment);
        btnPayment.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        paymentButtonClicked();
                    }
                }
        );

        //Set listner to view alerts
        Button btnAllert=(Button)findViewById(R.id.btnAlerts);
        btnAllert.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        tenantButtonClicked();
                    }
                }
        );

    }

    //Start the activity of adding basic info
    public void createButtonClicked(){

        startActivity(new Intent(Home.this,Add_BasicInfo.class));
    }

    //Start the activity of view Tenants
    public void tenantButtonClicked(){

        startActivity(new Intent(Home.this,View_Tenants_info.class));

    }
    //Start the activity of view Payments
    public void paymentButtonClicked(){

        startActivity(new Intent(Home.this,Payment_info.class));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
