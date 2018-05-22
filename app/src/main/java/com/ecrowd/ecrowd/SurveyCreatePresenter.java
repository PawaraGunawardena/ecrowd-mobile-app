package com.ecrowd.ecrowd;

public class SurveyCreatePresenter {

    SurveyCreateActivityView view;

    public SurveyCreatePresenter(SurveyCreateActivityView view) {

        this.view = view;
    }

    public boolean lanchCheckNetworkConnection(){
        return view.checkNetworkConnectionTriggered();
    }

    public void launchWidgetREtun( ){
        view.submitWidgetItem();
    }

    public void launchSubmitDynamicForm(Class activity){
        view.submitDynamicFormTriggered(activity);
    }


}
