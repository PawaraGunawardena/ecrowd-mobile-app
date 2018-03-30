package com.ecrowd.ecrowd.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ecrowd.ecrowd.data.schema.IFormPartialSchema;
import com.ecrowd.ecrowd.data.schema.IFormSchema;
import com.ecrowd.ecrowd.data.schema.IPartialValueSchema;
import com.ecrowd.ecrowd.data.schema.IUserSchema;

/**
 * Created by User on 2/9/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "slavery.db";
    private String TAG = "COOOOOOOOOOL";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(IUserSchema.TABLE_USER_CREATE);
        Log.i(TAG,IUserSchema.TABLE_USER_CREATE);
        sqLiteDatabase.execSQL(IFormSchema.TABLE_FORM_CREATE);
        Log.i(TAG,IFormSchema.TABLE_FORM_CREATE);
        sqLiteDatabase.execSQL(IFormPartialSchema.TABLE_FORM_CREATE);
        Log.i(TAG,IFormPartialSchema.TABLE_FORM_CREATE);
        sqLiteDatabase.execSQL(IPartialValueSchema.TABLE_FORM_CREATE);
        Log.i(TAG,IPartialValueSchema.TABLE_FORM_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

//    public boolean addData(){
//
//    }
}
