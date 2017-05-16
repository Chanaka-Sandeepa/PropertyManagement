package layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.chanaka.propertymanager.Controllers.Payment_Handler;
import com.example.chanaka.propertymanager.Controllers.User_Handler;
import com.example.chanaka.propertymanager.R;
import com.example.chanaka.propertymanager.Views.MyApartment;
import com.example.chanaka.propertymanager.Views.UpdateTenant;


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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View  view, int position, long id)
            {
                goToTenants(position);
            }
        });
        tenants =uHan.viewTenants();
        adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1, tenants);

        listView.setAdapter(adapter);

        return view;
    }

    private void goToTenants(int position) {
        Intent i = new Intent(getActivity(), UpdateTenant.class);
        String s=listView.getItemAtPosition(position).toString();
        i.putExtra("tenants", s);
        startActivity(i);
    }


}
