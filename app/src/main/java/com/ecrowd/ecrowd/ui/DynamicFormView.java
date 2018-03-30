package com.ecrowd.ecrowd.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecrowd.ecrowd.data.model.FormGeneral;
import com.ecrowd.ecrowd.data.model.FormPartial;

import java.util.ArrayList;

public class DynamicFormView extends AppCompatActivity {

    ArrayList<String> myWidgets = new ArrayList<>();

    RelativeLayout formLayout ;

    private FormGeneral form_general;
    private ArrayList<FormPartial> survey_partials;

    String myItems;
    private String survey_name;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        formLayout = new RelativeLayout(this);
        formLayout.setBackgroundColor(Color.argb(12,12,13,12));

        intent = getIntent();
        getFormSerializableData();

        setSurveyFormName();
        setWidgetListToPriview();
//        String[] myListWidgets = myItems.split(",");
//        for (int x = 0; x < myListWidgets.length; x++) {
//            myWidgets.add(0, myListWidgets[0]);
//            myWidgets.add(1, myListWidgets[1]);
//        }
//        createTextView(0, survey_name);
//
//        for (int x = 0; x < myWidgets.size(); x++){
//            if (myWidgets.get(x).equals("Button") ){
//                createButton( x+2 );
//            }else if (myWidgets.get(x).equals("Text")) {
//                createEditText(x+2);
//            }
//        }
        setContentView(formLayout);
    }

    //to keep the simplicity of the code this method seperately work to initialize the data of the form, which passed by the Tab item
    private void getFormSerializableData(){
        //Testing String
        myItems = (String) intent.getSerializableExtra("itemList");

        //Form Basic Information
        form_general = (FormGeneral) intent.getSerializableExtra("FormBasicInformation");

        //Form Widget Information
        survey_partials = (ArrayList<FormPartial>) intent.getSerializableExtra("selected_survey_partials");

        //set the name of the survey name
        survey_name = form_general.getForm_name();
    }

    private void setSurveyFormName(){
        //preview the survey name
        createTextView(0, survey_name);
    }

    private void setWidgetListToPriview() {

        //preview the widgets
        for (int x = 0; x < survey_partials.size(); x++) {
            FormPartial instant_partial = survey_partials.get(x);
            String instnt_partial_type = instant_partial.getAttribute_type();
            if (instnt_partial_type.equals("Button") ){
                createButton( x+2 );
            }else if (instnt_partial_type.equals("Text")) {
                createEditText(x+2);
            }
        }
    }

    public void createButton(int y){
        Button red = new Button(this);
        red.setText("Submit");
        RelativeLayout.LayoutParams btnDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        red.setId(y);
        if(y >= 2 ){
            btnDetails.addRule(RelativeLayout.BELOW);

        }else{

            btnDetails.addRule(RelativeLayout.CENTER_VERTICAL);
        }
        btnDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);

        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200, getResources().getDisplayMetrics());

        red.setWidth(px);
        btnDetails.setMargins(0,200,0,20);
        formLayout.addView(red, btnDetails);
    }

    public void createEditText(int y){
        EditText nameText = new EditText(this);
        nameText.setText("Type Here");
        RelativeLayout.LayoutParams txtDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        nameText.setId(y);

        if(y >= 2 ){
            txtDetails.addRule(RelativeLayout.BELOW, y-1);
        }else{
            txtDetails.addRule(RelativeLayout.CENTER_VERTICAL);
        }
        txtDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);

        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200, getResources().getDisplayMetrics());
        nameText.setWidth(px);

        txtDetails.setMargins(0,50,0,20);
        formLayout.addView(nameText, txtDetails);

    }

    public void createTextView(int y, String value){
        TextView nameText = new TextView(this);
        nameText.setText(value);
        RelativeLayout.LayoutParams txtDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        nameText.setId(y);

        if(y >= 2 ){
            txtDetails.addRule(RelativeLayout.ABOVE,(y-1));
        }else{
            txtDetails.addRule(RelativeLayout.ABOVE);
        }
        txtDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);

        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200, getResources().getDisplayMetrics());
        nameText.setWidth(px);

        txtDetails.setMargins(0,20,0,20);
        formLayout.addView(nameText, txtDetails);
    }

}
