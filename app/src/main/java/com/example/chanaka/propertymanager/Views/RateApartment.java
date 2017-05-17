package com.example.chanaka.propertymanager.Views;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.chanaka.propertymanager.Controllers.DatabaseConnector;
import com.example.chanaka.propertymanager.Controllers.SaveSharedPreferences;
import com.example.chanaka.propertymanager.R;

public class RateApartment extends AppCompatActivity {
    DatabaseConnector dbCon;
    private EditText txtReviews;
    private EditText txtRatings;
    private EditText txtRateAddress;
    private RatingBar ratingBar;
    Button btnRate;
    float rating;
    float reviews;
    String apartmentAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_apartment);
        dbCon=DatabaseConnector.getInstance(getBaseContext());
        apartmentAdd= dbCon.getUserApartment(SaveSharedPreferences.getUserName(Login.getCtx()));

        txtReviews=(EditText) findViewById(R.id.txtReviews);
        txtRatings=(EditText) findViewById(R.id.txtRating);
        txtRateAddress=(EditText) findViewById(R.id.txtRateAddress);
        txtRateAddress.setFocusable(false);
        txtReviews.setFocusable(false);
        txtRatings.setFocusable(false);
        ratingBar=(RatingBar) findViewById(R.id.ratingBar);
        btnRate=(Button) findViewById(R.id.btnSubmitRate);

        btnRate.setOnClickListener(

                new View.OnClickListener(){
                    public void onClick(View v){
                        rateButtonClicked();
                    }
                }
        );

        getApartmentRatings();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fbHome);
        fab.setImageResource(R.drawable.home);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RateApartment.this,TenantHome.class));

            }
        });

    }

    private void rateButtonClicked() {
        rateApartment();
        getApartmentRatings();
    }

    public void rateApartment(){
        dbCon=DatabaseConnector.getInstance(getBaseContext());
        rating=((rating*reviews)+ratingBar.getRating())/(reviews+1);
        dbCon.saveRatings(rating,reviews+1,apartmentAdd);
        SaveSharedPreferences.setIsRated(true);
    }

    public void getApartmentRatings(){
        txtRateAddress.setText(apartmentAdd);
        Float[] rate=dbCon.getRatings(apartmentAdd);
        if(rate[1].equals(null)){
            Toast.makeText(this,"No Ratings on This Apartment...!",Toast.LENGTH_LONG).show();
        }else {
            rating = rate[0];
            reviews = rate[1];
            txtRatings.setText(Float.toString(rating));
            txtReviews.setText(Float.toString(reviews));
        }
        if(SaveSharedPreferences.getIsRated()){
            ratingBar.setFocusable(false);
            btnRate.setEnabled(false);
        }

    }
}
