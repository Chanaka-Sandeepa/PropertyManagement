package layout;

import android.content.Context;
import android.net.Uri;
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

public class ViewApartments extends Fragment {
    ListView listView;
    ArrayAdapter<String> adapter;
    String[] apartments;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_apartment_list,container,false);

        listView=(ListView) view.findViewById(R.id.lstApartments);
        apartments=getResources().getStringArray(R.array.apartment_list);
        adapter=new ArrayAdapter<String>(getActivity().getBaseContext(),android.R.layout.simple_list_item_1,apartments);
        listView.setAdapter(adapter);

        return view;
    }
}
