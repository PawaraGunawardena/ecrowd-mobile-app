package com.ecrowd.ecrowd.data.model;

import java.util.ArrayList;

/**
 * Created by User on 2/9/2018.
 */

public class Form {
    private String form_name;
    private String table_name;
    private String starting_date;
    private String closing_date;
    private String username;

    ArrayList<String> attribute_titles = new ArrayList<>();
    ArrayList<String> attribute_type = new ArrayList<>();
    ArrayList<String> default_value = new ArrayList<>();
    String Mobility ;

    public String getMobility() {
        return Mobility;
    }

    public void setMobility(String mobility) {
        Mobility = mobility;
    }

    public Form() {

    }

    public Form(String form_name, String table_name, String username) {
        this.form_name = form_name;
        this.table_name = table_name;
        this.username = username;
    }

    public Form(String form_name, String table_name, String starting_date, String closing_date, String username,
                ArrayList<String> attribute_titles, ArrayList<String> attribute_type, ArrayList<String> default_value) {
        this.form_name = form_name;
        this.table_name = table_name;
        this.starting_date = starting_date;
        this.closing_date = closing_date;
        this.username = username;
        this.attribute_titles = attribute_titles;
        this.attribute_type = attribute_type;
        this.default_value = default_value;
    }

    public String getForm_name() {
        return form_name;
    }

    public void setForm_name(String form_name) {
        this.form_name = form_name;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getStarting_date() {
        return starting_date;
    }

    public void setStarting_date(String starting_date) {
        this.starting_date = starting_date;
    }

    public String getClosing_date() {
        return closing_date;
    }

    public void setClosing_date(String closing_date) {
        this.closing_date = closing_date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<String> getAttribute_titles() {
        return attribute_titles;
    }

    public void setAttribute_titles(ArrayList<String> attribute_titles) {
        this.attribute_titles = attribute_titles;
    }

    public ArrayList<String> getAttribute_type() {
        return attribute_type;
    }

    public void setAttribute_type(ArrayList<String> attribute_type) {
        this.attribute_type = attribute_type;
    }

    public ArrayList<String> getDefault_value() {
        return default_value;
    }

    public void setDefault_value(ArrayList<String> default_value) {
        this.default_value = default_value;
    }
}
