package com.example.chanaka.propertymanager.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.chanaka.propertymanager.R;

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

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.tenants_list_view,tenants);
        ListView list= (ListView) findViewById(R.id.lstTenants);
        list.setAdapter(adapter);

    }
}
