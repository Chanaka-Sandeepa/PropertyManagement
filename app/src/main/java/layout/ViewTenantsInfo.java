package layout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import com.example.chanaka.propertymanager.Views.ResultApartments;
import com.example.chanaka.propertymanager.Views.UpdateTenant;


public class ViewTenantsInfo extends Fragment {

    ListView listView;
    ListAdapter adapter;
    String[] tenants;
    User_Handler uHan;
    AlertDialog alertRemove;
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

        refreshList();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                createAlert(listView.getItemAtPosition(pos).toString());
                alertRemove.show();
                return true;
            }
        });


        return view;
    }

    private void goToTenants(int position) {
        Intent i = new Intent(getActivity(), UpdateTenant.class);
        String s=listView.getItemAtPosition(position).toString();
        i.putExtra("tenants", s);
        startActivity(i);
    }

    public void removeTenant(String address){
        uHan.deleteProperty(address);
        refreshList();
    }

    public void createAlert(final String name){
        final String s=name;
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage("Are you sure You want to remove the tenant?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        removeTenant(name);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        alertRemove= builder1.create();

    }

    public void refreshList(){
        tenants =uHan.viewTenants();
        adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1, tenants);
        listView.setAdapter(adapter);
    }
}
