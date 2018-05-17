package com.ecrowd.ecrowd.data.dbserver;

import com.ecrowd.ecrowd.data.model.Form;
import com.ecrowd.ecrowd.data.model.FormPartial;

import java.util.ArrayList;

public class FormGeneralServer {

    String formGeneralQuery;
    String formPartialQuery;
    String formTableCreateQuery;

    public FormGeneralServer() {



    }

    public String getFormGeneralString(Form form){
        formGeneralQuery = "INSERT INTO form (form_name, table_name, username) VALUES ('"
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

    public String getFormPartialStringByFormPartialArray(ArrayList<FormPartial> form_partials){


        formPartialQuery = "INSERT INTO form_partial (form_name, attribute_title, attribute_type) VALUES ";

        for(int i=0; i< form_partials.size(); i++){
            formPartialQuery+="('";
            formPartialQuery +=form_partials.get(i).getForm_name()+"','"+
                    form_partials.get(i).getAttribute_title()+"','"+
                    form_partials.get(i).getAttribute_type()+"'),";
        }

        formPartialQuery = removeLastComma(formPartialQuery);
        formPartialQuery+=");";
        return formPartialQuery;
    }

    public String getFormTablecreateStringByFormPartialArray(Form form_general, ArrayList<FormPartial> form_partials){


        formTableCreateQuery = "CREATE TABLE IF NOT EXISTS "+
                form_general.getTable_name()+" (username VARCHAR(50) PRIMARY KEY,";

        for(int i=0; i< form_partials.size(); i++){
            formTableCreateQuery +=form_partials.get(i).getAttribute_title()+" VARCHAR(50),";
        }
        String query_no_last_comma  = formTableCreateQuery.substring(0, formTableCreateQuery.length()-2);


//        = removeLastComma(query);
        query_no_last_comma+="));";
        return query_no_last_comma;
    }

}
