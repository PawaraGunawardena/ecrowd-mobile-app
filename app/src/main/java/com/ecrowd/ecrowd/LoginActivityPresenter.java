package com.ecrowd.ecrowd;

public class LoginActivityPresenter {

    LoginActivityView view;

    public LoginActivityPresenter(LoginActivityView view) {
        this.view = view;
    }

    public void launchSignupBtnClicked(Class activity){
        view.launchSignInPage(activity);
    }

    public void launchHomePageBtnClicked(Class activity){
        view.launchHomePage(activity);
    }

}
