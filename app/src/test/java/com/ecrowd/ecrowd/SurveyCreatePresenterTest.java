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
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SurveyCreatePresenterTest {


    SurveyCreatePresenter presenter;

    @Mock
    SurveyCreateActivityView  view;

    @Before
    public void setUp() throws Exception {
        presenter = new SurveyCreatePresenter(view);
    }

    @Test
    public void lanchCheckNetworkConnection() {
        //Act
        presenter.lanchCheckNetworkConnection();

        //Assert
        Mockito.verify(view).checkNetworkConnectionTriggered();
    }

    @Test
    public void launchWidgetREtun() {
        presenter.launchWidgetREtun();
        Mockito.verify(view).submitWidgetItem();
    }

    @Test
    public void launchSubmitDynamicForm() {
//Arrange
        Class clazz = Home.class;
        //Act
        presenter.launchSubmitDynamicForm(clazz);

        //Assert
        Mockito.verify(view).submitDynamicFormTriggered(clazz);
    }
}