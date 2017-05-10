package layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.chanaka.propertymanager.Controllers.Payment_Handler;
import com.example.chanaka.propertymanager.Controllers.Property_Handler;
import com.example.chanaka.propertymanager.R;

/**
 * Created by chanaka on 3/28/17.
 */

public class PaymentHistory extends Fragment {

    ListView listView;
    ListAdapter adapter;
    String[] payments;
    Payment_Handler pHan;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_view_payment_history,container,false);
        pHan=new Payment_Handler(getContext());
        listView=(ListView)view.findViewById(R.id.lstPayment);
        payments=pHan.viewPayments();
        adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,payments);

        listView.setAdapter(adapter);

        return view;
    }
}
