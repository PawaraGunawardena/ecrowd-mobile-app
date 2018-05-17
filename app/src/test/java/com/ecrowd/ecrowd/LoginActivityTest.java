//package com.ecrowd.ecrowd;
//
//import android.content.Intent;
//import android.os.Build;
//
//import com.ecrowd.ecrowd.ui.Login;
//import com.ecrowd.ecrowd.ui.SignUp;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.robolectric.RobolectricGradleTestRunner;
//import org.robolectric.Shadows;
//import org.robolectric.annotation.Config;
//import org.robolectric.shadows.ShadowActivity;
//
//import static org.junit.Assert.*;
//
////repositories{
////        maven {url "http://oss.sonatype.org/content/repositories/snapshots"}
////        }
//@RunWith(RobolectricGradleTestRunner.class)
//@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
//public class LoginActivityTest {
//
//    Login activity;
//
//    @Before
//    public void setUp(){
//
//        activity = new Login();
//    }
//
//    @Test
//    public void clickedLogin() throws  Exception{
//         //Arrange
//        Class clazz = SignUp.class;
//        Intent expectedIntent  = new Intent(activity,clazz);
//
//        //Act
//        activity.launchSignInPage(clazz);
//
//        //Assert
//        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
//        Intent actualIntent = shadowActivity.getNextStartedActivity();
//        assertTrue(expectedIntent.filterEquals(actualIntent));
//    }
//}
