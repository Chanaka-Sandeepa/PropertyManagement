package layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.chanaka.propertymanager.Controllers.Payment_Handler;
import com.example.chanaka.propertymanager.Controllers.User_Handler;
import com.example.chanaka.propertymanager.R;


public class AddNewTenant extends Fragment {
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    String[] info = new String[3];
    private String apartment;
    private EditText name;
    private EditText address;
    private EditText contact_no;
    private EditText image;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_new_tenant,container,false);

        name= (EditText) view.findViewById(R.id.txtName);
        address= (EditText) view.findViewById(R.id.txtAddress);
        contact_no= (EditText) view.findViewById(R.id.txtContact);
        image= (EditText) view.findViewById(R.id.txtImage);

        spinner =(Spinner)view.findViewById(R.id.spinner3);
        adapter= ArrayAdapter.createFromResource(this.getContext(),R.array.apartments ,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                apartment=parent.getItemAtPosition(pos).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

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
        info=new String[]{name.getText().toString(),address.getText().toString(),apartment,
                image.getText().toString()};

        new User_Handler(getActivity().getBaseContext()).createUser(info,Integer.parseInt(contact_no.getText().toString()),apartment);

    }


}
