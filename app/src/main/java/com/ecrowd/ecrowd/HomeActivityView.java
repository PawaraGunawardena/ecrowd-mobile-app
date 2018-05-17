package com.ecrowd.ecrowd;

import android.support.v4.app.Fragment;

public interface HomeActivityView {

    Fragment checkClickedTagItem(int position);


    CharSequence checkPageTitle(int position);

}
