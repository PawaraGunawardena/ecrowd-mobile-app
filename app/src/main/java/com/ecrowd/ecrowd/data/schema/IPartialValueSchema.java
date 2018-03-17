package com.ecrowd.ecrowd.data.schema;

/**
 * Created by User on 2/22/2018.
 */

public interface IPartialValueSchema {

    String TABLE_FORM = "form";
    String VALUE_ID = "value_id";
    String PARTIAL_ID = "partial_id";
    String DEFAULT_VALUE = "default_value";

    String TABLE_FORM_CREATE = "CREATE TABLE IF NOT EXISTS "+
            TABLE_FORM +" ("+
            VALUE_ID +" VARCHAR(50) PRIMARY KEY ,"+
            PARTIAL_ID+ " VARCHAR(50) ," +
            DEFAULT_VALUE+" VARCHAR(12) "+
            ");";

    public String[] FORM_COLUMNS = new String[] {

            VALUE_ID,
            PARTIAL_ID,
            DEFAULT_VALUE
    };

    public String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS "+TABLE_FORM;
}
