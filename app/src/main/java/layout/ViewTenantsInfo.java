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
import com.example.chanaka.propertymanager.Controllers.User_Handler;
import com.example.chanaka.propertymanager.R;


public class ViewTenantsInfo extends Fragment {

    ListView listView;
    ListAdapter adapter;
    String[] tenants;
    User_Handler uHan;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_view_tenants,container,false);
        uHan=new User_Handler(getActivity().getBaseContext());
        listView=(ListView)view.findViewById(R.id.lstTenants);
        tenants =uHan.viewTenants();
        adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1, tenants);

        listView.setAdapter(adapter);

        return view;
    }


}
