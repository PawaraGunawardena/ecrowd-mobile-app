package com.ecrowd.ecrowd.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ecrowd.ecrowd.Tab1AllSurveys;
import com.ecrowd.ecrowd.data.DynamicFormData;
import com.ecrowd.ecrowd.data.model.FormGeneral;
import com.ecrowd.ecrowd.data.model.FormPartial;
import com.ecrowd.ecrowd.data.model.User;

import java.util.ArrayList;

public class DynamicFormView extends AppCompatActivity {

//    ArrayList<String> myWidgets = new ArrayList<>();

    RelativeLayout formLayout ;

    private FormGeneral form_general;
    private ArrayList<FormPartial> survey_partials;

    private String survey_name;
    private Intent intent;
    private User user;
    private String TAG = "OOOOOOPS";
    private String TAG2 = "user";

    Context context_current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        formLayout = new RelativeLayout(this);
        formLayout.setBackgroundColor(Color.argb(250, 159, 226, 244));
        context_current = this;
        intent = getIntent();
        //Form Basic Information
        form_general = (FormGeneral) intent.getSerializableExtra("FormBasicInformation");
        //Form Widget Information
        survey_partials = (ArrayList<FormPartial>) intent.getSerializableExtra("selected_survey_partials");
        //set the name of the survey name
        survey_name = form_general.getForm_name();
        //Current User
        user = (User) intent.getSerializableExtra("user");
//        getFormSerializableData();
        Log.e(TAG2, user.getUsername()+ " current dynamic form surrent user name");

        setSurveyFormName();
        setWidgetListToPriview();
        setContentView(formLayout);
    }

    //to keep the simplicity of the code this method seperately work to initialize the data of the form, which passed by the Tab item
    private void getFormSerializableData(){
        //Form Basic Information
        form_general = (FormGeneral) intent.getSerializableExtra("FormBasicInformation");
        //Form Widget Information
        survey_partials = (ArrayList<FormPartial>) intent.getSerializableExtra("selected_survey_partials");
        //set the name of the survey name
        survey_name = form_general.getForm_name();
        //Current User
        user = (User) intent.getSerializableExtra("user");
    }

    private void setSurveyFormName(){
        //preview the survey name
        TextView nameText = new TextView(this);
        nameText.setId(1);
        nameText.setText(survey_name);
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200, getResources().getDisplayMetrics());
        nameText.setWidth(px);

        RelativeLayout.LayoutParams txtDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        txtDetails.addRule(RelativeLayout.ABOVE);
        txtDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        txtDetails.setMargins(20,20,20,20);

        formLayout.addView(nameText, txtDetails);
    }

    private void setWidgetListToPriview() {
        int y=2;
        //preview the widgets
        for (int x = 0; x < survey_partials.size(); x++) {
//            FormPartial previous_partial = survey_partials.get(x-1);
            FormPartial instant_partial = survey_partials.get(x);
            String instant_partial_type = instant_partial.getAttribute_type();
            if (instant_partial_type.equals("Button") ){
            }else if (instant_partial_type.equals("Text")) {
//                createTextView(y, instant_partial.getAttribute_title());
//                y+=1;
                instant_partial.setId(y);



//                instant_partial.setId(y);
                createEditText(y, instant_partial.getAttribute_title());
                y+=1;

            }
        }
        createButton( y);
    }

    public void createEditText(int y, String hint){
        EditText nameText = new EditText(this);
//        nameText.setText("Type Here");
        nameText.setHint(hint);
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200, getResources().getDisplayMetrics());
        nameText.setWidth(px);
        nameText.setId(y);

        RelativeLayout.LayoutParams txtDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        txtDetails.addRule(RelativeLayout.BELOW, y-1);
        txtDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        txtDetails.setMargins(0,10,0,10);

        formLayout.addView(nameText, txtDetails);
    }

    public void createTextView(int y, String value){
        TextView nameText = new TextView(this);
        nameText.setText(value);
        nameText.setId(y);
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200, getResources().getDisplayMetrics());
        nameText.setWidth(px);

        RelativeLayout.LayoutParams txtDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        txtDetails.addRule(RelativeLayout.BELOW,(y-1));
        txtDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        txtDetails.setMargins(0,10,0,10);

        formLayout.addView(nameText, txtDetails);
    }

    public void createRadioButton(int y){
        RadioButton button = new RadioButton(this);
        button.setText("Submit");
        RelativeLayout.LayoutParams btnDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        button.setId(y);
        if(y >= 2 ){
            btnDetails.addRule(RelativeLayout.BELOW);

        }else{

            btnDetails.addRule(RelativeLayout.CENTER_VERTICAL);
        }
        btnDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);

        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200, getResources().getDisplayMetrics());

        button.setWidth(px);
        btnDetails.setMargins(0,200,0,20);
        formLayout.addView(button, btnDetails);
    }

    public void createButton(int y){

        Button button = new Button(this);
        button.setText("Submit");
        button.setId(y);
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200, getResources().getDisplayMetrics());
        button.setWidth(px);



        RelativeLayout.LayoutParams btnDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        btnDetails.addRule(RelativeLayout.BELOW, y-1);
//        btnDetails.addRule(RelativeLayout.ALIGN_BOTTOM);
        btnDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        btnDetails.setMargins(0,10,0,10);

        formLayout.addView(button, btnDetails);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.e(TAG, "Button Clicked !!!");
                TextView tv = (TextView) findViewById(1);
                Log.e(TAG, (String) tv.getText());

                int y = 2;
                for (int x = 0; x < survey_partials.size(); x++) {
                    FormPartial instant_partial = survey_partials.get(x);
                    int id = instant_partial.getId();

                    String instant_partial_type = instant_partial.getAttribute_type();
                    if (instant_partial_type.equals("Button")){

                    } else if (instant_partial_type.equals("Text")) {
                        EditText editText = (EditText) findViewById(id);
                        instant_partial.setAttribute_value(String.valueOf(editText.getText()));
                        y += 1;
                    }

                }
                DynamicFormData dynamicFormData = new DynamicFormData(survey_partials,form_general,DynamicFormView.this);

//                Log.e(TAG, dynamicFormData.getInsertQuery(user.getUsername(), form_general.getTable_name()));
                dynamicFormData.insertIntoDynamicFormTable(user, form_general, context_current);
                Intent home = new Intent(DynamicFormView.this, Home.class);
                home.putExtra("UserAccount", user);
                startActivity(home);
            }



        });
    }
}
