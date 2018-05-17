package com.ecrowd.ecrowd;

import com.ecrowd.ecrowd.ui.Home;
import com.ecrowd.ecrowd.ui.SignUp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginActivityPresenterTest {

    LoginActivityPresenter presenter;

    @Mock
    LoginActivityView view;

//    class MockedView implements LoginActivityView{
//
//        @Override
//        public void launchSignInPage(Class activity) {
//
//        }
//
//        @Override
//        public void launchHomePage(Class activity) {
//
//        }
//    }

    @Before
    public void setUp() throws Exception {
//        view = new MockedView();
        presenter = new LoginActivityPresenter(view);
    }

    @Test
    public void launchSignupBtnClicked() {
        //Arrange
        Class clazz = SignUp.class;
        //Act
        presenter.launchSignupBtnClicked(clazz);

        //Assert
        Mockito.verify(view).launchSignInPage(clazz);
    }

    @Test
    public void launchHomePage() {
        //Arrange
        Class clazz = Home.class;
        //Act
        presenter.launchHomePageBtnClicked(clazz);

        //Assert
        Mockito.verify(view).launchHomePage(clazz);
    }
}