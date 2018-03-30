package com.ecrowd.ecrowd.data.model;

import java.util.ArrayList;

/**
 * Created by User on 3/30/2018.
 */

public class FormPartials {
    private ArrayList<String> attribute_titles = new ArrayList<>();
    private ArrayList<String> attribute_type = new ArrayList<>();
    private String Mobility ;
    private String form_name;

    public FormPartials() {

    }

    public FormPartials(ArrayList<String> attribute_titles, ArrayList<String> attribute_type, String mobility, String form_name) {
        this.attribute_titles = attribute_titles;
        this.attribute_type = attribute_type;
        Mobility = mobility;
        this.form_name = form_name;
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

    public String getMobility() {
        return Mobility;
    }

    public void setMobility(String mobility) {
        Mobility = mobility;
    }

    public String getForm_name() {
        return form_name;
    }

    public void setForm_name(String form_name) {
        this.form_name = form_name;
    }
}
