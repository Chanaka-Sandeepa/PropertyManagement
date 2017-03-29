package com.example.chanaka.propertymanager.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.chanaka.propertymanager.Models.Payment;
import com.example.chanaka.propertymanager.Models.Property;
import com.example.chanaka.propertymanager.Models.Tenant;

import java.util.ArrayList;

/**
 * Created by chanaka on 3/29/17.
 */

public class DatabaseConnector extends SQLiteOpenHelper {
    private static final int db_version=1;
    public final static String dbName="propertyManagerDB";
    Cursor cursor;

    //Apartments Table
    private final static String Table_Apartment_Details="apartmentDetails";
    //Apartment table columns
    private final static String Key_Apartment_ID="id";
    private final static String Key_Apartment_address="address";
    private final static String Key_Apartment_type="type";
    private final static String Key_sq_footage="sq_footage";
    private final static String Key_Apartment_Desc="description";
    private final static String Key_Rental="rental";
    private final static String Key_Deposit="deposit";
    private final static String Key_AvailableDate="available_date";
    private final static String Key_Apartment_image="image";

    //Tenants Table
    private final static String Table_Tenant_Details="tenantsDetails";
    //Tenants table columns
    private final static String Key_Tenant_ID="id";
    private final static String Key_Tenant_Name="name";
    private final static String Key_Tenant_Permanant_Address="permanant_address";
    private final static String Key_Tenant_Apartment="apartment";
    private final static String Key_Tenant_Contact="contact number";
    private final static String Key_Tenant_image="image";

    //Tenants Payment Table
    private final static String Table_Payment_Details="apartmentDetails";
    //Tenants Payment table columns
    private final static String Key_Payment_ID="id";
    private final static String Key_Payment_amount="amount";
    private final static String Key_Payment_Tenant="tenant_id";
    private final static String Key_Payment_Apartment="apartment";
    private final static String Key_Payment_Date="date";
    private final static String Key_Payment_Type="payment_type";



    public DatabaseConnector(Context context) {
        super(context, dbName, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create Apartments table
        String Create_Apartment_Table="CREATE TABLE "+Table_Apartment_Details+"("
                +Key_Apartment_ID+" INTEGER PRIMARY KEY,"
                +Key_Apartment_address+" TEXT,"
                +Key_Apartment_type+" TEXT,"
                +Key_sq_footage+" INTEGER,"
                +Key_Apartment_Desc+" TEXT,"
                +Key_Rental+" INTEGER,"
                +Key_Deposit+" INTEGER,"
                +Key_AvailableDate+" TEXT,"
                +Key_Apartment_image+" TEXT"+")";

        //create Tenants table
        String Create_Tenants_Table="CREATE TABLE "+Table_Tenant_Details+"("
                +Key_Tenant_ID+" INTEGER PRIMARY KEY,"
                +Key_Tenant_Name+" TEXT,"
                +Key_Tenant_Permanant_Address+" TEXT,"
                +Key_Tenant_Apartment+" TEXT,"
                +Key_Tenant_Contact+" INTEGER,"
                +Key_Tenant_image+" TEXT" +")";

        //create Payments table
        String Create_Payments_Table="CREATE TABLE "+Table_Payment_Details+"("
                +Key_Payment_ID+" INTEGER PRIMARY KEY,"
                +Key_Payment_amount+" FLOAT,"
                +Key_Payment_Tenant+" INTEGER,"
                +Key_Payment_Apartment+" INTEGER,"
                +Key_Payment_Date+" TEXT,"
                +Key_Payment_Type+" TEXT" +")";

        db.execSQL(Create_Apartment_Table);
        db.execSQL(Create_Tenants_Table);
        db.execSQL(Create_Payments_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
    //save new property
    public void addProperty(Property property) {
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Key_Apartment_ID,1);
        values.put(Key_Apartment_address,property.getAddress());
        values.put(Key_Apartment_type,property.getPropertyType());
        values.put(Key_sq_footage,property.getSqFootage());
        values.put(Key_Apartment_Desc,property.getDesc());
        values.put(Key_Rental,property.getRental());
        values.put(Key_Deposit,property.getDeposit());
        values.put(Key_Apartment_image,property.getImage());

        db.insert(Table_Apartment_Details,null,values);
        db.close();

    }

    //save new tenant
    public void addTenant(Tenant tenant) {
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Key_Tenant_ID,1);
        values.put(Key_Tenant_Name,tenant.getName());
        values.put(Key_Tenant_Permanant_Address,tenant.getAddress());
        values.put(Key_Tenant_Apartment,tenant.getApartment());
        values.put(Key_Tenant_Contact,tenant.getContactNo());
        values.put(Key_Tenant_image,tenant.getImage());

        db.insert(Table_Apartment_Details,null,values);
        db.close();

    }
    //retrieve apartments
    public Cursor getApartments(SQLiteDatabase db){
        Cursor cursor;
        String[] projections = {Key_Apartment_address};
        cursor=db.query(Table_Apartment_Details,projections,null,null,null,null,null);
        return cursor;
    }

    public ArrayList<String> viewAllApartments(){
        SQLiteDatabase db =this.getReadableDatabase();
        ArrayList<String> properties=new ArrayList<String>();
        cursor=getApartments(db);
        if(cursor.moveToFirst()){
            do{
                String address;
                address=cursor.getString(1);
                properties.add(address);
            }while (cursor.moveToNext());
        }
        return properties;

    }

    //save new payment
    public void addPayment(Payment payment) {
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Key_Payment_ID,2);
        values.put(Key_Payment_amount,payment.getAmount());
        values.put(Key_Payment_Apartment,payment.getProperty());
        values.put(Key_Payment_Tenant,payment.getTenant());
        values.put(Key_Payment_Date,payment.getDate());
        values.put(Key_Payment_Type,payment.getType());

        db.insert(Table_Apartment_Details,null,values);
        db.close();

    }
}
