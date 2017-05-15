package com.example.chanaka.propertymanager.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chanaka.propertymanager.Controllers.DatabaseConnector;
import com.example.chanaka.propertymanager.Controllers.SaveSharedPreferences;
import com.example.chanaka.propertymanager.R;

public class Login extends AppCompatActivity {
    EditText txtUsername;
    EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(SaveSharedPreferences.getUserName(Login.this).length() != 0)
        {
            loginButtonClicked(SaveSharedPreferences.getUserName(Login.this),SaveSharedPreferences.getPassword(Login.this));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername= (EditText)findViewById(R.id.txtUsername);
        txtPassword= (EditText)findViewById(R.id.txtPassword);

        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        loginButtonClicked(txtUsername.getText().toString(),txtPassword.getText().toString());
                    }
                }
        );

        Button btnRegister = (Button)findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        registerButtonClicked();
                    }
                }
        );
    }

    public void loginButtonClicked(String username, String password){
        DatabaseConnector dbCon= DatabaseConnector.getInstance(getBaseContext());
        int type=dbCon.login(username,password);
        SaveSharedPreferences.setUserName(this.getBaseContext(),username);
        SaveSharedPreferences.setUserName(this.getBaseContext(),password);
        if(type==1) {
            startActivity(new Intent(Login.this, Home.class));
        }else if (type==2){
            startActivity(new Intent(Login.this, TenantHome.class));
        }else{
            Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
        }
    }

    public void registerButtonClicked(){
        startActivity(new Intent(Login.this,UserRegister.class));
    }
}
