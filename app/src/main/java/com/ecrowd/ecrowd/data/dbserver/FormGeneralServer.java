package com.ecrowd.ecrowd.data.dbserver;

import com.ecrowd.ecrowd.data.model.Form;

public class FormGeneralServer {

    String formGeneralQuery;
    String formPartialQuery;
    String formTableCreateQuery;

    public FormGeneralServer() {



    }

    public String getFormGeneralString(Form form){
        formGeneralQuery = "INSERT INTO FORM (form_name, table_name, username) VALUES ('"
            +form.getForm_name()+"', '" +form.getTable_name()+"', '"+form.getUsername()+"');";
        return formGeneralQuery;
    }

    public String getFormPartialString(Form form){


        formPartialQuery = "INSERT INTO form_partial (form_name, attribute_title, attribute_type) VALUES ";

        for(int i=0; i< form.getAttribute_titles().size(); i++){
            formPartialQuery+="('";
            formPartialQuery +=form.getForm_name()+"','"+
                    form.getAttribute_titles().get(i)+"','"+
                    form.getAttribute_type().get(i)+"'),";
        }

        formPartialQuery = removeLastComma(formPartialQuery);
        formPartialQuery+=");";
        return formPartialQuery;
    }

    public String getFormTablecreateString(Form form){
        formTableCreateQuery = "CREATE TABLE IF NOT EXISTS "+
                form.getTable_name()+" (username VARCHAR(50) PRIMARY KEY,";

        for(int i=0; i< form.getAttribute_titles().size(); i++){
            formTableCreateQuery +=form.getAttribute_titles().get(i)+" VARCHAR(50),";
        }
        String query_no_last_comma  = formTableCreateQuery.substring(0, formTableCreateQuery.length()-2);


//        = removeLastComma(query);
        query_no_last_comma+="));";
        return query_no_last_comma;

    }

    private String removeLastComma(String query){
        int key = query.lastIndexOf(",");
        String query1 = query.substring(0 , (key-1));
        String query2 = query.substring(key+1);

        String newQuery = query1.concat(query2)+"";
        return newQuery;
    }

}
