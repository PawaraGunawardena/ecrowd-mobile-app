package com.ecrowd.ecrowd.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ecrowd.ecrowd.data.model.FormGeneral;
import com.ecrowd.ecrowd.data.model.FormPartial;

import java.util.ArrayList;

/**
 * Created by User on 2/17/2018.
 */

public class DynamicFormData {
//Survey form data connection form which interact with the database
    private ArrayList<FormPartial> formPartials;
    private FormGeneral formGeneral;
    private DBHelper crowdDatabaseHelper;
    private SQLiteDatabase db;
private String TAG;
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

    public void insertIntoDynamicFormTable(){
        db.execSQL(getInsertQuery());
        Log.e(TAG, "Inserted th equery in a successful way :)");

    }
    public String getInsertQuery(){
        String query = "Insert INTO "+formGeneral.getTable_name()
                +" VALUES ('"+formGeneral.getUsername()+"'"
                ;
        for(int i=0; i< formPartials.size(); i++){
            query += ", '" + formPartials.get(i).getAttribute_value()+"'";
        }



//        = removeLastComma(query);
        query+=");";
        return query;
    }
}
