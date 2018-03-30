package com.ecrowd.ecrowd.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ecrowd.ecrowd.R;
import com.ecrowd.ecrowd.data.FormData;
import com.ecrowd.ecrowd.data.model.Form;
import com.ecrowd.ecrowd.data.model.User;
import com.ecrowd.ecrowd.data.schema.DynamicFormTableSchema;

import java.util.ArrayList;

import static com.ecrowd.ecrowd.R.id.text_fieldTitle_dynamicFormCreate;

public class DynamicFormCreate extends AppCompatActivity {

    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    private Form dynamcForm;
    private DynamicFormTableSchema databaseTableSchema;
    ArrayList<String> userFieldTitleList = new ArrayList<>();
    ArrayList<String> userFieldTypeList = new ArrayList<>();
    String TAG = "Error";
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dynamic_form_create);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_form_create);
        setSpinner();
        Intent i = getIntent();
        user = (User) i.getSerializableExtra("UserAccount");
    }


    //method to refresh the content of widgets
    public void setSpinner(){
        TextView itemTitle = (TextView)findViewById(text_fieldTitle_dynamicFormCreate);
        itemTitle.setText("");
        Spinner x = (Spinner) findViewById(R.id.spinner_fieldType_dynamicFormCreate);
//        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(DynamicFormCreate.this,android.R.layout.simple_list_item_1,
//                getResources().getStringArray(R.array.names));
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        x.setAdapter(adapter);
        listItems.add(0, "Select Type");
        listItems.add(1, "Text");
        listItems.add(2, "Button");
        listItems.add(3, "Drop Down");
        listItems.add(4, "Check Box");
        listItems.add(5, "Radio Button");
        adapter = new ArrayAdapter<String>(this,  R.layout.spinner_layout,R.id.txt, listItems);
        x.setAdapter(adapter);
    }


    public void submit_field_clicked(View view){
        try {
            EditText itemTitle = (EditText) findViewById(R.id.text_fieldTitle_dynamicFormCreate);
            String addedItemTitle = itemTitle.getText().toString();
            userFieldTitleList.add(addedItemTitle);

            Spinner itemType = (Spinner) findViewById(R.id.spinner_fieldType_dynamicFormCreate);
            String addedItemType = itemType.getSelectedItem().toString();
            userFieldTypeList.add(addedItemType);

            Log.i(TAG,"Correct :"+userFieldTypeList.get(0)+" "+userFieldTitleList.get(0));
        }catch (Exception e){
            Log.i(TAG,e.getMessage());
        }
        setSpinner();

//        TextView title = (TextView)findViewById(R.id.label_addField_dynamicFormCreate);
//        title.setText(userFieldTitleList.get(0)+" ,"+userFieldTypeList.get(0));
    }

    public void submit_dynamic_form(View view){
//        dynamcForm = new Form();
        try {
            dynamcForm = new Form();
            EditText formTitle = (EditText) findViewById(R.id.text_titleSurvey_dynamicFormCreate);
            String formTitle_form = formTitle.getText().toString();

            dynamcForm.setForm_name(formTitle_form);
            dynamcForm.setTable_name(formTitle_form);
            dynamcForm.setStarting_date("2010-11-11");
            dynamcForm.setClosing_date("2011-12-12");
            dynamcForm.setUsername(user.getUsername());

            dynamcForm.setAttribute_titles(userFieldTitleList);
            dynamcForm.setAttribute_type(userFieldTypeList);
            dynamcForm.setMobility("true");


            FormData dataForm = new FormData(this);


            dataForm.addForm(dynamcForm);
            Log.i(TAG, "Form paased to inser :) :)");
            Intent home = new Intent(this, Home.class);

            home.putExtra("UserAccount", user);
            startActivity(home);
        }catch(Exception e){
            Log.i(TAG, e.getMessage());
        }

    }
}
