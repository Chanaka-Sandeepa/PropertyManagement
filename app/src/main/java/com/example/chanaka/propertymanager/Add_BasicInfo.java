package com.example.chanaka.propertymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Add_BasicInfo extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    String[] info = new String[4];
    private String type;

    private EditText number;
    private EditText street;
    private EditText city;
    private EditText footage;
    private EditText desc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add__basic_info);
        number= (EditText) findViewById(R.id.txtNo);
        street= (EditText) findViewById(R.id.txtStreet);
        city= (EditText) findViewById(R.id.txtCity);
        footage= (EditText) findViewById(R.id.txtFootage);
        desc= (EditText) findViewById(R.id.txtDesc);



        spinner =(Spinner)findViewById(R.id.spinner);
        adapter=ArrayAdapter.createFromResource(this,R.array.types ,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                type=parent.getItemAtPosition(pos).toString();
                //Toast.makeText(getBaseContext(),parent.getItemAtPosition(pos).toString(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
        info=new String[]{number.getText().toString()+street.getText().toString()+city.getText().toString(),footage.getText().toString(),desc.getText().toString(),type};
        Intent intent =new Intent(Add_BasicInfo.this,Add_RentalInfo.class);
        intent.putExtra("info",info);
        startActivity(intent);
    }
}
