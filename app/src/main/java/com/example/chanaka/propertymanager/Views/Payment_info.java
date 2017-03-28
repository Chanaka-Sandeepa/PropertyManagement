package com.example.chanaka.propertymanager.Views;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.chanaka.propertymanager.R;
import com.example.chanaka.propertymanager.SectionPageAdapter;

import layout.AddNewTenant;
import layout.NewPayment;
import layout.PaymentHistory;
import layout.ViewTenantsInfo;

/**
 * Created by chanaka on 3/28/17.
 */

public class Payment_info extends AppCompatActivity{

    private SectionPageAdapter msectionPage;
    private ViewPager mviewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__tenants_info);

        msectionPage=new SectionPageAdapter(getSupportFragmentManager());
        mviewPager=(ViewPager) findViewById(R.id.container);
        setUpViewPager(mviewPager);

        TabLayout tabLayout= (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mviewPager);

    }
    public void setUpViewPager(ViewPager viewPager){
        SectionPageAdapter adapter=new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new PaymentHistory(),"Payment History");
        adapter.addFragment(new NewPayment(),"New Payment");
        viewPager.setAdapter(adapter);

    }
}
