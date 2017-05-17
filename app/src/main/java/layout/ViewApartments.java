package layout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chanaka.propertymanager.Controllers.Property_Handler;
import com.example.chanaka.propertymanager.Models.Property;
import com.example.chanaka.propertymanager.R;
import com.example.chanaka.propertymanager.Views.MyApartment;
import com.example.chanaka.propertymanager.Views.ResultApartments;

import java.util.ArrayList;

/**
 * Created by chanaka on 3/28/17.
 */

public class ViewApartments extends Fragment {
    ListView listView;
    ListAdapter adapter;
    String[] apartments;
    Property_Handler pHan;
    AlertDialog alertRemove;
    Toolbar toolbar;
    public static boolean isSearch=false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_apartment_list,container,false);
        pHan=new Property_Handler(getContext());

        toolbar=(Toolbar)view.findViewById(R.id.toolbar);
        listView=(ListView)view.findViewById(R.id.lstApartments);
        listView.setLongClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View  view, int position, long id)
            {
                goToApartment(position);
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

    private void goToApartment(int position) {
        Intent i = new Intent(getActivity(), MyApartment.class);
        String[] s={"true",listView.getItemAtPosition(position).toString()};
        i.putExtra("searchRes", s);
        startActivity(i);
    }

    public void removeProperty(String address){
        pHan.deleteProperty(address);
        refreshList();
    }

    public void createAlert(final String address){
        final String s=address;
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage("Are you sure You want to remove the property?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        removeProperty(s);
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
        if(isSearch){
            ResultApartments activity=(ResultApartments)getActivity();
            apartments=activity.getProperties();
            listView.setLongClickable(false);
            toolbar.setTitle("Search Results");
        }else {
            apartments = pHan.viewApartments();
        }

        adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,apartments);

        listView.setAdapter(adapter);
    }
}
