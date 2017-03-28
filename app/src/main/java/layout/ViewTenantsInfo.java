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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.chanaka.propertymanager.R;


public class ViewTenantsInfo extends Fragment {
    //private static final String tag="Tab2";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_view_tenants_info,container,false);
        ListView listItems=(ListView)view.findViewById(R.id.lstTenants);
        populateList(listItems);
        return view;
    }
    //generate items into the list
    public void populateList(ListView lv){
        //dummy tenants to display
        String[] tenants = {"Jhon","Sam","Max","Jim"};

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity().getBaseContext(),android.R.layout.simple_list_item_1,tenants);
        lv.setAdapter(adapter);

    }


}
