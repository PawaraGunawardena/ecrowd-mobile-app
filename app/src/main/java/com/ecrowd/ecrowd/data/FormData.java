package com.ecrowd.ecrowd.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ecrowd.ecrowd.data.dbserver.DBServerHelper;
import com.ecrowd.ecrowd.data.dbserver.FormGeneralServer;
import com.ecrowd.ecrowd.data.model.Form;
import com.ecrowd.ecrowd.data.model.FormGeneral;
import com.ecrowd.ecrowd.data.model.User;
import com.ecrowd.ecrowd.data.schema.DynamicFormTableSchema;
import com.ecrowd.ecrowd.data.schema.IFormPartialSchema;
import com.ecrowd.ecrowd.data.schema.IFormSchema;
import com.ecrowd.ecrowd.data.schema.IUserSchema;

/**
 * Created by User on 2/10/2018.
 */

public class FormData {

    private DBHelper crowdDatabaseHelper;
    private SQLiteDatabase db;
    private String TAG= "WAAAAAAAAAAR";
    private String TAG1= "BAwwa";
    private DynamicFormTableSchema formTableSchema;

    DBServerHelper dbServerHelper;
    FormGeneralServer formGeneralServer;

    public FormData(Context context) {
        crowdDatabaseHelper = new DBHelper(context);
        db = crowdDatabaseHelper.getWritableDatabase();
        formTableSchema = new DynamicFormTableSchema();

        dbServerHelper = new DBServerHelper();
        formGeneralServer = new FormGeneralServer();
    }

    public void addForm(Form form, int sync_status) {
//        Log.i(TAG,"going to insert :) :)");
//        String serverQuery = formGeneralServer.getFormGeneralString(form);
//        Log.i(TAG,"server query :) "+serverQuery );
//        Boolean insertedToTheServer = dbServerHelper.saveToAppServer(form , context);
//        Log.i(TAG,"Inserted to the server :) ? "+insertedToTheServer );

        ContentValues values_table_form = new ContentValues();
        values_table_form.put(IFormSchema.FORM_NAME, form.getForm_name());
        values_table_form.put(IFormSchema.TABLE_NAME, form.getTable_name());
        values_table_form.put(IFormSchema.STARTING_DATE, form.getStarting_date());
        values_table_form.put(IFormSchema.CLOSING_DATE, form.getClosing_date());
        values_table_form.put(IFormSchema.USER_NAME, form.getUsername());

//        if(insertedToTheServer==true){
//            values_table_form.put(IFormSchema.SYNC_STATUS, 1);
//            db.insert(IFormSchema.TABLE_FORM, null, values_table_form);
//            Log.i(TAG,"Form insert after server call :) :)");
//        }else{
            values_table_form.put(IFormSchema.SYNC_STATUS, sync_status);
            db.insert(IFormSchema.TABLE_FORM, null, values_table_form);
            Log.i(TAG,"Form insert without server call :) :)");
//        }







        Log.i(TAG,"Form Inserted Really:) :)");
//        addFormPartials(Form form);
        addFormPartials(form, sync_status);
        formTableSchema.setAttributes(form.getAttribute_titles());
        formTableSchema.setFormTableName(form.getTable_name());

        String query = String.valueOf(formTableSchema.getQueryCreateDynamicFormTable());
        Log.i(TAG,"Table query : "+query);
        Log.i(TAG,"Going to add the table");
        addTable(query);
        Log.i(TAG,"Created the table");
    }

    public void saveToLocalStorage(){

    }

    public void addFormPartials(Form form, int sync_status) {

        Log.i(TAG,"Partial goig to insert");
        Log.i(TAG,"form.getAttribute_titles().size() "+form.getAttribute_titles().size());
        for(int i=0 ; i<form.getAttribute_titles().size(); i++){
            Log.i(TAG,"Partial loop started i = "+i);
//            Log.i(TAG,"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" +
//                    "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX     " +
//                     form.getAttribute_titles().size() +
//                    "         XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            ContentValues values_table_form_partial = new ContentValues();

            values_table_form_partial.put(IFormPartialSchema.FORM_NAME, form.getForm_name());
            values_table_form_partial.put(IFormPartialSchema.ATTRIBUTE_TITLE, form.getAttribute_titles().get(i));
            values_table_form_partial.put(IFormPartialSchema.ATTRIBUTE_TYPE, form.getAttribute_type().get(i));
            values_table_form_partial.put(IFormPartialSchema.MOBILITY, "false");
            values_table_form_partial.put(IFormPartialSchema.SYNC_STATUS, sync_status);
//            values_table_form_partial.put(IFormPartialSchema.DEFAULT_VALUE, "testing");

            db.insert(IFormPartialSchema.TABLE_FORM, null, values_table_form_partial);

            Log.i(TAG,"Partial Inserted Really");
        }
    }

    public void addTable(String query ){
        try{
            db.execSQL(query);
        }catch (Exception e){
            Log.i(TAG,e.getMessage());
        }
    }

    public Form getFormPrimeInfo(int user_id){
        final String selectionArgs[] = {String.valueOf(user_id)};
        final String selection = IFormSchema.USER_NAME + " = ? ";
        Form form = null;
        Cursor cursor = db.query(
                IFormSchema.TABLE_FORM,
                IFormSchema.FORM_COLUMNS,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                form = cursorToEntity(cursor);
                cursor.moveToNext();
            }
            cursor.close();

        }
        return form;
    }

    private Form cursorToEntity(Cursor cursor) {
        String user_name = null;
        String email = null;
        String first_name = null;
        String last_name = null;
        String password = null;

        int iuser_name;
        int iemail;
        int ifirst_name;
        int ilast_name;
        int ipassword;

        if(cursor != null ){
            if(cursor.getColumnIndex(IUserSchema.COLUMN_USER_NAME) !=-1){
                iuser_name = cursor.getColumnIndexOrThrow(IUserSchema.COLUMN_USER_NAME);
                user_name = cursor.getString(iuser_name);
            }
            if(cursor.getColumnIndex(IUserSchema.COLUMN_EMAIL) !=-1){
                iemail = cursor.getColumnIndexOrThrow(IUserSchema.COLUMN_EMAIL);
                email = cursor.getString(iemail);
            }
            if(cursor.getColumnIndex(IUserSchema.COLUMN_PASSWORD) !=-1){
                ipassword = cursor.getColumnIndexOrThrow(IUserSchema.COLUMN_PASSWORD);
                password = cursor.getString(ipassword);
            }
            if(cursor.getColumnIndex(IUserSchema.COLUMN_FIRST_NAME) !=-1){
                ifirst_name = cursor.getColumnIndexOrThrow(IUserSchema.COLUMN_FIRST_NAME);
                first_name = cursor.getString(ifirst_name);
            }
            if(cursor.getColumnIndex(IUserSchema.COLUMN_LAST_NAME) !=-1){
                ilast_name = cursor.getColumnIndexOrThrow(IUserSchema.COLUMN_LAST_NAME);
                last_name = cursor.getString(ilast_name);
            }
        }else{
            Log.e(TAG,"cursorTo Entity Error");
        }
        return new Form();
    }


    public Cursor getFormName(){
        Cursor formName = db.rawQuery("SELECT * FROM "+ IFormSchema.TABLE_FORM, null);
        return formName;
    }

    public Cursor getMaxTimeStamp(){
        Log.i(TAG1, "Going to database");
        Cursor form_max_date = null;
        try {
            String query = "SELECT " + IFormSchema.INSERTED_DATE + " FROM " + IFormSchema.TABLE_FORM+" ;";
            Log.i(TAG1, "Query : "+query);
            form_max_date = db.rawQuery(query, null);
        }catch(Exception e){
            Log.i(TAG1, e.getMessage());
        }
        if(form_max_date==null){
            Log.i(TAG1, "Null cursor database");
        }
        Log.i(TAG1, "GAfter database");
        return form_max_date;
    }

    public Cursor getFormNameByUser(String username){
        String query = "SELECT * FROM "+ IFormSchema.TABLE_FORM
                +" WHERE form.username = '"+username+"'"
                ;
        Cursor formName = db.rawQuery(query , null);
        Log.i(TAG,"Table query : "+query);
        return formName;


    }

    public Form getFormByFormName(String form_name){
        Form form = new Form();


        return form;
    }

    public void updateSyncStatus(Form form, int sync_status){
        ContentValues contentValues = new ContentValues();

        contentValues.put("sync_status",sync_status);

        String selection = "form_name LIKE ?";

        String[] selection_args = {
                form.getForm_name()
        };

        db.update(
                "form",
                contentValues,
                selection ,
                selection_args
        );

    }

}
