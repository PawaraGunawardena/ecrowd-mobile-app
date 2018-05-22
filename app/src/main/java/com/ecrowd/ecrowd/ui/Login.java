package com.ecrowd.ecrowd.ui;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ecrowd.ecrowd.LoginActivityPresenter;
import com.ecrowd.ecrowd.LoginActivityView;
import com.ecrowd.ecrowd.R;
import com.ecrowd.ecrowd.data.UserData;
import com.ecrowd.ecrowd.data.dbserver.DBServerHelper;
import com.ecrowd.ecrowd.data.dbserver.MySingleton;
import com.ecrowd.ecrowd.data.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements LoginActivityView {

    private EditText username_typed;
    private EditText password_typed;

    private String user_name;
    private String password;

    private UserData userDataabseConnectvity;
    private User user;
    String TAG = "Lion";

    //attributes to take shhared preferene to make sessions
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String username_saved_shared;
    String password_saved_shared;
    Boolean saveLogin;

    BroadcastReceiver broadcastReceiver;

    //testing stuffs
    LoginActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginActivityPresenter(this);

        username_typed = (EditText) findViewById(R.id.editText_username_login);
        password_typed = (EditText) findViewById(R.id.editText_password_login);

        sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        username_saved_shared = sharedPreferences.getString("username_shared", "");
        password_saved_shared = sharedPreferences.getString("password_shared", "");


        userDataabseConnectvity = new UserData(this);
        Log.i(TAG, "Created");


        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };

        if (username_saved_shared.length() > 0 && password_saved_shared.length() > 0) {
            Intent home = new Intent(this, Home.class);
            user = (User) userDataabseConnectvity.getUser(username_saved_shared);

            home.putExtra("UserAccount", user);
            Log.i(TAG, user.getUsername() + " passing users username");
            startActivity(home);
            finish();
        }

    }


    public void clicked_login(View view) {
        user_name = username_typed.getText().toString();
        password = password_typed.getText().toString();
        Log.i(TAG, "Usrr is the " + user_name);

        if (checkNetworkConnection(this)) {
            Log.i(TAG, "Internet works " + user_name);
            StringRequest stringRequest =
                    new StringRequest
                            //Start of the constructor paranthesis - StringRequest
                            (Request.Method.POST, DBServerHelper.SERVER_URL_LOGIN,
                                    new Response.Listener<String>() {//response lisner for the request

                                        @Override
                                        public void onResponse(String response) {
                                            JSONObject jsonObject = null;
                                            Log.i(TAG, "Tr y to get response " + user_name);
                                            try {
                                                jsonObject = new JSONObject(response);
                                            } catch (Exception e) {
                                                Log.i(TAG, e.getMessage() + " JSON response " + response);
                                            }

//                                            String Response = null;
                                            boolean success = false;
                                            try {
//                                                JSONObject jsonResponse = new JSONObject(response);
                                                success = jsonObject.getBoolean("success");
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                                Log.i(TAG, e.getMessage() + "SUCESS");
                                            }

                                            if (success == true) {
//                                                        saveToLocalStorage(name, DbContact.SYNC_STATUS_OK);

                                                try {
                                                    user = (User) userDataabseConnectvity.getUser(user_name);
                                                    if (user == null) {
                                                        String username1 = jsonObject.getString("username");
                                                        String email1 = jsonObject.getString("email");
                                                        String first_name1 = jsonObject.getString("first_name");
                                                        String last_name1 = jsonObject.getString("last_name");
                                                        String password1 = jsonObject.getString("password");
                                                        String role1 = jsonObject.getString("role");

                                                        UserData userData = new UserData(Login.this);
                                                        User user1 = new User();
                                                        user1.setUsername(username1);
                                                        user1.setFirstname(first_name1);
                                                        user1.setPassword(password1);
                                                        user1.setLast_name(last_name1);
                                                        user1.setEmail(email1);

                                                        userData.addUser(user1);
                                                        Log.i(TAG, "username1 is the " + username1);
                                                        Log.i(TAG, "email1 is the " + email1);
                                                        Log.i(TAG, "first_name1 is the " + first_name1);
                                                        Log.i(TAG, "last_name1 is the " + last_name1);
                                                        Log.i(TAG, "password1 " + password1);
                                                    }

                                                    SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                                    editor.putString("username_shared", user_name);
                                                    editor.putString("password_shared", password);
                                                    editor.apply();

                                                    Intent home = new Intent(Login.this, Home.class);
                                                    home.putExtra("UserAccount", user);
                                                    Log.i(TAG, user.getUsername() + " passing users username");

                                                    startActivity(home);
//                                                        presenter.launchHomePageBtnClicked(Home.class);
                                                    finish();


                                                } catch (JSONException e) {
                                                    Log.i(TAG, " is the error " + e.getMessage());
                                                }


                                            } else {
//
                                                Log.i(TAG, "Response not 2");
                                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                                builder.setMessage("Wrong Credentials for Login. Try Again .")
                                                        .setNegativeButton("Retry", null)
                                                        .create()
                                                        .show();
                                            }

                                        }
                                    },

                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                            Log.i(TAG, "Response not 1");
                                            Log.i(TAG, "Response not 1" + error.getMessage());
                                        }
                                    }
                            )
                            //end of the constructor paranthesis - StringRequest

                    {
                        //start of the constructor bocy - StringRequest
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();

                            params.put("username", user_name);
                            params.put("password", password);
//                            params.put("role", "user");

                            Log.i(TAG, "params " + user_name + " Pass " + password);
                            return params;

                        }
                        // end of the body
                    };

            MySingleton.getInstance(Login.this).addToRequestQue(stringRequest);

        } else {

            Log.i(TAG, "Response not 2");
            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
            builder.setMessage("Conncet To the Internet")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();
        }


//            if (user.getPassword().equals(password)) {
//
//            } else {
//                Log.i(TAG, password + " given password");
//                Log.i(TAG, user.getPassword() + " db password");
//                //Log.i(TAG,password);
//            }


//        final String username_server = user_name;
//        final String password_server = password;
//
//        if(checkNetworkConnection(Login.this)){
//
//
//            // Response received from the server
//            Response.Listener<String> responseListener = new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    try {
//                        JSONObject jsonResponse = new JSONObject(response);
//                        boolean success = jsonResponse.getBoolean("success");
//
//                        if (success) {
//                            String first_name2 =jsonResponse.getString("first_name");
//                            String last_name2 = jsonResponse.getString("last_name");
//                            String email2 =jsonResponse.getString("email");
//
//
//                            SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.putString("username_shared", user_name);
//                            editor.putString("password_shared", password);
//                            editor.apply();
//
//                            Intent home = new Intent(Login.this, Home.class);
//                            user.setFirstname(first_name2);
//                            user.setEmail(email2);
//                            user.setLast_name(last_name2);
//                            user.setPassword(password_server);
//                            user.setUsername(username_server);
//                            home.putExtra("UserAccount", user);
//                            Log.i(TAG,user.getUsername()+" passing users username");
//
//                            startActivity(home);
//                            finish();
//
//                        } else {
//                            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
//                            builder.setMessage("Login Failed")
//                                    .setNegativeButton("Retry", null)
//                                    .create()
//                                    .show();
//                        }
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            };
//
//            LoginRequest loginRequest = new LoginRequest(username_server, password_server, responseListener);
//            RequestQueue queue = Volley.newRequestQueue(Login.this);
//            queue.add(loginRequest);
//        }
//        else{
//            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
//            builder.setMessage("Login Failed. Check Internet Connection.")
//                    .setNegativeButton("Retry", null)
//                    .create()
//                    .show();
//        }
    }


    public void clicked_create_account(View view) {
//        Intent signUp = new Intent(this, SignUp.class);
//        startActivity(signUp);

        presenter.launchSignupBtnClicked(SignUp.class);
        finish();

    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(broadcastReceiver, new IntentFilter());
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(broadcastReceiver);
    }

    public boolean checkNetworkConnection(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


    //For test
    @Override
    public void launchSignInPage(Class activity) {
        Intent intent = new Intent(Login.this, activity);
        startActivity(intent);


    }

    @Override
    public void launchHomePage(Class activity) {
        Intent intent = new Intent(Login.this, activity);
        startActivity(intent);
    }
}
