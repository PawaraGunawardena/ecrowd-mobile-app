package com.ecrowd.ecrowd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ecrowd.ecrowd.data.UserData;
import com.ecrowd.ecrowd.data.model.User;
import com.ecrowd.ecrowd.ui.Login;



public class SignUp extends AppCompatActivity {

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
        get_typed_values();
        setUser();
        userDatabaseConnector.addUser(user);
        Intent login = new Intent(this, Login.class);
//        Intent login = new Intent(this, Face.class);
        startActivity(login);
    }

    public void button_login_signup_clicked(View view){
        Intent login = new Intent(this, Login.class);
        startActivity(login);
    }
}
