package com.example.chanaka.propertymanager.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chanaka.propertymanager.R;

public class AlertsInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView listItems=(ListView)findViewById(R.id.lstAlerts);
        TextView textView= new TextView(this.getBaseContext());
        textView.setText("Alerts");
        listItems.addHeaderView(textView);
        populateList(listItems);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.back);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AlertsInfo.this,Home.class));

            }
        });
    }

    //generate items into the list
    public void populateList(ListView lv){
        //dummy payments to display
        String[] alerts = {"Rentals to be collected - Jhone  3000.00",
                "Rentals to be collected - Sam  2500.00",
                "Total Payments collected - 7800.00"
                };

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this.getBaseContext(),android.R.layout.simple_list_item_1,alerts);
        lv.setAdapter(adapter);

    }

}
