package com.kerry.gogobasketball;

import com.kerry.gogobasketball.playing.referee.RefereeGoingContract;
import com.kerry.gogobasketball.playing.referee.RefereeGoingPresenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.*;

public class RefereeGoingPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    RefereeGoingContract.View mView;
    @Mock
    RefereeGoingPresenter mPresenter;



    @Before
    public void setup() {
        mPresenter = new RefereeGoingPresenter(mView);
    }

    @Test
    public void testScoreSumTeamA(){

    }

}
