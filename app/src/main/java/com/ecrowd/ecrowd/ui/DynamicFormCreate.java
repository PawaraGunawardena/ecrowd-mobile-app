package com.ecrowd.ecrowd.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ecrowd.ecrowd.R;
import com.ecrowd.ecrowd.SurveyCreateActivityView;
import com.ecrowd.ecrowd.SurveyCreatePresenter;
import com.ecrowd.ecrowd.data.FormData;
import com.ecrowd.ecrowd.data.dbserver.DBServerHelper;
import com.ecrowd.ecrowd.data.dbserver.FormGeneralServer;
import com.ecrowd.ecrowd.data.dbserver.MySingleton;
import com.ecrowd.ecrowd.data.model.Form;
import com.ecrowd.ecrowd.data.model.User;
import com.ecrowd.ecrowd.data.schema.DynamicFormTableSchema;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.ecrowd.ecrowd.R.id.text_fieldTitle_dynamicFormCreate;

public class DynamicFormCreate extends AppCompatActivity implements SurveyCreateActivityView {

    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    private Form dynamcForm;
    private DynamicFormTableSchema databaseTableSchema;
    ArrayList<String> userFieldTitleList = new ArrayList<>();
    ArrayList<String> userFieldTypeList = new ArrayList<>();
    String TAG = "Error";
    User user;
SurveyCreatePresenter presenter;

    FormGeneralServer formGeneralServer; // to get queries URL to address the Database Server
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        presenter = new SurveyCreatePresenter(this);
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dynamic_form_create);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_form_create);
        setSpinner();
        Intent i = getIntent();
        user = (User) i.getSerializableExtra("UserAccount");

        formGeneralServer = new FormGeneralServer();
    }


    //method to refresh the content of widgets
    public void setSpinner() {
//        adapter.clear();
        TextView itemTitle = (TextView) findViewById(text_fieldTitle_dynamicFormCreate);
        itemTitle.setText("");
        Spinner x = (Spinner) findViewById(R.id.spinner_fieldType_dynamicFormCreate);
//        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(DynamicFormCreate.this,android.R.layout.simple_list_item_1,
//                getResources().getStringArray(R.array.names));
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        x.setAdapter(adapter);
        listItems.add(0, "Select Type");
        listItems.add(1, "Text");
        listItems.add(2, "Mobile Usage");
        listItems.add(3, "Data Usage");
//        listItems.add(5, "Radio Button");
//        listItems.add(4, "Check Box");
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.txt, listItems);
        x.setAdapter(adapter);
    }


    public void submit_field_clicked(View view) {
        try {
            EditText itemTitle = (EditText) findViewById(R.id.text_fieldTitle_dynamicFormCreate);
            String addedItemTitle = itemTitle.getText().toString();
            userFieldTitleList.add(addedItemTitle);

            Spinner itemType = (Spinner) findViewById(R.id.spinner_fieldType_dynamicFormCreate);
            String addedItemType = itemType.getSelectedItem().toString();
            userFieldTypeList.add(addedItemType);

            Log.i(TAG, "Correct :" + userFieldTypeList.get(0) + " " + userFieldTitleList.get(0));
        } catch (Exception e) {
            Log.i(TAG, e.getMessage());
        }
        setSpinner();

//        TextView title = (TextView)findViewById(R.id.label_addField_dynamicFormCreate);
//        title.setText(userFieldTitleList.get(0)+" ,"+userFieldTypeList.get(0));
    }

    public void submit_dynamic_form(View view) {
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


            final FormData dataForm = new FormData(this);

            DBServerHelper dbServerHelper = new DBServerHelper();



                if (checkNetworkConnection()) {


                    StringRequest stringRequest =
                            new StringRequest
                                    //Start of the constructor paranthesis - StringRequest
                                    (Request.Method.POST, DBServerHelper.SERVER_URL,
                                            new Response.Listener<String>() {//response lisner for the request

                                                @Override
                                                public void onResponse(String response) {
                                                    JSONObject jsonObject = null;
                                                    try {
                                                        jsonObject = new JSONObject(response);
                                                    } catch (Exception e) {

                                                    }

                                                    String Response = null;
                                                    try {
                                                        Response = jsonObject.getString("response");
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }

                                                    if (Response.equals("OK")) {
//                                                        saveToLocalStorage(name, DbContact.SYNC_STATUS_OK);
                                                        Log.i(TAG, "OK ");
                                                        dataForm.addForm(dynamcForm,1);
                                                    } else {
//                                                        saveToLocalStorage(name, DbContact.SYNC_STATUS_FAILED);
                                                        Log.i(TAG, "Response not recieved as OK");
                                                        dataForm.addForm(dynamcForm,0);
                                                    }

                                                }
                                            },

                                            new Response.ErrorListener() {
                                                // error listner for the request as the fourth parameter of the constructor
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
//                                                saveToLocalStorage(name, DbContact.SYNC_STATUS_FAILED);
                                                    dataForm.addForm(dynamcForm,0);

                                                    Log.i(TAG, "Response not 1");
                                                    Log.i(TAG, "Response not 1"+error.getMessage());
                                                }
                                            }
                                    )
                                    //end of the constructor paranthesis - StringRequest

                            {
                                //start of the constructor bocy - StringRequest
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> params = new HashMap<>();

                                    String formGeneral = formGeneralServer.getFormGeneralString(dynamcForm);
                                    String partialString = formGeneralServer.getFormPartialString(dynamcForm);
                                    String formTableCreateQuery = formGeneralServer.getFormTablecreateString(dynamcForm);
//                                    params.put("form_name", dynamcForm.getForm_name());
//                                    params.put("table_name", dynamcForm.getTable_name());
//                                    params.put("username", dynamcForm.getUsername());

                                    params.put("query_general", formGeneral);
                                    params.put("query_partial", partialString);
                                    params.put("query_form_table", formTableCreateQuery);

                                    Log.i(TAG, "General String :"+formGeneral);
                                    Log.i(TAG, "Partial String :"+partialString);
                                    Log.i(TAG, "table create query :"+formTableCreateQuery);

                                    return params;

                                }
                                // end of the body
                            };

                    MySingleton.getInstance(DynamicFormCreate.this).addToRequestQue(stringRequest);

                } else {
//            saveToLocalStorage(name, DbContact.SYNC_STATUS_FAILED);
                    dataForm.addForm(dynamcForm,0);
                    Log.i(TAG, "Response not 2");
                }




//            dataForm.addForm(dynamcForm, this.getApplicationContext());
            Log.i(TAG, "Form paased to inser :) :)");
            Intent home = new Intent(this, Home.class);

            home.putExtra("UserAccount", user);
            startActivity(home);


        } catch (
                Exception e)

        {
            Log.i(TAG, e.getMessage());
        }
    }

    @Override
    public void submitWidgetItem() {
    }

    public boolean checkNetworkConnection(){
//        ConnectivityManager connectivityManager =
//                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//        return (networkInfo!=null && networkInfo.isConnected());
        return presenter.lanchCheckNetworkConnection();
    }

    @Override
    public boolean checkNetworkConnectionTriggered() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());
    }

    @Override
    public void submitDynamicFormTriggered(Class activity) {
        Log.i(TAG, "Form paased to inser :) :)");
        Intent home = new Intent(this, Home.class);

    }




}
