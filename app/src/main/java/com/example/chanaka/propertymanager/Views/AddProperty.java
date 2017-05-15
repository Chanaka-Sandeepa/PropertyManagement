package com.example.chanaka.propertymanager.Views;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import android.view.View;
import android.widget.TabHost;

import com.example.chanaka.propertymanager.Controllers.Property_Handler;
import com.example.chanaka.propertymanager.R;
import com.example.chanaka.propertymanager.SectionPageAdapter;

import layout.AddBasicInfo;
import layout.AddRenatalInfo;

public class AddProperty extends AppCompatActivity  implements AddBasicInfo.AddBasicInfoListener, AddRenatalInfo.AddRentalInfoListener{
    private SectionPageAdapter msectionPage;
    private ViewPager mviewPager;
    public static boolean isTenant= false;
    TabHost tabHost;
    Property_Handler pHan;
    String[] basic_info = new String[4];
    Double[] rental_info =new Double[2];
    String[] other_info = new String[2];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_property);

        msectionPage=new SectionPageAdapter(getSupportFragmentManager());
        mviewPager=(ViewPager) findViewById(R.id.container2);
        setUpViewPager(mviewPager);
        tabHost=new TabHost(getBaseContext());

        TabLayout tabLayout= (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mviewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.back);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddProperty.this,Home.class));

            }
        });


    }
    public void setUpViewPager(ViewPager viewPager){
        SectionPageAdapter adapter=new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new AddBasicInfo(),"Basic Information");
        adapter.addFragment(new AddRenatalInfo(),"Rental Inforamtion");
        viewPager.setAdapter(adapter);

    }
    public void setTabs(String[] info){
        msectionPage=new SectionPageAdapter(getSupportFragmentManager());
        mviewPager=(ViewPager) findViewById(R.id.container2);
        mviewPager.setCurrentItem(2,true);
        this.basic_info=info;
    }

    public void addNewProperty(Double[] rental_info, String[] other_info) {
        pHan = new Property_Handler(this);
        pHan.createProperty(basic_info,rental_info,other_info);
    }

    @Override
    public void passInfo(String[] info) {
        setTabs(info);
    }

    @Override
    public void addNewPropertyFragment(Double[] rental_info, String[] other_info) {
        addNewProperty(rental_info,other_info);
    }

    @Override
    public void goToHome() {
        startActivity(new Intent(AddProperty.this,Home.class));
    }


}
