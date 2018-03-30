package com.ecrowd.ecrowd.ui;

import android.content.Intent;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_typed = (EditText) findViewById(R.id.editText_username_login);
        password_typed= (EditText) findViewById(R.id.editText_password_login);

        userDataabseConnectvity = new UserData(this);
        Log.i(TAG,"Created");

    }

    public void clicked_login(View view){
        user_name = username_typed.getText().toString();
        password = password_typed.getText().toString();

        user  = (User)userDataabseConnectvity.getUser(user_name);

        //String password2 = userDataabseConnectvity.getPassword(user_name).toString();

        if(user.getPassword().equals(password)) {
            Intent home = new Intent(this, Home.class);

            home.putExtra("UserAccount", user);
            startActivity(home);
        }else{
            Log.i(TAG,password+" given password");
            Log.i(TAG,user.getPassword()+" db password");
            //Log.i(TAG,password);
        }
    }

    public void clicked_create_account(View view){
        Intent signUp = new Intent(this, SignUp.class);
        startActivity(signUp);
    }
}
