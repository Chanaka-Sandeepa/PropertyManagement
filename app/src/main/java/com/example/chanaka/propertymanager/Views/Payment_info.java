package com.example.chanaka.propertymanager.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.chanaka.propertymanager.R;
import com.example.chanaka.propertymanager.SectionPageAdapter;

import layout.NewPayment;
import layout.PaymentHistory;
import layout.TenantPayment;

/**
 * Created by chanaka on 3/28/17.
 */

public class Payment_info extends AppCompatActivity{

    private SectionPageAdapter msectionPage;
    private ViewPager mviewPager;
    private boolean isTenant=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_info);

        isTenant=getIntent().getBooleanExtra("isTenant",false);
        msectionPage=new SectionPageAdapter(getSupportFragmentManager());
        mviewPager=(ViewPager) findViewById(R.id.container3);
        setUpViewPager(mviewPager);

        TabLayout tabLayout= (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mviewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.back);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Payment_info.this,Home.class));

            }
        });

    }
    public void setUpViewPager(ViewPager viewPager){
        SectionPageAdapter adapter=new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new PaymentHistory(),"Payment History");
        if(isTenant) {
            adapter.addFragment(new TenantPayment(), "New Payment");
        }else{
            adapter.addFragment(new NewPayment(), "New Payment");
        }
        viewPager.setAdapter(adapter);

    }

    public void setTenant(boolean tenant) {
        isTenant = tenant;
    }
}
