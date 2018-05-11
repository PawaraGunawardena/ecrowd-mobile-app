package com.ecrowd.ecrowd.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.ecrowd.ecrowd.R;
import com.ecrowd.ecrowd.data.UserData;
import com.ecrowd.ecrowd.data.model.User;

public class Login extends AppCompatActivity {

    private EditText username_typed;
    private EditText password_typed;

    private String user_name;
    private String password;

    private UserData userDataabseConnectvity;
    private User user ;
    String TAG = "user";

    //attributes to take shhared preferene to make sessions
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String username_saved_shared;
    String password_saved_shared;
    Boolean saveLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_typed = (EditText) findViewById(R.id.editText_username_login);
        password_typed= (EditText) findViewById(R.id.editText_password_login);

        sharedPreferences = getSharedPreferences("userInfo",MODE_PRIVATE);
        username_saved_shared = sharedPreferences.getString("username_shared", "");
        password_saved_shared = sharedPreferences.getString("password_shared", "");


        userDataabseConnectvity = new UserData(this);
        Log.i(TAG,"Created");

        if(username_saved_shared.length()>0 && password_saved_shared.length()>0){
            Intent home = new Intent(this, Home.class);
            user  = (User)userDataabseConnectvity.getUser(username_saved_shared);

            home.putExtra("UserAccount", user);
            Log.i(TAG,user.getUsername()+" passing users username");
            startActivity(home);
            finish();
        }

    }


    public void clicked_login(View view){
        user_name = username_typed.getText().toString();
        password = password_typed.getText().toString();

        user  = (User)userDataabseConnectvity.getUser(user_name);

        //String password2 = userDataabseConnectvity.getPassword(user_name).toString();

        if(user.getPassword().equals(password)) {
            SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username_shared", user_name);
            editor.putString("password_shared", password);
            editor.apply();

            Intent home = new Intent(this, Home.class);
            home.putExtra("UserAccount", user);
            Log.i(TAG,user.getUsername()+" passing users username");

            startActivity(home);
            finish();
        }else{
            Log.i(TAG,password+" given password");
            Log.i(TAG,user.getPassword()+" db password");
            //Log.i(TAG,password);
        }
    }



    public void clicked_create_account(View view){
        Intent signUp = new Intent(this, SignUp.class);
        startActivity(signUp);
        finish();
    }
}
