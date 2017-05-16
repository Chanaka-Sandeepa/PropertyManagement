package layout;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.chanaka.propertymanager.R;
import com.example.chanaka.propertymanager.Views.Home;

/**
 * Created by chanaka on 3/30/17.
 */

public class AddRenatalInfo extends Fragment {

    AddRentalInfoListener addRentalInfoListener;

    public interface AddRentalInfoListener{
        void addNewPropertyFragment(Double[] rental_info,String[] other_info);
        void goToHome();
    }

    private EditText rental;
    private EditText deposit;
    private EditText date;
    private EditText image;
    Double[] rental_info =new Double[2];
    String[] other_info = new String[2];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_rental_info,container,false);

        rental= (EditText) view.findViewById(R.id.txtRental);
        deposit= (EditText) view.findViewById(R.id.txtDeposit);
        date= (EditText) view.findViewById(R.id.txtAvailableDate);
        date.setText(Home.getDate());
        image= (EditText) view.findViewById(R.id.txtPropertyImage);

        Button btnAdd=(Button)view.findViewById(R.id.addBtn);
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
        rental_info=new Double[]{Double.parseDouble(rental.getText().toString()),Double.parseDouble(deposit.getText().toString())};
        other_info=new String[]{date.getText().toString(),image.getText().toString()};

        addRentalInfoListener.addNewPropertyFragment(rental_info,other_info);
        addRentalInfoListener.goToHome();

    }


    @Override
    public void onAttach(Activity activity) {
        addRentalInfoListener = (AddRentalInfoListener) activity;
        super.onAttach(activity);
    }
}
