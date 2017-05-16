package com.example.chanaka.propertymanager.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.chanaka.propertymanager.Controllers.Property_Handler;
import com.example.chanaka.propertymanager.Controllers.SaveSharedPreferences;
import com.example.chanaka.propertymanager.Controllers.User_Handler;
import com.example.chanaka.propertymanager.Models.Tenant;
import com.example.chanaka.propertymanager.R;

import static com.example.chanaka.propertymanager.R.array.apartments;

public class TenantHome extends AppCompatActivity {

    private User_Handler uHan;
    ListView listView;
    ListAdapter adapter;

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

        Button btnRate=(Button)findViewById(R.id.btnRate);
        btnRate.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        rateButtonClicked();
                    }
                }
        );

        Button btnSearch=(Button)findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        searchButtonClicked();
                    }
                }
        );

        uHan=new User_Handler(getBaseContext());
        listView=(ListView)findViewById(R.id.lstAlerts);
        String alerts[]=uHan.getNotifications();
        adapter=new ArrayAdapter<String>(this.getApplicationContext(),android.R.layout.simple_list_item_1,alerts);

        listView.setAdapter(adapter);


    }

    public void apartmentButtonClicked(){
        startActivity(new Intent(TenantHome.this,MyApartment.class));
    }

    public void searchButtonClicked(){
        startActivity(new Intent(TenantHome.this,Search_Apartment.class));
    }

    public void paymentButtonClicked(){
        Intent i=new Intent(TenantHome.this,Payment_info.class);
        i.putExtra("isTenant",true);
        startActivity(i);
    }

    public void rateButtonClicked(){
        startActivity(new Intent(TenantHome.this,RateApartment.class));
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
        if (id ==R.id.action_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        SaveSharedPreferences.setUserName(TenantHome.this,"");
        SaveSharedPreferences.setPassword(TenantHome.this,"");
        SaveSharedPreferences.setIsRated(false);
        startActivity(new Intent(TenantHome.this,Login.class));
    }
}
