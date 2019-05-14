package com.kerry.gogobasketball;

import com.kerry.gogobasketball.home.map.CourtsMapPresenter;

import org.junit.Test;
import org.mockito.Mock;

public class MainPresenterTest {

    @Mock
    MainActivity mMainActivity;

    @Mock
    CourtsMapPresenter mMapPresenter;

    MainPresenter mMainPresenter = new MainPresenter(mMainActivity);

    @Test
    public void testIfCreateUser(){

    }

    @Test
    public void testCoodinate(){
        
    }

}
