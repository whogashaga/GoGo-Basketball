package com.kerry.gogobasketball;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MyIntegrationTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void setup() throws Exception {
        //do something…
    }

    @Test
    public void allTestProcess() throws InterruptedException {
        // click create room
        onView(withId(R.id.fragment_home_rooms));
        Thread.sleep(5000);
        onView(withId(R.id.btn_home_rooms_build)).perform(click());

        // type room name
        onView(withId(R.id.fragment_want2_create_room));
        Thread.sleep(5000);
        onView(withId(R.id.edit_want2create_room_name_content)).
                perform(typeText("TestRoom;"),closeSoftKeyboard());

        Thread.sleep(5000);
        onView(withId(R.id.btn_want2create_build_confirm)).perform(click());

        // waiting room
        onView(withId(R.id.fragment_waiting4join_master));

        // change to referee seat
        Thread.sleep(5000);
        onView(withId(R.id.btn_waiting_referee_change_seat)).perform(click());

        // give mock data
        

        // start game
        Thread.sleep(10000);
        onView(withId(R.id.btn_waiting4join_start)).perform(click());

    }

    @After
    public void custom() throws Exception {
        // do something
        Thread.sleep(8000);
    }
}
