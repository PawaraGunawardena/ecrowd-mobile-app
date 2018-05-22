package com.ecrowd.ecrowd;

public interface SurveyCreateActivityView {

    boolean checkNetworkConnectionTriggered();

    void submitDynamicFormTriggered(Class activity);

    void submitWidgetItem();

}
