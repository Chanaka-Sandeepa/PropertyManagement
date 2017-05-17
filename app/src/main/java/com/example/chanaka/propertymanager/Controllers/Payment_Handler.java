package com.example.chanaka.propertymanager.Controllers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.chanaka.propertymanager.Models.Payment;
import com.example.chanaka.propertymanager.Models.Property;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by chanaka on 3/27/17.
 */

public class Payment_Handler {
    Payment payment;
    Context context;
    DatabaseConnector dbCon;

    public Payment_Handler(Context context) {
        this.context = context;
        dbCon=DatabaseConnector.getInstance(context);
    }

    //Create a payment
    public void createPayment(String[] stringinfo,float amount,int[] intinfo){
        payment=new Payment(amount, stringinfo[0], intinfo[0], intinfo[1], stringinfo[1]);
        dbCon.addPayment(payment);

        Toast.makeText(context, "Payment Created Successfully",Toast.LENGTH_LONG).show();
    }

    //get the list of saved payments
    public String[] viewPayments(){
        ArrayList<Payment> a=dbCon.getPayments();
        String[] payments=new String[a.size()];
        String[] sPayments=new String[a.size()];
        for (int i=0;i<a.size();i++){
            payments[i]= dbCon.getTenant(a.get(i).getTenant()).getName()+"      "+a.get(i).getAmount()+".00      "+a.get(i).getDate();
        }
        for (int i=0;  i<payments.length;  i++) {
            sPayments[i] = "" + payments[i];
        }

        return payments;

    }

    public Payment getPayment(int id){
        ArrayList<Payment> a=dbCon.getPayments();

        for(int i=0;i<a.size();i++){
            if(id==a.get(i).getId()){
                return a.get(i);
            }
        }
        return null;
    }

}
