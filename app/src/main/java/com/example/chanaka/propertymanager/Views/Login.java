package com.example.chanaka.propertymanager.Views;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chanaka.propertymanager.Controllers.DatabaseConnector;
import com.example.chanaka.propertymanager.Controllers.SaveSharedPreferences;
import com.example.chanaka.propertymanager.Controllers.User_Handler;
import com.example.chanaka.propertymanager.R;

import layout.TenantPayment;
import layout.ViewApartments;

import static android.media.RingtoneManager.getDefaultUri;

public class Login extends AppCompatActivity {
    EditText txtUsername;
    EditText txtPassword;
    static Context loginCtx;
    NotificationCompat.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loginCtx=getBaseContext();
        builder= new NotificationCompat.Builder(this);
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

    public static Context getCtx() {
        return loginCtx;
    }

    public void loginButtonClicked(String username, String password){
        User_Handler uHan = new User_Handler(getBaseContext());
        DatabaseConnector dbCon= DatabaseConnector.getInstance(getBaseContext());
        int type=dbCon.login(username,password);
        SaveSharedPreferences.setUserName(this.getBaseContext(),username);
        SaveSharedPreferences.setUserName(this.getBaseContext(),password);
        if(type==1) {
            ViewApartments.isSearch=false;
            startActivity(new Intent(Login.this, Home.class));
            String alert=uHan.checkForNotifications(1);
            if(alert != null){
                showNotification(alert);
            }
        }else if (type==2){
            ViewApartments.isSearch=true;
            startActivity(new Intent(Login.this, TenantHome.class));
            String alert=uHan.checkForNotifications(2);
            if(alert != null){
                showNotification(alert);
            }
        }else{
            Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
        }
        this.finish();
    }

    public void registerButtonClicked(){
        startActivity(new Intent(Login.this,UserRegister.class));
    }

    public void showNotification(String alert){
        int requestID = (int) System.currentTimeMillis();
        Uri alarmSound = getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.alarm)
                        .setContentTitle("Payments")
                        .setContentText(alert);

        Intent resultIntent = new Intent(this, TenantPayment.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        requestID,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);
        mBuilder.setSound(alarmSound);

        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(001, mBuilder.build());

    }

}
