package layout;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.example.chanaka.propertymanager.R;

/**
 * Created by chanaka on 5/16/17.
 */

public class TenantPayment extends android.support.v4.app.Fragment {

    AutoCompleteTextView txtAutoApartment;
    Spinner spinnerType;
    EditText txtTenantAmount;
    EditText txtTenantPayDay;
    ArrayAdapter<CharSequence> adapter;
    private String payType;

    DatabaseConnector dbCon;

    String[] apartments;
    Property_Handler pHan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tenant_payment,container,false);
        dbCon=DatabaseConnector.getInstance(getActivity());
        txtAutoApartment= (AutoCompleteTextView) view.findViewById(R.id.txtAutoApartment);
        spinnerType= (Spinner) view.findViewById(R.id.spinnerType);
        txtTenantAmount= (EditText) view.findViewById(R.id.txtTenantAmount);
        txtTenantPayDay =(EditText) view.findViewById(R.id.txtTenantPayDay);

        adapter= ArrayAdapter.createFromResource(getActivity(),R.array.payment_types ,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                payType=parent.getItemAtPosition(pos).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //set adapter to apartment text
        ArrayAdapter<String> adapterApartment;
        pHan=new Property_Handler(getActivity());
        apartments = pHan.viewApartments();
        adapterApartment=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,apartments);
        txtAutoApartment=(AutoCompleteTextView) view.findViewById(R.id.txtAutoApartment);
        txtAutoApartment.setAdapter(adapterApartment);

        Button btnTenantPayAdd=(Button)view.findViewById(R.id.btnTenantPayAdd);
        btnTenantPayAdd.setOnClickListener(

                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked();
                    }
                }
        );



        return view;

    }
    public void buttonClicked(){
        String[] stringinfo=new String[]{payType,txtTenantPayDay.getText().toString()};
        int[] intinfo=new int[]{1,dbCon.getPropertyId(txtAutoApartment.getText().toString())};

        new Payment_Handler(getActivity().getBaseContext()).createPayment(stringinfo,Float.parseFloat(txtTenantAmount.getText().toString()),intinfo);

    }

}
