package com.example.chanaka.propertymanager.Views;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.example.chanaka.propertymanager.R;
import com.example.chanaka.propertymanager.SectionPageAdapter;

import layout.AddNewTenant;
import layout.ViewTenantsInfo;

public class View_Tenants_info extends AppCompatActivity {

    private SectionPageAdapter msectionPage;
    private ViewPager mviewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__tenants_info);

        msectionPage=new SectionPageAdapter(getSupportFragmentManager());
        mviewPager=(ViewPager) findViewById(R.id.container2);
        setUpViewPager(mviewPager);

        TabLayout tabLayout= (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mviewPager);

    }
    public void setUpViewPager(ViewPager viewPager){
        SectionPageAdapter adapter=new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ViewTenantsInfo(),"View Tenants");
        adapter.addFragment(new AddNewTenant(),"New Tenant");
        viewPager.setAdapter(adapter);

    }


}
