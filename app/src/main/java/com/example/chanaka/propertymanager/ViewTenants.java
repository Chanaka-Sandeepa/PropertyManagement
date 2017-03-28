package com.example.chanaka.propertymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewTenants extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tenants);

        populateList();
    }

    //generate items into the list
    public void populateList(){
        //dummy tenants to display
        String[] tenants = {"Jhon","Sam","Max","Jim"};

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.activity_view_tenants,tenants);
        ListView list= (ListView) findViewById(R.id.lstTenants);
        list.setAdapter(adapter);

    }
}
