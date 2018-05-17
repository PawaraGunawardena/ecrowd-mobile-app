package com.ecrowd.ecrowd;

public class SignUpActivityPresenter {
    SignUpActivityView view;

    public SignUpActivityPresenter(SignUpActivityView view) {
        this.view = view;
    }

    public void launchLoginPageBtnClicked(Class activity){
        view.launchLoginPage(activity);
    }

    public void launchSignInRegisterBtnClicked(Class activity){
        view.registerBySignUp(activity);
    }
}





