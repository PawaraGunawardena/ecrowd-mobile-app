package com.ecrowd.ecrowd;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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

import com.ecrowd.ecrowd.data.FormData;
import com.ecrowd.ecrowd.data.FormDataAccess;
import com.ecrowd.ecrowd.data.FormGeneralData;
import com.ecrowd.ecrowd.data.model.FormGeneral;
import com.ecrowd.ecrowd.data.model.FormPartial;
import com.ecrowd.ecrowd.data.model.User;
import com.ecrowd.ecrowd.ui.DynamicFormDataCollectionView;
import com.ecrowd.ecrowd.ui.DynamicFormView;

import java.util.ArrayList;

/**
 * Created by User on 3/3/2018.
 */

public class Tab3CollectedData extends Fragment {

    String TAG = "COOOOOOOOOO";
    private User user;
    private Context passingContext;
    private View rootView;
    private ListView listView;
    private FormData form_data;
    private ListAdapter listAdapter;
    private String selected_value;
    private FormDataAccess formDataAccess;

    FormGeneral form_general;
    FormGeneralData form_general_infomation_supplier;

    public Tab3CollectedData() {
    }

    public Tab3CollectedData(User user) {
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){

        rootView = inflater.inflate(R.layout.fragment_tab3_data, container, false);
        passingContext = this.getActivity().getBaseContext();
        formDataAccess = new FormDataAccess(passingContext);

        form_general = new FormGeneral();
        form_general_infomation_supplier = new FormGeneralData(passingContext);

        try {

            listView = (ListView) rootView.findViewById(R.id.id_listView_tab3);
            form_data = new FormData(this.getContext());

            final ArrayList<String> listItems = new ArrayList<>();

            Cursor data = form_data.getFormNameByUser(user.getUsername());

            if(data.getCount() == 0){
                Toast.makeText(this.getContext(), "Table was empty", Toast.LENGTH_LONG).show();

            }else{
                while(data.moveToNext()){
                    listItems.add(data.getString(1));
                    listAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,listItems);
                    listView.setAdapter(listAdapter);
                }
            }


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    try{

                        Intent dynamicForm = new Intent(passingContext, DynamicFormDataCollectionView.class);

                        //reading the currently selected value
                        selected_value = listItems.get(i);

                        //get the forms basic information like form_name, username, starting, closing dates and pass them
                        form_general = form_general_infomation_supplier.get_form_general(selected_value
//                                ,user.getUsername()
                        );
                        dynamicForm.putExtra("FormBasicInformation",form_general);

                        //get the list of attributes details as ArrayList of FormPartials and pass them
                        ArrayList<FormPartial> selected_form_partials = formDataAccess.get_form_partials((String)selected_value);
                        dynamicForm.putExtra("selected_survey_partials",selected_form_partials);

                        //Example test value, need to delete
                        dynamicForm.putExtra("user", user);

                        startActivity(dynamicForm);

                    }catch(Exception e){
                        Log.i(TAG,e.getMessage());
                    }

                }
            });

        }catch(Exception e){
            Log.i(TAG,e.getMessage());
        }
        return rootView;

    }
}

