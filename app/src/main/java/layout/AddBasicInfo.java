package layout;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.chanaka.propertymanager.R;
import com.example.chanaka.propertymanager.Views.AddProperty;

/**
 * Created by chanaka on 3/30/17.
 */


public class AddBasicInfo extends Fragment {

    AddBasicInfoListener addBasicInfoListener;

    public interface AddBasicInfoListener{
        void passInfo(String[] info);
    }

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    String[] info = new String[4];
    private String type;

    private EditText number;
    private EditText street;
    private EditText city;
    private EditText footage;
    private EditText desc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_basic_info,container,false);
        number= (EditText) view.findViewById(R.id.txtNo);
        street= (EditText) view.findViewById(R.id.txtStreet);
        city= (EditText) view.findViewById(R.id.txtCity);
        footage= (EditText) view.findViewById(R.id.txtFootage);
        desc= (EditText) view.findViewById(R.id.txtDesc);



        spinner =(Spinner)view.findViewById(R.id.spinner);
        adapter=ArrayAdapter.createFromResource(this.getContext(),R.array.types ,android.R.layout.simple_spinner_item);
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

        Button btnNext=(Button)view.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(

                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked();
                    }
                }
        );

        if(AddProperty.isTenant){
            number.setFocusable(false);
            street.setFocusable(false);
            city.setFocusable(false);
            footage.setFocusable(false);
            desc.setFocusable(false);
            spinner.setFocusable(false);
        }



        return view;
    }
    public void buttonClicked(){
        String sNum=number.getText().toString();
        String sSteet=street.getText().toString();
        String sCity=city.getText().toString();
        String sFootage=footage.getText().toString();
        String sDesc=desc.getText().toString();
        String[] inputs={sNum,sSteet,sCity,sFootage,sDesc};
        if(checkInputValidity(inputs)) {
            info = new String[]{number.getText().toString() + "," + street.getText().toString() + "," + city.getText().toString(), footage.getText().toString(), desc.getText().toString(), type};
            addBasicInfoListener.passInfo(info);
        }else{
        }

    }


    @Override
    public void onAttach(Activity activity) {
        addBasicInfoListener=(AddBasicInfoListener)activity;
        super.onAttach(activity);
    }

    public boolean checkInputValidity(String[] inputs){
        if(TextUtils.isEmpty(inputs[0])){
            number.setError("Enter a valid value...");
            return false;
        }else if(TextUtils.isEmpty(inputs[1])){
            street.setError("Enter a valid value...");
            return false;
        }else if(TextUtils.isEmpty(inputs[2])){
            city.setError("Enter a valid value...");
            return false;
        }else if(TextUtils.isEmpty(inputs[3])){
            footage.setError("Enter a valid value...");
            return false;
        }else if(TextUtils.isEmpty(inputs[4])){
            desc.setError("Enter a valid value...");
            return false;
        }
        return true;
    }
}
