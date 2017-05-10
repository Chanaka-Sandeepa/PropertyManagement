package layout;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.chanaka.propertymanager.R;

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



        return view;
    }
    public void buttonClicked(){
        info=new String[]{number.getText().toString()+","+street.getText().toString()+","+city.getText().toString(),footage.getText().toString(),desc.getText().toString(),type};

        addBasicInfoListener.passInfo(info);
    }


    @Override
    public void onAttach(Activity activity) {
        addBasicInfoListener=(AddBasicInfoListener)activity;
        super.onAttach(activity);
    }
}
