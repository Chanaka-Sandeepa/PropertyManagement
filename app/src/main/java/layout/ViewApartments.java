package layout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    public static boolean isSearch=false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_apartment_list,container,false);
        pHan=new Property_Handler(getContext());

        listView=(ListView)view.findViewById(R.id.lstApartments);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View  view, int position, long id)
            {
                goToApartment(position);
            }
        });

        if(isSearch){
            ResultApartments activity=(ResultApartments)getActivity();
            apartments=activity.getProperties();
        }else {
            apartments = pHan.viewApartments();
        }
        adapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,apartments);

        listView.setAdapter(adapter);

        return view;
    }

    private void goToApartment(int position) {
        Intent i = new Intent(getActivity(), MyApartment.class);
        String[] s={"true",listView.getItemAtPosition(position).toString()};
        i.putExtra("searchRes", s);
        startActivity(i);
    }




}
