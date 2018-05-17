package com.ecrowd.ecrowd;


import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ecrowd.ecrowd.ui.Home;
import com.ecrowd.ecrowd.ui.Login;
import com.ecrowd.ecrowd.ui.SignUp;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(AndroidJUnit4.class)
public class LogginActivityUITest {


    @Rule
    public ActivityTestRule<Login> activityTestRule= new ActivityTestRule<>(Login.class);

    @Before


    @Test
    public void launchSignupBtnClicked() {
        //Arrange
        String givenString_username = "Power";
        String givenString_password = "power";
        onView(withId(R.id.editText_username_login)).perform(typeText(givenString_username));
        onView(withId(R.id.editText_password_login)).perform(typeText(givenString_password));

        String id_activity = "main_content";
        //Act
        onView(withId(R.id.button_login_login)).perform(click());

        //Assert
        onView(withId(R.id.id_listView_tab1)).check(matches(notNullValue()));
    }

    @Test
    public void launchHomePage() {
        //Arrange

        //Act


        //Assert

    }

}
