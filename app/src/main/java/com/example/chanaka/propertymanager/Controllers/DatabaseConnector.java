package com.example.chanaka.propertymanager.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.chanaka.propertymanager.Models.Payment;
import com.example.chanaka.propertymanager.Models.Property;
import com.example.chanaka.propertymanager.Models.Tenant;

import java.io.Console;
import java.util.ArrayList;

/**
 * Created by chanaka on 3/29/17.
 */

public class DatabaseConnector extends SQLiteOpenHelper {
    private static final int db_version=1;
    public final static String dbName="propertyManagementDB";
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
    private final static String Key_Tenant_Contact="contact_number";
    private final static String Key_Tenant_image="image";

    //Tenants Payment Table
    private final static String Table_Payment_Details="paymentDetails";
    //Tenants Payment table columns
    private final static String Key_Payment_ID="id";
    private final static String Key_Payment_amount="amount";
    private final static String Key_Payment_Tenant="tenant_id";
    private final static String Key_Payment_Apartment="apartment";
    private final static String Key_Payment_Date="date";
    private final static String Key_Payment_Type="payment_type";

    //User Table
    private final static String Table_Users="userDetails";
    //User table columns
    private final static String Key_User_ID="id";
    private final static String Key_full_name="full_name";
    private final static String Key_Username="username";
    private final static String Key_User_Type="user_type";
    private final static String Key_Password="password";


    private static DatabaseConnector databaseConnector=null;

    public static DatabaseConnector getInstance(Context context){
        if(databaseConnector==null){
            databaseConnector=new DatabaseConnector(context);
        }
        return databaseConnector;
    }

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

        //create User table
        String Create_User_Table="CREATE TABLE "+Table_Users+"("
                +Key_User_ID+" INTEGER PRIMARY KEY,"
                +Key_full_name+" TEXT,"
                +Key_Username+" TEXT,"
                +Key_User_Type+" TEXT,"
                +Key_Password+" TEXT" +")";

        db.execSQL(Create_Apartment_Table);
        db.execSQL(Create_Tenants_Table);
         db.execSQL(Create_Payments_Table);
        db.execSQL(Create_User_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
    //save new property
    public void addProperty(Property property) {
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues values=new ContentValues();
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

    //get all the saved apartments from the database
    public ArrayList<String> viewAllApartments(){
        SQLiteDatabase db =this.getReadableDatabase();
        String query="select address from apartmentDetails";
        cursor=db.rawQuery(query,null);
        ArrayList<String> properties=new ArrayList<String>();
        while(cursor.moveToNext()){
            if (cursor.getString(cursor.getColumnIndex("address"))!=null)
                properties.add(cursor.getString(cursor.getColumnIndex("address")));
        }
        return properties;

    }

    //get all the saved apartments from the database
    public ArrayList<Property> getApartments(){
        SQLiteDatabase db =this.getReadableDatabase();
        String query="select * from apartmentDetails";
        cursor=db.rawQuery(query,null);
        ArrayList<Property> properties=new ArrayList<Property>();
        while(cursor.moveToNext()){
            if (cursor.getString(cursor.getColumnIndex("address"))!=null) {
                Property p=new Property(cursor.getString(cursor.getColumnIndex("address")),cursor.getString(cursor.getColumnIndex("type")),
                        cursor.getString(cursor.getColumnIndex("sq_footage")),cursor.getString(cursor.getColumnIndex("description")),
                        cursor.getInt(cursor.getColumnIndex("rental")),cursor.getInt(cursor.getColumnIndex("deposit")),
                        cursor.getString(cursor.getColumnIndex("available_date")),cursor.getString(cursor.getColumnIndex("image")));
                properties.add(p);
            }
        }
        return properties;

    }

    //get a specific apartemnt
    public Property getProperty(int i){
        SQLiteDatabase db =this.getReadableDatabase();
        String query="select * from apartmentDetails where id='"+i+"'";
        cursor=db.rawQuery(query,null);
        while(cursor.moveToNext()){
            if (cursor.getString(cursor.getColumnIndex("address"))!=null) {
                Property p = new Property(cursor.getString(cursor.getColumnIndex("address")), cursor.getString(cursor.getColumnIndex("type")),
                        cursor.getString(cursor.getColumnIndex("sq_footage")), cursor.getString(cursor.getColumnIndex("description")),
                        cursor.getInt(cursor.getColumnIndex("rental")), cursor.getInt(cursor.getColumnIndex("deposit")),
                        cursor.getString(cursor.getColumnIndex("available_date")), cursor.getString(cursor.getColumnIndex("image")));
                return p;
            }
        }
        return null;

    }

    //get nearby properties
    public ArrayList<Property> getNearbyProperties(String s){
        SQLiteDatabase db =this.getReadableDatabase();
        ArrayList<Property> properties=new ArrayList<>();
        String query="select * from apartmentDetails where address like '%"+s+"%'";
        cursor=db.rawQuery(query,null);
        while(cursor.moveToNext()){
            Log.e("aa","aa");
            if (cursor.getString(cursor.getColumnIndex("address"))!=null) {
                Property p = new Property(cursor.getString(cursor.getColumnIndex("address")), cursor.getString(cursor.getColumnIndex("type")),
                        cursor.getString(cursor.getColumnIndex("sq_footage")), cursor.getString(cursor.getColumnIndex("description")),
                        cursor.getInt(cursor.getColumnIndex("rental")), cursor.getInt(cursor.getColumnIndex("deposit")),
                        cursor.getString(cursor.getColumnIndex("available_date")), cursor.getString(cursor.getColumnIndex("image")));
                properties.add(p);
            }
        }
        return properties;

    }

    //delete a saved property
    public void removeProperty(int id){
        SQLiteDatabase db =this.getReadableDatabase();
        String query="delete * from apartmentDetails where id='"+id+"'";
        cursor=db.rawQuery(query,null);

    }

    //save new tenant
    public void addTenant(Tenant tenant) {
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Key_Tenant_Name,tenant.getName());
        values.put(Key_Tenant_Permanant_Address,tenant.getAddress());
        values.put(Key_Tenant_Apartment,tenant.getApartment());
        values.put(Key_Tenant_Contact,tenant.getContactNo());
        values.put(Key_Tenant_image,tenant.getImage());

        db.insert(Table_Tenant_Details,null,values);
        db.close();

    }

    //save new payment
    public void addPayment(Payment payment) {
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Key_Payment_amount,payment.getAmount());
        values.put(Key_Payment_Apartment,payment.getProperty());
        values.put(Key_Payment_Tenant,payment.getTenant());
        values.put(Key_Payment_Date,payment.getDate());
        values.put(Key_Payment_Type,payment.getType());

        db.insert(Table_Payment_Details,null,values);
        db.close();

    }

    //get all the saved payments
    public ArrayList<Payment> getPayments() {
        SQLiteDatabase db =this.getReadableDatabase();
        String query="select * from paymentDetails";
        cursor=db.rawQuery(query,null);
        ArrayList<Payment> payments=new ArrayList<Payment>();
        while(cursor.moveToNext()){
            if (cursor.getString(cursor.getColumnIndex("payment_type"))!=null) {
                Payment p=new Payment(cursor.getFloat(cursor.getColumnIndex("amount")),cursor.getString(cursor.getColumnIndex("payment_type")),
                        cursor.getInt(cursor.getColumnIndex("tenant_id")),cursor.getInt(cursor.getColumnIndex("apartment")),
                        cursor.getString(cursor.getColumnIndex("date")));
                payments.add(p);
            }
        }
        return payments;
    }

    //get all the saved tenants
    public ArrayList<Tenant> getTenants() {
        SQLiteDatabase db =this.getReadableDatabase();
        String query="select * from tenantsDetails";
        cursor=db.rawQuery(query,null);
        ArrayList<Tenant> tenants=new ArrayList<Tenant>();
        while(cursor.moveToNext()){
            if (cursor.getString(cursor.getColumnIndex("name"))!=null) {
                Tenant t=new Tenant(cursor.getString(cursor.getColumnIndex("name")),cursor.getString(cursor.getColumnIndex("permanant_address")),
                        cursor.getInt(cursor.getColumnIndex("contact_number")),cursor.getString(cursor.getColumnIndex("apartment")),
                        cursor.getString(cursor.getColumnIndex("image")));
                tenants.add(t);
            }
        }
        Log.e("aa","return tenant");
        return tenants;
    }

    //get a specific tenant
    public Tenant getTenant(int i){
        String property="";
        SQLiteDatabase db =this.getReadableDatabase();
        String query="select * from tenantsDetails where id='"+i+"'";
        cursor=db.rawQuery(query,null);
        while(cursor.moveToNext()){
            if (cursor.getString(cursor.getColumnIndex("name"))!=null) {
                Tenant t=new Tenant(cursor.getString(cursor.getColumnIndex("name")),cursor.getString(cursor.getColumnIndex("permanant_address")),
                        cursor.getInt(cursor.getColumnIndex("contact_number")),cursor.getString(cursor.getColumnIndex("apartment")),
                        cursor.getString(cursor.getColumnIndex("image")));
                return t;
            }
        }
        return null;

    }

    public void addUser(String name, String uname, String type, String password) {
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Key_full_name,name);
        values.put(Key_Username,uname);
        values.put(Key_User_Type,type);
        values.put(Key_Password,password);

        db.insert(Table_Users,null,values);
        db.close();
    }

    public int login(String s, String pw) {
        int i=0;
        SQLiteDatabase db =this.getReadableDatabase();
        String query="select user_type from userDetails where username='"+s+"' and password='"+pw+"'";
        cursor=db.rawQuery(query,null);
        while(cursor.moveToNext()){
            if (cursor.getString(cursor.getColumnIndex("user_type")).equals("Landloard")) {
                return 1;
            }else if (cursor.getString(cursor.getColumnIndex("user_type")).equals("Tenant")){
                return 2;
            }
        }
        return 0;
    }
}
