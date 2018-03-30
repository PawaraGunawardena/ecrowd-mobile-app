package com.ecrowd.ecrowd.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ecrowd.ecrowd.data.model.FormGeneral;
import com.ecrowd.ecrowd.data.schema.IFormSchema;

import java.util.ArrayList;

/**
 * Created by User on 3/30/2018.
 */

public class FormGeneralData {
    private DBHelper crowdDatabaseHelper;
    private SQLiteDatabase db;
    private String TAG = "COOOOOOOOOO";
    private FormGeneral form_general;

//    private DynamicFormTableSchema formTableSchema;


    public FormGeneralData(Context context) {
        crowdDatabaseHelper = new DBHelper(context);
        db = crowdDatabaseHelper.getWritableDatabase();
        form_general = new FormGeneral();
//        formTableSchema = new DynamicFormTableSchema();
    }

    public FormGeneral get_form_general(String form_name) {
        Cursor form_general_cursor = db.rawQuery("SELECT attribute_type FROM " + IFormSchema.TABLE_FORM + " WHERE form_name = '" + form_name+"'", null);
        if (form_general_cursor.getCount() == 0) {
//            Toast.makeText(this, "Table was empty", Toast.LENGTH_LONG).show();
        } else {
            Log.i(TAG,"Meauw Meauw 1");
            int iform_name;
            int itable_name;
            int istarting_date;
            int iclosing_date;
            int iusername;
            iform_name = form_general_cursor.getColumnIndexOrThrow(IFormSchema.FORM_NAME);
            itable_name = form_general_cursor.getColumnIndexOrThrow(IFormSchema.TABLE_NAME);
            istarting_date = form_general_cursor.getColumnIndexOrThrow(IFormSchema.STARTING_DATE);
            iclosing_date = form_general_cursor.getColumnIndexOrThrow(IFormSchema.CLOSING_DATE);
            iusername =form_general_cursor.getColumnIndexOrThrow(IFormSchema.USER_NAME);
            Log.i(TAG,"Meauw Meauw 2");
            form_general = new FormGeneral();
            form_general.setForm_name(form_general_cursor.getString(iform_name));
            form_general.setTable_name(form_general_cursor.getString(itable_name));
            form_general.setClosing_date(form_general_cursor.getString(iclosing_date));
            form_general.setStarting_date(form_general_cursor.getString(istarting_date));
            form_general.setUsername(form_general_cursor.getString(iusername));
            Log.i(TAG,"Meauw Meauw 3");
        }
        return form_general;
    }

    public FormGeneral get_form_general(String form_name,String username) {

        Cursor form_general_cursor = db.rawQuery("SELECT * FROM " + IFormSchema.TABLE_FORM +
                " WHERE form_name = '" + form_name+
                "' AND username ='"+username+"'",
                null);
        if (form_general_cursor.getCount() == 0) {
//            Toast.makeText(this, "Table was empty", Toast.LENGTH_LONG).show();
        } else {Log.i(TAG,"Meauw Meauw  11 "+ form_name+" "+username+" count "+form_general_cursor.getCount() );
            int iform_name;
            int itable_name;
            int istarting_date;
            int iclosing_date;
            int iusername;
            iform_name = form_general_cursor.getColumnIndexOrThrow(IFormSchema.FORM_NAME);
            itable_name = form_general_cursor.getColumnIndexOrThrow(IFormSchema.TABLE_NAME);
            istarting_date = form_general_cursor.getColumnIndexOrThrow(IFormSchema.STARTING_DATE);
            iclosing_date = form_general_cursor.getColumnIndexOrThrow(IFormSchema.CLOSING_DATE);
            iusername =form_general_cursor.getColumnIndexOrThrow(IFormSchema.USER_NAME);
            Log.i(TAG,"Meauw Meauw  12 " +iform_name+" "+itable_name+" "+iusername);
            while (form_general_cursor.moveToNext()) {
            form_general = new FormGeneral();
            form_general.setForm_name(form_general_cursor.getString(iform_name));
            form_general.setTable_name(form_general_cursor.getString(itable_name));
            form_general.setClosing_date(form_general_cursor.getString(iclosing_date));
            form_general.setStarting_date(form_general_cursor.getString(istarting_date));
            form_general.setUsername(form_general_cursor.getString(iusername));Log.i(TAG,"Meauw Meauw 3");
        }

    }return form_general;
    }
}
