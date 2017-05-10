package com.example.chanaka.propertymanager.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chanaka.propertymanager.R;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username= (EditText)findViewById(R.id.txtUsername);
        final EditText password= (EditText)findViewById(R.id.txtPassword);

        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        loginButtonClicked(username.getText().toString(),password.getText().toString());
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
        if(username.equals("admin") && password.equals("admin")){
            startActivity(new Intent(Login.this,Home.class));
        }else{
            Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
        }
    }

    public void registerButtonClicked(){
        startActivity(new Intent(Login.this,UserRegister.class));
    }
}
