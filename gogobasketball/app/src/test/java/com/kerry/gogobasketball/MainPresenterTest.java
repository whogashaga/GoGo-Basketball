package com.kerry.gogobasketball;

import com.kerry.gogobasketball.util.Constants;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.*;

public class MainPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    MainActivity mMainActivity;
//    MainActivity mMainActivity = new MainActivity();

    @Mock
    MainContract.View mView;

    @Spy
    MainPresenter mMainPresenter;

    @Before
    public void setup() {
//        MockitoAnnotations.initMocks(this);
        mMainPresenter = new MainPresenter();
    }

    @Test
    public void testCoordinateScope() {
        String location = Constants.SONG_SAN_HIGH_SCHOOL;
        double latitude = 25.042300;
        double longitude = 121.566869;
        System.out.print("checkCoordinateScope = " + mMainPresenter.checkCoordinateScope(latitude, longitude));
        assertEquals(mMainPresenter.checkCoordinateScope(latitude, longitude), location);

    }

}
