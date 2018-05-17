package com.ecrowd.ecrowd.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ecrowd.ecrowd.data.dbserver.DBServerHelper;
import com.ecrowd.ecrowd.data.dbserver.MySingleton;
import com.ecrowd.ecrowd.data.model.FormGeneral;
import com.ecrowd.ecrowd.data.model.FormPartial;
import com.ecrowd.ecrowd.data.model.User;
import com.ecrowd.ecrowd.data.schema.IFormSchema;
import com.ecrowd.ecrowd.ui.DynamicFormCreate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 2/17/2018.
 */

public class DynamicFormData {
    //Survey form data connection form which interact with the database
    private ArrayList<FormPartial> formPartials;
    private FormGeneral formGeneral;
    private DBHelper crowdDatabaseHelper;
    private SQLiteDatabase db;
    private String TAG = "CROCODE";

    public DynamicFormData(Context context) {
        crowdDatabaseHelper = new DBHelper(context);
        db = crowdDatabaseHelper.getWritableDatabase();
    }

    public DynamicFormData(ArrayList<FormPartial> formPartials, FormGeneral formGeneral,Context context) {
        this.formPartials = formPartials;
        this.formGeneral = formGeneral;
        crowdDatabaseHelper = new DBHelper(context);
        db = crowdDatabaseHelper.getWritableDatabase();
    }

    public ArrayList<FormPartial> getFormPartials() {
        return formPartials;
    }

    public void setFormPartials(ArrayList<FormPartial> formPartials) {
        this.formPartials = formPartials;
    }

    public FormGeneral getFormGeneral() {
        return formGeneral;
    }

    public void setFormGeneral(FormGeneral formGeneral) {
        this.formGeneral = formGeneral;
    }





    public void insertIntoDynamicFormTable(final User user, final FormGeneral form_general, Context context){
        try {

            if(checkNetworkConnection(context)){

                StringRequest stringRequest =
                        new StringRequest
                                //Start of the constructor paranthesis - StringRequest
                                (Request.Method.POST, DBServerHelper.SERVER_URL_FORM_FILL,
                                        new Response.Listener<String>() {
                                    //response lisner for the request
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

                                                    Log.i(TAG, "Got the confirm OK ");
                                                    db.execSQL(getInsertQuery(user.getUsername(), form_general.getTable_name(),1));
                                                    Log.i(TAG, "Got the confirm OK "+getInsertQuery(user.getUsername(), form_general.getTable_name(),1));

                                                } else {

                                                    Log.i(TAG, "Response not recieved as OK");
                                                    db.execSQL(getInsertQuery(user.getUsername(), form_general.getTable_name(),0));
                                                    Log.i(TAG, "No confirm "+getInsertQuery(user.getUsername(), form_general.getTable_name(),0));
                                                    Log.i(TAG, "Yes confirm "+getInsertQuery(user.getUsername(), form_general.getTable_name(),1));
                                                }
                                            }
                                        },
                                        new Response.ErrorListener() {
                                            // error listner for the request as the fourth parameter of the constructor
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                db.execSQL(getInsertQuery(user.getUsername(), form_general.getTable_name(),0));

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
                                String formFillingQuery = getDBServerInsertQuery(user.getUsername(), form_general.getTable_name());
                                params.put("query_insert_to_table", formFillingQuery);
                                Log.i(TAG, "General String :"+formFillingQuery);
                                return params;
                            }
                            // end of the body
                        };
                MySingleton.getInstance(context).addToRequestQue(stringRequest);
            } else {
                db.execSQL(getInsertQuery(user.getUsername(), form_general.getTable_name(),0));
                Log.i(TAG, "Response not 2");
            }
//            db.execSQL(getInsertQuery(user.getUsername(), form_general.getTable_name()));
            Log.e(TAG, "Inserted th equery in a successful way :)");
        }catch(Exception e){
            Log.e(TAG, formGeneral.getUsername()+" username and "+ formGeneral.getTable_name()+" table name");
//            Log.e(TAG, getInsertQuery());
            Log.e(TAG, e.getMessage());
        }
    }


    public String getInsertQuery(String username, String tablename, int sync_status){
        String query = "Insert INTO "+tablename
                +" VALUES ('"+username+"','"+sync_status+"'"
                ;
        for(int i=0; i< formPartials.size(); i++){
            query += ", '" + formPartials.get(i).getAttribute_value()+"'";
        }



//        = removeLastComma(query);
        query+=");";
        return query;
    }

    public String getDBServerInsertQuery(String username, String tablename){
        String query = "Insert INTO "+tablename
                +" VALUES ('"+username+"'"
                ;
        for(int i=0; i< formPartials.size(); i++){
            query += ", '" + formPartials.get(i).getAttribute_value()+"'";
        }



//        = removeLastComma(query);
        query+=");";
        return query;
    }


    public ArrayList<String> getDynamicFormDataCollect(FormGeneral formGeneral, ArrayList<FormPartial> formPartials){
        String tableName = formGeneral.getTable_name();

        String query = "SELECT * FROM "+tableName;
        Cursor formDataCursor = db.rawQuery(query, null);
        ArrayList<String> typed_values  = new ArrayList<>();

        if(formDataCursor.getCount() == 0){
//            Toast.makeText(this.getContext(), "Table was empty", Toast.LENGTH_LONG).show();

        }else{
            while(formDataCursor.moveToNext()) {

                for (int index = 0; index < formPartials.size()+1; index++)
                    typed_values.add(formDataCursor.getString(index));
            }

        }
        return typed_values;
//        return formName;
//        db.execSQL(query);
    }


    public boolean checkNetworkConnection(Context context){
        ConnectivityManager connectivityManager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());
    }


}
