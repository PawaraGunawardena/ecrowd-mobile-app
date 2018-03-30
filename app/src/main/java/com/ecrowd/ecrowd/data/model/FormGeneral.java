package com.ecrowd.ecrowd.data.model;

import java.io.Serializable;

/**
 * Created by User on 3/30/2018.
 */

public class FormGeneral implements Serializable {
    private String form_name;
    private String table_name;
    private String starting_date;
    private String closing_date;
    private String username;

    public FormGeneral() {
    }

    public FormGeneral(String form_name, String table_name, String starting_date, String closing_date, String username) {
        this.form_name = form_name;
        this.table_name = table_name;
        this.starting_date = starting_date;
        this.closing_date = closing_date;
        this.username = username;
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
}
