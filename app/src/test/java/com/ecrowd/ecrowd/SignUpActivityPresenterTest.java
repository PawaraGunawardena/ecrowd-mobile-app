package com.ecrowd.ecrowd;

import com.ecrowd.ecrowd.ui.SignUp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SignUpActivityPresenterTest {

    SignUpActivityPresenter presenter;

    @Mock
    SignUpActivityView view;

    @Before
    public void setUp() throws Exception {
        presenter = new SignUpActivityPresenter(view);
    }

    @Test
    public void launchLoginPageBtnClicked() {
        //Arrange
        Class clazz = SignUp.class;
        //Act
        presenter.launchLoginPageBtnClicked(clazz);

        //Assert
        Mockito.verify(view).launchLoginPage(clazz);
    }

    @Test
    public void launchSignInRegisterBtnClicked() {
        //Arrange
        Class clazz = SignUp.class;
        //Act
        presenter.launchLoginPageBtnClicked(clazz);

        //Assert
        Mockito.verify(view).launchLoginPage(clazz);
    }
}