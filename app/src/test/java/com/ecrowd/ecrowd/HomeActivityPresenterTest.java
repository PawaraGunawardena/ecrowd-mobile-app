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
public class HomeActivityPresenterTest {

    HomeActivityPresenter presenter;

    @Mock
    HomeActivityView view;

    @Before
    public void setUp() throws Exception {
        presenter = new HomeActivityPresenter(view);
    }

    @Test
    public void launchTagItemChange1() {

        //Arrange
        int position_current = 1;

        //Act
        presenter.launchTagItemChange(position_current);

        //Assert
        Mockito.verify(view).checkClickedTagItem(position_current);
    }
    @Test
    public void launchTagItemChange2() {

        //Arrange
        int position_current = 2;

        //Act
        presenter.launchTagItemChange(position_current);

        //Assert
        Mockito.verify(view).checkClickedTagItem(position_current);
    }
    @Test
    public void launchTagItemChange3() {

        //Arrange
        int position_current = 3;

        //Act
        presenter.launchTagItemChange(position_current);

        //Assert
        Mockito.verify(view).checkClickedTagItem(position_current);
    }

    @Test
    public void launchGetSelectedItemSeqeunceName1() {
//Arrange
        int position_current = 1;

        //Act
        presenter.launchGetSelectedItemSeqeunceName(position_current);

        //Assert
        Mockito.verify(view).checkPageTitle(position_current);

    }
    @Test
    public void launchGetSelectedItemSeqeunceName2() {
//Arrange
        int position_current = 2;

        //Act
        presenter.launchGetSelectedItemSeqeunceName(position_current);

        //Assert
        Mockito.verify(view).checkPageTitle(position_current);

    }

    @Test
    public void launchGetSelectedItemSeqeunceName3() {
//Arrange
        int position_current = 3;

        //Act
        presenter.launchGetSelectedItemSeqeunceName(position_current);

        //Assert
        Mockito.verify(view).checkPageTitle(position_current);

    }
}