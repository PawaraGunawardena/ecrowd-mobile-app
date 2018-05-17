package com.ecrowd.ecrowd;

import android.support.v4.app.Fragment;

public class HomeActivityPresenter {

    HomeActivityView view;

    public HomeActivityPresenter(HomeActivityView view) {
        this.view = view;
    }

    public Fragment launchTagItemChange(int position){
        return view.checkClickedTagItem(position);
    }
    public CharSequence launchGetSelectedItemSeqeunceName(int position){
        return view.checkPageTitle(position);
    }

}
