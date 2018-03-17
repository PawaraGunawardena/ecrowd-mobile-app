package com.ecrowd.ecrowd.data.schema;

/**
 * Created by User on 1/24/2018.
 */

public interface IFormSchema {
    String TABLE_FORM = "form";
    String FORM_NAME = "form_name";
    String TABLE_NAME = "table_name";
    String STARTING_DATE = "starting_date";
    String CLOSING_DATE = "closing_date";
    String USER_NAME = "username";

    String TABLE_FORM_CREATE = "CREATE TABLE IF NOT EXISTS "+
            TABLE_FORM +" ("+
            FORM_NAME +" VARCHAR(50) PRIMARY KEY ,"+
            TABLE_NAME+ " VARCHAR(50) ," +
            STARTING_DATE+" VARCHAR(12) ,"+
            CLOSING_DATE+" VARCHAR(12) ,"+
            USER_NAME+" VARCHAR(50) "+
            ");";

    public String[] FORM_COLUMNS = new String[] {

            FORM_NAME,
            TABLE_NAME,
            STARTING_DATE,
            CLOSING_DATE,
            USER_NAME
    };

    public String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS "+TABLE_FORM;
}
