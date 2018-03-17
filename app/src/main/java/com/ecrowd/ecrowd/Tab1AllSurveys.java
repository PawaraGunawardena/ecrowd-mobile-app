package com.ecrowd.ecrowd;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ecrowd.ecrowd.data.FormData;

import java.util.ArrayList;
import java.util.zip.Inflater;


/**
 * Created by User on 3/3/2018.
 */

public class Tab1AllSurveys extends Fragment {


    String TAG = "COOOOOOOOOO";
    public Tab1AllSurveys() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_tab1_all_surveys, container, false);
        try {
//            String[] spaceCrafts = {"Juno", "Clr", "torsan"};

            ListView listView = (ListView) rootView.findViewById(R.id.id_listView_tab1);
            FormData form_data = new FormData(this.getContext());

            ArrayList<String> listItems = new ArrayList<>();
            Cursor data = form_data.getFormName();

            if(data.getCount() == 0){
                Toast.makeText(this.getContext(), "Table was empty", Toast.LENGTH_LONG).show();

            }else{
                while(data.moveToNext()){
                    listItems.add(data.getString(1));
                    ListAdapter listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,listItems);
                    listView.setAdapter(listAdapter);
                }
            }
//            ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
//                    getActivity(),
//                    android.R.layout.simple_list_item_1,
//                    spaceCrafts
//            );
//            listView.setAdapter(listViewAdapter);
        }catch(Exception e){
            Log.i(TAG,e.getMessage());
        }
        return rootView;
    }
}

