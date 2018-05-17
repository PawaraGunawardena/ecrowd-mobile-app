package com.ecrowd.ecrowd.data.dbserver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ecrowd.ecrowd.data.DBHelper;
import com.ecrowd.ecrowd.data.FormData;
import com.ecrowd.ecrowd.data.FormDataAccess;
import com.ecrowd.ecrowd.data.FormGeneralData;
import com.ecrowd.ecrowd.data.model.Form;
import com.ecrowd.ecrowd.data.model.FormGeneral;
import com.ecrowd.ecrowd.data.model.FormPartial;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NetworkMonitor extends  BroadcastReceiver{
    FormData form_data;
    private FormDataAccess formDataAccess;//to get form partial data
    FormGeneral form_general;
    FormGeneralData form_general_infomation_supplier;
    FormGeneralServer formGeneralServer;
    private Form dynamcForm;
    String TAG = "WellWish";
    @Override
    public void onReceive(final Context context, Intent intent) {

        if(checkNetworkConnection(context)){
            final DBHelper dbHelper = new DBHelper((context));

            final SQLiteDatabase database = dbHelper.getWritableDatabase();
            form_data = new FormData(context);
            form_general = new FormGeneral();
            form_general_infomation_supplier = new FormGeneralData(context);
            formDataAccess = new FormDataAccess(context);
            formGeneralServer = new FormGeneralServer();
            dynamcForm = new Form();

            Cursor cursor = form_data.getFormName();

            while(cursor.moveToNext()){
                int sync_status = cursor.getInt(cursor.getColumnIndex("sync_status"));
                if(sync_status ==0){
                    String current_form_name = cursor.getString(cursor.getColumnIndex("form_name"));
                    String current_table_name = cursor.getString(cursor.getColumnIndex("table_name"));
                    String current_user_name = cursor.getString(cursor.getColumnIndex("username"));

                    dynamcForm.setForm_name(current_form_name);
                    dynamcForm.setTable_name(current_table_name);
                    dynamcForm.setUsername(current_user_name);

                    form_general = form_general_infomation_supplier.get_form_general(current_form_name
//                                ,user.getUsername()
                    );

                    final ArrayList<FormPartial> selected_form_partials = formDataAccess.get_form_partials((String)current_form_name);

//                    dynamcForm.setAttribute_titles(selected_form_partials.);


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, DBServerHelper.SERVER_URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        String Response = jsonObject.getString("response");
                                        if(Response.equals("OK")){
//                                            dbHelper.updateLocalDatabase(Name, DbContact.SYNC_STATUS_OK, database);
                                            form_data.updateSyncStatus(dynamcForm, 1);
//                                            context.sendBroadcast(new Intent(DbContract.UI_UPDATE_BROADCAST));
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }
                    ){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<>();
                            String formGeneral = formGeneralServer.getFormGeneralString(dynamcForm);
                            String partialString = formGeneralServer.getFormPartialStringByFormPartialArray(selected_form_partials);
                            String formTableCreateQuery = formGeneralServer.getFormTablecreateStringByFormPartialArray(dynamcForm, selected_form_partials);
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
                    };

                    MySingleton.getInstance(context).addToRequestQue(stringRequest);
                }
            }

            dbHelper.close();
        }

    }




    public boolean checkNetworkConnection(Context context){
        ConnectivityManager connectivityManager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());
    }
}

