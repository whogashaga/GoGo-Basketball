package com.kerry.gogobasketball;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;

import com.kerry.gogobasketball.playing.referee.RefereeGoingFragment;
import com.kerry.gogobasketball.profile.ProfileContract;
import com.kerry.gogobasketball.profile.ProfilePresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertThat;


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 26)
public class FragmentTest {

    @Mock
    ProfileContract.View mView;

    @Mock
    ProfilePresenter mPresenter;

    private MainActivity mainActivity;
    private BottomNavigationView bottomNavigationView;

    @Before
    public void setup() {
//        mainActivity = Robolectric.setupActivity(MainActivity.class);
//        bottomNavigationView = mainActivity.findViewById(R.id.bottom_navigation_main);

        mPresenter = new ProfilePresenter(mView);

    }

    @Test
    public void testActivityLifeCycle() throws Exception {
        assertNotNull(mView);
    }

}
