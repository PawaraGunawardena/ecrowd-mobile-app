package com.ecrowd.ecrowd.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;

import com.ecrowd.ecrowd.data.DynamicFormData;
import com.ecrowd.ecrowd.data.model.FormGeneral;
import com.ecrowd.ecrowd.data.model.FormPartial;
import com.ecrowd.ecrowd.data.model.User;

import java.util.ArrayList;

/**
 * Created by User on 3/31/2018.
 */

public class DynamicFormDataCollectionView extends AppCompatActivity {

    private String table_name;
    private String survey_name;
    private Intent intent;
    private User user;
    String TAG = "COOOOOOOOOO";
    RelativeLayout formLayout ;
    private DynamicFormData dynamicFormData;
    private FormGeneral form_general;
    private ArrayList<FormPartial> survey_partials;

    static final String[] alphabets = new String[] {"A", "B", "C", "D", "E","F", "G", "H", "I", "J","K", "L", "M", "N", "O","P", "Q", "R", "S", "T","U", "V", "W", "X"};
    static final ArrayList<String> testValues= new ArrayList<>();//

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, testValues);
        dynamicFormData = new DynamicFormData(this);


        formLayout = new RelativeLayout(this);
        formLayout.setBackgroundColor(Color.CYAN);
        intent = getIntent();
        getFormSerializableData();
//        survey_partials = (ArrayList<FormPartial>) intent.getSerializableExtra("selected_survey_partials");
        FormPartial fp = (FormPartial)survey_partials.get(1);
        Log.i(TAG,fp.getAttribute_title()+" Is the 1st attribute title !!!!!!!!!!!");

        RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT,
                RelativeLayout.LayoutParams.FILL_PARENT);

        ArrayList<String> typed_values = dynamicFormData.getDynamicFormDataCollect(form_general, survey_partials);
        testValues.add("Username");
        for(int index = 0 ; index<survey_partials.size() ; index++){
            FormPartial current_partial = (FormPartial)survey_partials.get(index);
            testValues.add((String)current_partial.getAttribute_title());
//            testValues.add(alphabets[index]);
        }
        for(int index = 0 ; index<typed_values.size() ; index++){
            String current_value = (String)typed_values.get(index);
            testValues.add(current_value);
//            testValues.add(alphabets[index]);
        }

        GridView gridView= new GridView(this);
//        gridView.setLayoutParams(new GridView.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        gridView.setNumColumns(survey_partials.size()+1);
        gridView.setAdapter(adapter);
        formLayout.addView(gridView);

        setContentView(formLayout, relativeLayoutParams);
    }

    private void getFormSerializableData(){
        //Form Basic Information
        form_general = (FormGeneral) intent.getSerializableExtra("FormBasicInformation");
        //Form Widget Information
        survey_partials = (ArrayList<FormPartial>) intent.getSerializableExtra("selected_survey_partials");
        //set the name of the survey name
        survey_name = form_general.getForm_name();
        //Current User
        user = (User) intent.getSerializableExtra("user");
    }
}
