package com.example.chanaka.propertymanager.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.chanaka.propertymanager.Controllers.Propert_Handler;
import com.example.chanaka.propertymanager.R;

public class Add_RentalInfo extends AppCompatActivity {
    String[] basic_info =new String[4];
    Double[] rental_info =new Double[2];
    String[] other_info = new String[2];
    private EditText rental;
    private EditText deposit;
    private EditText date;
    private EditText image;

    Propert_Handler propertyHan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__rental_info);
        propertyHan = new Propert_Handler(getApplicationContext());

        //get inforamtion from the previous basic info activity
        basic_info=getIntent().getExtras().getStringArray("info");

        rental= (EditText) findViewById(R.id.txtRental);
        deposit= (EditText) findViewById(R.id.txtDeposit);
        date= (EditText) findViewById(R.id.txtDate);
        image= (EditText) findViewById(R.id.txtImage);

        Button btnAdd=(Button)findViewById(R.id.addBtn);
        btnAdd.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked();
                    }
                }
        );
    }

    public void buttonClicked(){
        rental_info=new Double[]{Double.parseDouble(rental.getText().toString()),Double.parseDouble(deposit.getText().toString())};
        other_info=new String[]{date.getText().toString(),image.getText().toString()};

        propertyHan.createProperty(basic_info,rental_info,other_info);
    }
}
