package com.ecrowd.ecrowd.data.model;

import java.io.Serializable;

/**
 * Created by User on 3/30/2018.
 */

public class FormPartial implements Serializable {
    private String attribute_title ;
    private String attribute_type ;
    private String Mobility ;
    private String form_name;

    public FormPartial() {
    }

    public String getAttribute_title() {

        return attribute_title;
    }

    public void setAttribute_title(String attribute_title) {
        this.attribute_title = attribute_title;
    }

    public String getAttribute_type() {
        return attribute_type;
    }

    public void setAttribute_type(String attribute_type) {
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
