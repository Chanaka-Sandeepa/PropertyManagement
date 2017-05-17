package layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.chanaka.propertymanager.Controllers.Property_Handler;
import com.example.chanaka.propertymanager.Controllers.User_Handler;
import com.example.chanaka.propertymanager.Models.Tenant;
import com.example.chanaka.propertymanager.R;
import com.example.chanaka.propertymanager.Views.Home;


public class AddNewTenant extends Fragment {
    AutoCompleteTextView txtAutoApartment;
    String[] info = new String[3];
    EditText name;
    EditText address;
    EditText contact_no;
    View view;


    String[] apartments;
    Property_Handler pHan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_add_new_tenant,container,false);

        name= (EditText) view.findViewById(R.id.txtName);
        address= (EditText) view.findViewById(R.id.txtAddress);
        contact_no= (EditText) view.findViewById(R.id.txtTenantContact);

        ArrayAdapter<String> adapterApartment;
        pHan=new Property_Handler(getActivity());
        apartments = pHan.viewApartments();
        adapterApartment=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,apartments);
        txtAutoApartment=(AutoCompleteTextView) view.findViewById(R.id.txtAutoTenantAp);
        txtAutoApartment.setAdapter(adapterApartment);
        Button btnAdd=(Button)view.findViewById(R.id.btnAdd);

        if( getActivity().getIntent().getExtras() != null){
            fillDetails(getActivity().getIntent().getStringExtra("tenants") );
            btnAdd.setText("Update");
        }

        btnAdd.setOnClickListener(

                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked();
                    }
                }
        );



        return view;
    }

    private void fillDetails(String tenant) {
        Tenant t= new User_Handler(getActivity()).getTenantByName(tenant);
        contact_no= (EditText) view.findViewById(R.id.txtTenantContact);
        name=(EditText)view.findViewById(R.id.txtName);
        name.setText(t.getName());
        address.setText(t.getAddress());
        contact_no.setText(Integer.toString(t.getContactNo()));
        txtAutoApartment.setText(t.getApartment());
    }

    public void buttonClicked(){
        String sName=name.getText().toString();
        String sAddress=address.getText().toString();
        String sApartment=txtAutoApartment.getText().toString();
        String sContact=contact_no.getText().toString();
        String[] inputs={sName,sAddress,sApartment,sContact};

        if(checkInputValidity(inputs)) {
            info = new String[]{name.getText().toString(), address.getText().toString(), txtAutoApartment.getText().toString(),
                    "url"};

            new User_Handler(getActivity().getBaseContext()).createUser(info, Integer.parseInt(contact_no.getText().toString()));
            startActivity(new Intent(getActivity(), Home.class));
            getActivity().finish();
        }

    }

    public boolean checkInputValidity(String[] inputs){
        if(TextUtils.isEmpty(inputs[0])){
            name.setError("Enter a valid value...");
            return false;
        }else if(TextUtils.isEmpty(inputs[1])){
            address.setError("Enter a valid value...");
            return false;
        }else if(TextUtils.isEmpty(inputs[2])){
            txtAutoApartment.setError("Enter a valid value...");
            return false;
        }else if(TextUtils.isEmpty(inputs[3])){
            contact_no.setError("Enter a valid value...");
            return false;
        }
        return true;
    }

}
