package com.ecrowd.ecrowd.data.schema;

/**
 * Created by User on 1/24/2018.
 */

public interface IUserSchema {
    String TABLE_USER = "user";

    String COLUMN_ID = "user_id";
    String COLUMN_USER_NAME = "username";
    String COLUMN_EMAIL = "email";
    String COLUMN_FIRST_NAME = "first_name";
    String COLUMN_LAST_NAME = "last_name";
    String COLUMN_PASSWORD = "password";

    String TABLE_USER_CREATE = "CREATE TABLE IF NOT EXISTS "+
            TABLE_USER +" ("+
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"+
            COLUMN_USER_NAME+ " VARCHAR(50) NOT NULL UNIQUE ,"+
            COLUMN_EMAIL+ " VARCHAR(50) NOT NULL UNIQUE ," +
            COLUMN_FIRST_NAME+" VARCHAR(50) NOT NULL ,"+
            COLUMN_LAST_NAME+" VARCHAR(50) NOT NULL ,"+
            COLUMN_PASSWORD+" VARCHAR(50) NOT NULL "+
            ");";

    public String[] USER_COLUMNS = new String[] {
            COLUMN_ID,
            COLUMN_USER_NAME,
            COLUMN_EMAIL,
            COLUMN_PASSWORD,
            COLUMN_FIRST_NAME,
            COLUMN_LAST_NAME
    };

    public String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS "+TABLE_USER;
}
