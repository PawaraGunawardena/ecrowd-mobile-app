package com.ecrowd.ecrowd.data.dbserver;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ecrowd.ecrowd.data.model.Form;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DBServerHelper {
String TAG = "WAAAAAAAAAAR";
    public static final String SERVER_URL = "http://ecrowd.000webhostapp.com/ecrowdserver/formgeneral.php";
    public static final String SERVER_URL_FORM_FILL = "http://ecrowd.000webhostapp.com/ecrowdserver/formfill.php";
//    public static final String SERVER_URL_REQUEST_DATA_OF_SERVER = "http://ecrowd.000webhostapp.com/ecrowdserver/formInServer.php";
    public static final String SERVER_URL_REGISTER = "http://ecrowd.000webhostapp.com/ecrowdserver/Register.php";
    public static final String SERVER_URL_LOGIN = "http://ecrowd.000webhostapp.com/ecrowdserver/Login.php";

    public static final String SERVER_URL_REQUEST_DATA_OF_SERVER = "http://ecrowd.000webhostapp.com/ecrowdserver/formDetails.php";

    public Boolean savedToAppServer;

    public DBServerHelper() {



    }

    public boolean checkNetworkConnection(Context context){
        ConnectivityManager connectivityManager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());
    }



    public Boolean saveToAppServer(final String query, Context context){

        Log.i(TAG,"server query at DBServerHelper:) username "+query );
        if (checkNetworkConnection( context)) {
            Log.i(TAG,"NetworkWorks " );

            StringRequest stringRequest =
                    new StringRequest
                            //Start of the constructor paranthesis - StringRequest
                            (Request.Method.POST, this.SERVER_URL,
                                    new Response.Listener<String>() {//response lisner for the request

                                        @Override
                                        public void onResponse(String response) {
                                            JSONObject jsonObject = null;
                                            try {
                                                jsonObject = new JSONObject(response);
                                            }catch(Exception e){

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
                                                savedToAppServer = true;
                                            } else {
//                                                        saveToLocalStorage(name, DbContact.SYNC_STATUS_FAILED);
                                                Log.i(TAG, "Response not recieved as OK");
                                                savedToAppServer = false;
                                            }

                                        }
                                    },

                                    new Response.ErrorListener() {
                                        // error listner for the request as the fourth parameter of the constructor
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
//                                                saveToLocalStorage(name, DbContact.SYNC_STATUS_FAILED);
                                            savedToAppServer = false;Log.i(TAG, "Response not 1");
                                        }
                                    }
                            )
                            //end of the constructor paranthesis - StringRequest

                    {
                        //start of the constructor bocy - StringRequest
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("query", query);
//                            params.put("table_name", form.getTable_name());
//                            params.put("username", form.getUsername());
                            Log.i(TAG,"Inside the String request " );
//                            Log.i(TAG,"Inside the String request "+name );
                            return params;

                        }
                        // end of the body
                    };

            MySingleton.getInstance(context).addToRequestQue(stringRequest);
            Log.i(TAG,"Before else " );
        }else{
//            saveToLocalStorage(name, DbContact.SYNC_STATUS_FAILED);
            savedToAppServer=false;
            Log.i(TAG, "Response not 2");
        }
        Log.i(TAG,"saved or not at DBServerHelper:) "+savedToAppServer );
        return savedToAppServer;
    }
}
