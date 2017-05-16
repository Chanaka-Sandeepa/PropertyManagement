package layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.chanaka.propertymanager.Controllers.DatabaseConnector;
import com.example.chanaka.propertymanager.Controllers.Payment_Handler;
import com.example.chanaka.propertymanager.Controllers.Property_Handler;
import com.example.chanaka.propertymanager.Controllers.User_Handler;
import com.example.chanaka.propertymanager.Models.Tenant;
import com.example.chanaka.propertymanager.R;
import com.example.chanaka.propertymanager.Views.Home;

/**
 * Created by chanaka on 3/28/17.
 */

public class NewPayment extends Fragment {

    Property_Handler pHan;
    User_Handler uHan;

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    String[] stringinfo = new String[2];
    int[] intinfo = new int[2];
    private String type;
    String[] apartments;
    String[] tenants;

    private AutoCompleteTextView name;
    private AutoCompleteTextView apartment;
    private EditText amount;
    private EditText date;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_new_payment,container,false);
        amount= (EditText) view.findViewById(R.id.txtTenantAmount);
        date= (EditText) view.findViewById(R.id.txtTenantPayDate);
        date.setText(Home.getDate());

        spinner =(Spinner)view.findViewById(R.id.spinner2);
        adapter=ArrayAdapter.createFromResource(this.getContext(),R.array.payment_types ,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                type=parent.getItemAtPosition(pos).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        //setting adapter for apartment search
        ArrayAdapter<String> adapter;
        pHan=new Property_Handler(getActivity());
        apartments = pHan.viewApartments();
        adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,apartments);
        apartment=(AutoCompleteTextView)view.findViewById(R.id.txtAutoAp);
        apartment.setAdapter(adapter);

        //setting adapter for tenant search
        uHan=new User_Handler(getActivity());
        tenants = uHan.viewTenants();
        adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,tenants);
        name=(AutoCompleteTextView)view.findViewById(R.id.txtAutoName);
        name.setAdapter(adapter);

        Button btnAdd=(Button)view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(

                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked();
                    }
                }
        );



        return view;

    }
    public void buttonClicked(){

        DatabaseConnector d=DatabaseConnector.getInstance(getActivity());
        stringinfo=new String[]{type,date.getText().toString()};
        intinfo=new int[]{d.getTenantId(name.getText().toString()),d.getPropertyId(apartment.getText().toString())};

        new Payment_Handler(getActivity().getBaseContext()).createPayment(stringinfo,Float.parseFloat(amount.getText().toString()),intinfo);

    }
}
