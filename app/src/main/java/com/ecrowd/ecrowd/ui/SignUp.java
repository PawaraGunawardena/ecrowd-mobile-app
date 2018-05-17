package com.ecrowd.ecrowd.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ecrowd.ecrowd.LoginActivityPresenter;
import com.ecrowd.ecrowd.R;
import com.ecrowd.ecrowd.SignUpActivityPresenter;
import com.ecrowd.ecrowd.SignUpActivityView;
import com.ecrowd.ecrowd.data.UserData;
import com.ecrowd.ecrowd.data.dbserver.DBServerHelper;
import com.ecrowd.ecrowd.data.dbserver.MySingleton;
import com.ecrowd.ecrowd.data.dbserver.RegisterRequest;
import com.ecrowd.ecrowd.data.model.User;
import com.ecrowd.ecrowd.ui.Login;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class SignUp extends AppCompatActivity implements SignUpActivityView {

    private EditText username_typed;
    private EditText email_typed;
    private EditText firstname_typed;
    private EditText last_name_typed;
    private EditText password_typed;
    private EditText password_confirm_typed;

    private String user_name;
    private String email;
    private String first_name;
    private String last_name;
    private String password;

    private UserData userDatabaseConnector;

    //For testing
    SignUpActivityPresenter presenter;

String TAG = "PIXXEK";

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        user = new User();

        username_typed = (EditText) findViewById(R.id.editText_usernme_signup);
        email_typed= (EditText) findViewById(R.id.editText_email_signup);
        firstname_typed= (EditText) findViewById(R.id.editText_Firstname_signup);
        last_name_typed= (EditText) findViewById(R.id.editText_lastname_signup);
        password_typed= (EditText) findViewById(R.id.editText_password_signup);
        password_confirm_typed= (EditText) findViewById(R.id.editTextconfirm_password_signup);

//        this.gestureDetectorCompat = new GestureDetectorCompat(this,this);
//        gestureDetectorCompat.setOnDoubleTapListener(this);

        userDatabaseConnector = new UserData(this);

         presenter = new SignUpActivityPresenter(this);

    }

    private void get_typed_values(){
        user_name = username_typed.getText().toString();
        email = email_typed.getText().toString();
        first_name = firstname_typed.getText().toString();
        last_name = last_name_typed.getText().toString();
        password = password_typed.getText().toString();
    }

    private void setUser(){
        user.setUsername(user_name);
        user.setEmail(email);
        user.setLast_name(last_name);
        user.setFirstname(first_name);
        user.setPassword(password);
    }

    public void button_submit_signup_clicked(View view){




        if (checkNetworkConnection(this)) {

            get_typed_values();
            setUser();


            StringRequest stringRequest =
                    new StringRequest
                            //Start of the constructor paranthesis - StringRequest
                            (Request.Method.POST, DBServerHelper.SERVER_URL_REGISTER,
                                    new Response.Listener<String>() {//response lisner for the request

                                        @Override
                                        public void onResponse(String response) {
                                            JSONObject jsonObject = null;
                                            try {
                                                jsonObject = new JSONObject(response);
                                            } catch (Exception e) {
                                                Log.i(TAG, e.getMessage()+" JSON response "+response);
                                            }

//                                            String Response = null;
                                            boolean success=false;
                                            try {
//                                                JSONObject jsonResponse = new JSONObject(response);
                                                success = jsonObject.getBoolean("success");
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                                Log.i(TAG, e.getMessage()+"SUCESS");
                                            }

                                            if (success== true) {
//                                                        saveToLocalStorage(name, DbContact.SYNC_STATUS_OK);
                                                userDatabaseConnector.addUser(user);
//                                                Intent login = new Intent(SignUp.this, Login.class);
//        Intent login = new Intent(this, Face.class);
//                                                startActivity(login);

                                                presenter.launchSignInRegisterBtnClicked(Login.class);
                                                finish();
                                                Log.i(TAG, "OK ");
//                                                dataForm.addForm(dynamcForm,1);
                                            } else {
//                                                        saveToLocalStorage(name, DbContact.SYNC_STATUS_FAILED);
                                                Log.i(TAG, "Response not recieved as OK");
//                                                dataForm.addForm(dynamcForm,0);
                                            }

                                        }
                                    },

                                    new Response.ErrorListener() {
                                        // error listner for the request as the fourth parameter of the constructor
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
//                                                saveToLocalStorage(name, DbContact.SYNC_STATUS_FAILED);
//                                            dataForm.addForm(dynamcForm,0);

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

                            params.put("username", user.getUsername());
                            params.put("email", user.getEmail());
                            params.put("first_name", user.getFirstname());
                            params.put("last_name", user.getLastname());
                            params.put("password", user.getPassword());
                            params.put("role", "user");


                            Log.i(TAG, "getUsername " +user.getUsername());
                            Log.i(TAG, "getEmail  :"+user.getEmail());
                            Log.i(TAG, "getFirstname   :"+user.getFirstname());
                            Log.i(TAG, "getLastname " +user.getLastname());
                            Log.i(TAG, "getPassword  :"+user.getPassword());
                            Log.i(TAG, "role :"+"user");

                            return params;

                        }
                        // end of the body
                    };

            MySingleton.getInstance(SignUp.this).addToRequestQue(stringRequest);

        } else {
//            saveToLocalStorage(name, DbContact.SYNC_STATUS_FAILED);
//            dataForm.addForm(dynamcForm,0);
            Log.i(TAG, "Response not 2");
            AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
            builder.setMessage("Conncet To the Internet")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();
        }
















//        if(checkNetworkConnection(this)) {
//            get_typed_values();
//            setUser();
//            userDatabaseConnector.addUser(user);
//
//
//
//
//            final String username2 = user.getUsername();
//            final String email2 = user.getEmail();
//            final String first_name2= user.getFirstname();
//            final String last_name2= user.getLastname();
//
//            final String password2 =user.getPassword();
//
//            Response.Listener<String> responseListener = new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    try {
//                        JSONObject jsonResponse = new JSONObject(response);
//                        boolean success = jsonResponse.getBoolean("success");
//                        if (success) {
////                            Intent intent = new Intent(SignUp.this, Login.class);
////                            Signup.this.startActivity(intent);
//
//                            Intent login = new Intent(SignUp.this, Login.class);
////        Intent login = new Intent(this, Face.class);
//                            startActivity(login);
//                            finish();
//                        } else {
//                            AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
//                            builder.setMessage("Register Failed")
//                                    .setNegativeButton("Retry", null)
//                                    .create()
//                                    .show();
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            };
//
//            RegisterRequest registerRequest = new RegisterRequest(username2, email2, first_name2,last_name2, password2, responseListener);
//            RequestQueue queue = Volley.newRequestQueue(SignUp.this);
//            queue.add(registerRequest);
//
//
//
//
//
//
//
//        }else{
//            AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
//            builder.setMessage("Please Connect With the Internet")
//                    .setNegativeButton("Retry", null)
//                    .create()
//                    .show();
//        }

    }

    public void button_login_signup_clicked(View view){

//        Intent login = new Intent(this, Login.class);
//        startActivity(login);

        presenter.launchLoginPageBtnClicked(Login.class);
        finish();
    }

    public boolean checkNetworkConnection(Context context){
        ConnectivityManager connectivityManager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());
    }

    @Override
    public void launchLoginPage(Class activity) {
        Intent login = new Intent(this, Login.class);
        startActivity(login);
    }

    @Override
    public void registerBySignUp(Class activity) {
        Intent login = new Intent(this, Login.class);
        startActivity(login);
    }
}
