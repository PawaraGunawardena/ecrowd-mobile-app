package com.ecrowd.ecrowd.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.ecrowd.ecrowd.data.model.FormPartial;
import com.ecrowd.ecrowd.data.model.FormPartials;
import com.ecrowd.ecrowd.data.schema.DynamicFormTableSchema;
import com.ecrowd.ecrowd.data.schema.IFormPartialSchema;

import java.util.ArrayList;

/**
 * Created by User on 3/30/2018.
 */

public class FormDataAccess {
    private DBHelper crowdDatabaseHelper;
    private SQLiteDatabase db;
    private String TAG = "COOOOOOOOOO";
    private FormPartials form_partial;
//    private DynamicFormTableSchema formTableSchema;


    public FormDataAccess(Context context) {
        crowdDatabaseHelper = new DBHelper(context);
        db = crowdDatabaseHelper.getWritableDatabase();
        form_partial = new FormPartials();
//        formTableSchema = new DynamicFormTableSchema();
    }

    public ArrayList get_attribute_titles(String form_name) {
        Cursor form_partial_cursor = db.rawQuery("SELECT attribute_title FROM " + IFormPartialSchema.TABLE_FORM + " WHERE form_name = " + form_name, null);

        final ArrayList<String> attribute_titles = new ArrayList<>();


        if (form_partial_cursor.getCount() == 0) {
//            Toast.makeText(this, "Table was empty", Toast.LENGTH_LONG).show();

        } else {
            while (form_partial_cursor.moveToNext()) {
                attribute_titles.add(form_partial_cursor.getString(1));
            }
        }
        return attribute_titles;
    }
    public ArrayList get_attribute_types(String form_name) {
        Cursor form_partial_cursor = db.rawQuery("SELECT attribute_type FROM " + IFormPartialSchema.TABLE_FORM + " WHERE form_name = '" + form_name+"'", null);

        final ArrayList<String> attribute_type = new ArrayList<>();


        if (form_partial_cursor.getCount() == 0) {
//            Toast.makeText(this, "Table was empty", Toast.LENGTH_LONG).show();

        } else {
            while (form_partial_cursor.moveToNext()) {
                attribute_type.add(form_partial_cursor.getString(1));
            }
        }
        return attribute_type;
    }

    public ArrayList<FormPartial> get_form_partials(String form_name) {
        Cursor form_partial_cursor = db.rawQuery("SELECT * FROM " + IFormPartialSchema.TABLE_FORM + " WHERE form_name = '" + form_name+"'", null);

        final ArrayList<FormPartial> formPartials = new ArrayList<>();


        if (form_partial_cursor.getCount() == 0) {
//            Toast.makeText(this, "Table was empty", Toast.LENGTH_LONG).show();
            Log.i(TAG,"Meauw Meauw");
        } else {
            int iattribute_title;
            int iattribute_type;
            int iform_name;
            int imobility;
            iattribute_title = form_partial_cursor.getColumnIndexOrThrow(IFormPartialSchema.ATTRIBUTE_TITLE);
            iattribute_type = form_partial_cursor.getColumnIndexOrThrow(IFormPartialSchema.ATTRIBUTE_TYPE);
            iform_name = form_partial_cursor.getColumnIndexOrThrow(IFormPartialSchema.FORM_NAME);
            imobility = form_partial_cursor.getColumnIndexOrThrow(IFormPartialSchema.MOBILITY);

            while (form_partial_cursor.moveToNext()) {
                FormPartial formPartial = new FormPartial();
                formPartial.setAttribute_title(form_partial_cursor.getString(iattribute_title));
                formPartial.setAttribute_type(form_partial_cursor.getString(iattribute_type));
                formPartial.setForm_name(form_partial_cursor.getString(iform_name));
                formPartial.setMobility(form_partial_cursor.getString(imobility));

                formPartials.add(formPartial);
            }

        }
        return formPartials;
    }
}
