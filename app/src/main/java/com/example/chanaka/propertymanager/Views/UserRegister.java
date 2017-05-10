package com.example.chanaka.propertymanager.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.chanaka.propertymanager.Controllers.DatabaseConnector;
import com.example.chanaka.propertymanager.R;

public class UserRegister extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    private String type;
    private EditText firstName;
    private EditText sirName;
    private EditText username;
    private EditText password;
    private EditText confPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        firstName= (EditText)findViewById(R.id.txtFirstName);
        sirName= (EditText)findViewById(R.id.txtSirName);
        username= (EditText)findViewById(R.id.txtUsername);
        password= (EditText)findViewById(R.id.txtUserPassword);
        confPassword= (EditText)findViewById(R.id.txtConfirmPassword);

        spinner =(Spinner)findViewById(R.id.spinner3);
        adapter= ArrayAdapter.createFromResource(this.getBaseContext(),R.array.usertypes ,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                type=parent.getItemAtPosition(pos).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btnUserRegister=(Button)findViewById(R.id.btnUserRegister);
        btnUserRegister.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked();
                    }
                }
        );
    }

    private void buttonClicked() {
        DatabaseConnector dbCon= DatabaseConnector.getInstance(getBaseContext());
        if(password.getText().toString().equals(confPassword.getText().toString())){
            dbCon.addUser(firstName.getText().toString()+sirName.getText().toString(),username.getText().toString(),type,password.getText().toString());
        }else{
            Toast.makeText(getBaseContext(),"Password not matching...!",Toast.LENGTH_LONG).show();
        }
    }
}
