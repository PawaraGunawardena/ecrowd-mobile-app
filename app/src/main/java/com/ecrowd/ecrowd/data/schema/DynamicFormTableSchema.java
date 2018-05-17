package com.ecrowd.ecrowd.data.schema;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by User on 2/10/2018.
 */

public class DynamicFormTableSchema {

    ArrayList<String> attributes ;
    String formTableName;
    String TAG="TAAAAAAAAAAAAABLE";
    String query ="" ;

    public DynamicFormTableSchema(ArrayList<String> attributes, String formName) {
        this.attributes = attributes;
        this.formTableName = formName;

    }

    public DynamicFormTableSchema() {

    }

    public ArrayList<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<String> attributes) {
        this.attributes = attributes;
    }

    public String getFormTableName() {
        return formTableName;
    }

    public void setFormTableName(String formTableName) {
        this.formTableName = formTableName;
    }



    public String getQueryCreateDynamicFormTable(){
        query += "CREATE TABLE IF NOT EXISTS "+
                formTableName+" (username VARCHAR(50) PRIMARY KEY, sync_status INTEGER, ";

        for(int i=0; i< attributes.size(); i++){
            query +=attributes.get(i)+" VARCHAR(50),";
        }
        String query_no_last_comma  = query.substring(0, query.length()-2);


//        = removeLastComma(query);
        query_no_last_comma+="));";
        return query_no_last_comma;
    }
    public void createDynamicFormTable(){
        getQueryCreateDynamicFormTable();
        Log.i(TAG,query);
    }
    public String[] USER_COLUMNS = new String[] {

    };

    public String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS "+formTableName;

    private String removeLastComma(String query){
        int key = query.lastIndexOf(",");
        String query1 = query.substring(0 , (key-1));
        String query2 = query.substring(key+1);

        String newQuery = query1.concat(query2)+"";
        return newQuery;
    }
}
