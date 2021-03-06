package com.kerry.gogobasketball;

import com.kerry.gogobasketball.util.Constants;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.*;

public class MainPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    MainContract.View mView;
    @Mock
    MainPresenter mMainPresenter;

    @Before
    public void setup() {
        mMainPresenter = new MainPresenter(mView);
    }

    @Test
    public void testCoordinateScope() {
        String location = Constants.SONG_SAN_HIGH_SCHOOL;
        double latitude = 25.042300;
        double longitude = 121.566867;
        System.out.print("checkCoordinateScope = " + mMainPresenter.checkCoordinateScope(latitude, longitude));
        assertEquals(mMainPresenter.checkCoordinateScope(latitude, longitude), location);

    }

}
