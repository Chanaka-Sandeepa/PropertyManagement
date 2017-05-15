package com.example.chanaka.propertymanager.Views;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.chanaka.propertymanager.Controllers.SaveSharedPreferences;
import com.example.chanaka.propertymanager.R;

import static com.example.chanaka.propertymanager.R.drawable.home;
import static java.security.AccessController.getContext;

public class Home extends AppCompatActivity {

    public static boolean isTenant=false;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Context ctx=this.getBaseContext();


        //Set listner to create button
        Button btnCreate=(Button)findViewById(R.id.btnCreate);
        //set image to button
        Drawable imgCreate = ctx.getResources().getDrawable( R.drawable.home );
        imgCreate.setBounds( 0, 0, 120, 100 );
        btnCreate.setCompoundDrawables( null, imgCreate, null, null );
        btnCreate.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        createButtonClicked();
                    }
                }
        );

        //Set listner to view Tenants
        Button btnTenants=(Button)findViewById(R.id.btnTenant);
        //set image to button
        Drawable imgTenants = ctx.getResources().getDrawable( R.drawable.tenant );
        imgTenants.setBounds( 0, 0, 120, 100 );
        btnTenants.setCompoundDrawables( null, imgTenants, null, null );
        btnTenants.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        tenantButtonClicked();
                    }
                }
        );

        //Set listner to view Payments
        Button btnPayment=(Button)findViewById(R.id.btnPayment);
        //set image to button
        Drawable imgPay = ctx.getResources().getDrawable( R.drawable.money );
        imgPay.setBounds( 0, 0, 120, 100 );
        btnPayment.setCompoundDrawables( null, imgPay, null, null );
        btnPayment.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        paymentButtonClicked();
                    }
                }
        );

        //Set listner to view alerts
        Button btnAllert=(Button)findViewById(R.id.btnAlerts);
        //set image to button
        Drawable imgAlerts = ctx.getResources().getDrawable( R.drawable.alarm );
        imgAlerts.setBounds( 0, 0, 120, 100 );
        btnAllert.setCompoundDrawables( null, imgAlerts, null, null );
        btnAllert.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        alertsButtonClicked();
                    }
                }
        );

    }

    //Start the activity of adding basic info
    public void createButtonClicked(){

        startActivity(new Intent(Home.this,AddProperty.class));
    }

    //Start the activity of view Tenants
    public void tenantButtonClicked(){

        startActivity(new Intent(Home.this,View_Tenants_info.class));

    }
    //Start the activity of view Payments
    public void paymentButtonClicked(){

        startActivity(new Intent(Home.this,Payment_info.class));

    }

    //Start the activity of alerts
    public void alertsButtonClicked(){

        startActivity(new Intent(Home.this,AlertsInfo.class));

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
        if (id == R.id.action_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        SaveSharedPreferences.setUserName(Home.this,"");
        SaveSharedPreferences.setPassword(Home.this,"");
        startActivity(new Intent(Home.this,Login.class));
    }
}
