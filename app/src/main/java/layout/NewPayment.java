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
import com.example.chanaka.propertymanager.R;

/**
 * Created by chanaka on 3/28/17.
 */

public class NewPayment extends Fragment {

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    String[] stringinfo = new String[2];
    int[] intinfo = new int[2];
    private String type;

    private EditText name;
    private EditText amount;
    private EditText date;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_new_payment,container,false);
        name= (EditText) view.findViewById(R.id.txtName);
        amount= (EditText) view.findViewById(R.id.txtAddress);
        date= (EditText) view.findViewById(R.id.txtContact);

        spinner =(Spinner)view.findViewById(R.id.spinner3);
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
        stringinfo=new String[]{type,date.getText().toString()};
        intinfo=new int[]{1,2};

        new Payment_Handler(getActivity().getBaseContext()).createPayment(stringinfo,Float.parseFloat(amount.getText().toString()),intinfo);

    }
}
