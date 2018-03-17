package com.ecrowd.ecrowd.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ecrowd.ecrowd.data.model.User;
import com.ecrowd.ecrowd.data.schema.IUserSchema;

/**
 * Created by User on 1/24/2018.
 */

public class UserData {

    private DBHelper crowdDatabaseHelper;
    private SQLiteDatabase db;
    private String TAG= "user";
    public UserData(Context context) {
        crowdDatabaseHelper = new DBHelper(context);
        db = crowdDatabaseHelper.getWritableDatabase();
    }

    public void addUser(User user) {
        ContentValues values = new ContentValues();

        values.put(IUserSchema.COLUMN_USER_NAME, user.getUsername());
        values.put(IUserSchema.COLUMN_EMAIL, user.getEmail());
        values.put(IUserSchema.COLUMN_PASSWORD, user.getPassword());
        values.put(IUserSchema.COLUMN_FIRST_NAME, user.getFirstname());
        values.put(IUserSchema.COLUMN_LAST_NAME, user.getLastname());

        db.insert(IUserSchema.TABLE_USER, null, values);
    }



    public User getUser(String user_name) {
        final String selectionArgs[] = {String.valueOf(user_name)};
        final String selection = IUserSchema.COLUMN_USER_NAME + " = ? ";
        User user = null;
        Cursor cursor = db.query(
                IUserSchema.TABLE_USER,
                IUserSchema.USER_COLUMNS,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                user = cursorToEntity(cursor);
                cursor.moveToNext();
            }
            cursor.close();

        }
        return user;
    }


    private User cursorToEntity(Cursor cursor) {
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
        return new User(user_name, email,first_name,last_name,password);
    }


    public String getPassword(String user_name){
        String dbString ="";
        SQLiteDatabase sqLiteDatabase = crowdDatabaseHelper.getWritableDatabase();
//        String query = "SELECT name FROM "+TABLE_PRODUCTS+" ;";
//        Cursor c = sqLiteDatabase.rawQuery(query, null);
//        c.moveToFirst();
        final String selection = IUserSchema.COLUMN_USER_NAME + " = ? ";
        final String selectionArgs[] = {String.valueOf(user_name)};

        //Cursor cl = sqLiteDatabase.query(IUserSchema.TABLE_USER,);
        Cursor c = sqLiteDatabase.query(
                IUserSchema.TABLE_USER,
                new String[] {IUserSchema.COLUMN_PASSWORD},
                selection,
                selectionArgs,
                null,
                null,
                IUserSchema.COLUMN_ID+ " ASC"
        );

        if(c!=null) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                if (c.getColumnIndex(IUserSchema.COLUMN_PASSWORD) != -1) {
                    String myName = c.getString(c.getColumnIndex(IUserSchema.COLUMN_PASSWORD));
                    dbString= myName;

                }
                c.moveToNext();
            }
            c.close();

        }
        return dbString;
    }

}