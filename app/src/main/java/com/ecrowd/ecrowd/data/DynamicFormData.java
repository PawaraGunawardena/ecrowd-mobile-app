package com.ecrowd.ecrowd.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.ecrowd.ecrowd.data.model.FormGeneral;
import com.ecrowd.ecrowd.data.model.FormPartial;
import com.ecrowd.ecrowd.data.model.User;
import com.ecrowd.ecrowd.data.schema.IFormSchema;

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
    private String TAG = "CROCODE";

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

    public void insertIntoDynamicFormTable(User user, FormGeneral form_general){
        try {
            db.execSQL(getInsertQuery(user.getUsername(), form_general.getTable_name()));
            Log.e(TAG, "Inserted th equery in a successful way :)");
        }catch(Exception e){
            Log.e(TAG, formGeneral.getUsername()+" username and "+ formGeneral.getTable_name()+" table name");
//            Log.e(TAG, getInsertQuery());
            Log.e(TAG, e.getMessage());
        }
    }
    public String getInsertQuery(String username, String tablename){
        String query = "Insert INTO "+tablename
                +" VALUES ('"+username+"'"
                ;
        for(int i=0; i< formPartials.size(); i++){
            query += ", '" + formPartials.get(i).getAttribute_value()+"'";
        }



//        = removeLastComma(query);
        query+=");";
        return query;
    }


    public ArrayList<String> getDynamicFormDataCollect(FormGeneral formGeneral, ArrayList<FormPartial> formPartials){
        String tableName = formGeneral.getTable_name();

        String query = "SELECT * FROM "+tableName;
        Cursor formDataCursor = db.rawQuery(query, null);
        ArrayList<String> typed_values  = new ArrayList<>();

        if(formDataCursor.getCount() == 0){
//            Toast.makeText(this.getContext(), "Table was empty", Toast.LENGTH_LONG).show();

        }else{
            while(formDataCursor.moveToNext()) {

                for (int index = 0; index < formPartials.size()+1; index++)
                    typed_values.add(formDataCursor.getString(index));
            }

        }
        return typed_values;
//        return formName;
//        db.execSQL(query);
    }
}
