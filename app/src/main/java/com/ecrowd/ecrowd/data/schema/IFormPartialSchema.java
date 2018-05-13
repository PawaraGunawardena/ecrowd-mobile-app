package com.ecrowd.ecrowd.data.schema;

/**
 * Created by User on 2/10/2018.
 */

public interface IFormPartialSchema {
    String TABLE_FORM = "form_partial";

    String PARTIAL_ID = "partial_id";
    String FORM_NAME = "form_name";
    String ATTRIBUTE_TITLE = "attribute_title";
    String ATTRIBUTE_TYPE = "attribute_type";
    String MOBILITY = "mobility";
    String SYNC_STATUS = "sync_status";

    String TABLE_FORM_CREATE = "CREATE TABLE IF NOT EXISTS "+
            TABLE_FORM +" ("+
            PARTIAL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
            FORM_NAME+ " VARCHAR(50) ," +
            ATTRIBUTE_TITLE+" VARCHAR(50) ,"+
            ATTRIBUTE_TYPE+" VARCHAR(50) ,"+
            MOBILITY+" VARCHAR(50) ,"+
            SYNC_STATUS+" INTEGER "+
            ");";

    public String[] FORM_COLUMNS = new String[] {

            PARTIAL_ID,
            FORM_NAME,
            ATTRIBUTE_TITLE,
            ATTRIBUTE_TYPE,
            MOBILITY,
            SYNC_STATUS
    };

    public String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS "+TABLE_FORM;


}
