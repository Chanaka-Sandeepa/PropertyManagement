package layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.chanaka.propertymanager.R;

/**
 * Created by chanaka on 3/28/17.
 */

public class PaymentHistory extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_view_payment_history,container,false);
        ListView listItems=(ListView)view.findViewById(R.id.lstPayment);
        populateList(listItems);
        return view;
    }
    //generate items into the list
    public void populateList(ListView lv){
        //dummy payments to display
        String[] payments = {"Jhon               2000.00                  03/03/2017",
                            "Sam                3000.00                  04/03/2017",
                            "Max                1500.00                  06/03/2017",
                            "Jim                 4000.00                  10/03/2017"};

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity().getBaseContext(),android.R.layout.simple_list_item_1,payments);
        lv.setAdapter(adapter);

    }
}
