package com.kerry.gogobasketball;

import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.StringContains.containsString;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
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

        /* -------------------------------------------------------------------------------------- */
        /* Want2CreateRoom */
        onView(withId(R.id.fragment_want2_create_room));

        // type room name
        Thread.sleep(2000);
        onView(withId(R.id.edit_want2create_room_name_content)).
                perform(replaceText("TestRoom"), closeSoftKeyboard());

        Thread.sleep(6000);
        onView(withId(R.id.btn_want2create_build_confirm)).perform(click());

        /* -------------------------------------------------------------------------------------- */
        /* Waiting4Join */
        onView(withId(R.id.fragment_waiting4join_master));

        // change to referee seat
        Thread.sleep(5000);
        onView(withId(R.id.btn_waiting_referee_change_seat)).perform(click());

        // give mock data

        // start game
        Thread.sleep(10000);
        onView(withId(R.id.btn_waiting4join_start)).perform(click());

        /* -------------------------------------------------------------------------------------- */
        /* RefereeGoing */

        onView(withId(R.id.layout_playing_referee));
        Thread.sleep(3000);
        onView(withId(R.id.btn_playing_team_a_player1_point_plus)).perform(click());
        onView(withId(R.id.btn_playing_team_a_player2_point_plus)).perform(click());
        onView(withId(R.id.btn_playing_team_a_player3_point_plus)).perform(click());
        onView(withId(R.id.btn_playing_team_b_player1_point_plus)).perform(click());
        onView(withId(R.id.btn_playing_team_b_player2_point_plus)).perform(click());
        onView(withId(R.id.btn_playing_team_b_player3_point_plus)).perform(click());

        Thread.sleep(1000);
        onView(withId(R.id.btn_playing_team_a_player1_rebound_plus)).perform(click());
        onView(withId(R.id.btn_playing_team_a_player2_rebound_plus)).perform(click());
        onView(withId(R.id.btn_playing_team_a_player3_rebound_plus)).perform(click());
        onView(withId(R.id.btn_playing_team_b_player1_rebound_plus)).perform(click());
        onView(withId(R.id.btn_playing_team_b_player2_rebound_plus)).perform(click());
        onView(withId(R.id.btn_playing_team_b_player3_rebound_plus)).perform(click());

        Thread.sleep(1000);
        onView(withId(R.id.btn_playing_team_a_player1_foul_plus)).perform(click());
        onView(withId(R.id.btn_playing_team_a_player2_foul_plus)).perform(click());
        onView(withId(R.id.btn_playing_team_a_player3_foul_plus)).perform(click());
        onView(withId(R.id.btn_playing_team_b_player1_foul_plus)).perform(click());
        onView(withId(R.id.btn_playing_team_b_player2_foul_plus)).perform(click());
        onView(withId(R.id.btn_playing_team_b_player3_foul_plus)).perform(click());

        Thread.sleep(1000);
        onView(withId(R.id.btn_playing_team_a_player1_point_plus)).perform(click());
        onView(withId(R.id.btn_playing_team_b_player1_point_plus)).perform(click());
        onView(withId(R.id.btn_playing_team_a_player2_rebound_plus)).perform(click());
        onView(withId(R.id.btn_playing_team_b_player2_rebound_plus)).perform(click());
        onView(withId(R.id.btn_playing_team_a_player3_foul_plus)).perform(click());
        onView(withId(R.id.btn_playing_team_b_player3_foul_plus)).perform(click());

        Thread.sleep(1000);
        onView(withId(R.id.btn_playing_team_b_player3_point_plus)).perform(click());
        onView(withId(R.id.btn_playing_team_b_player3_point_plus)).perform(click());

        Thread.sleep(3000);
        onView(withId(R.id.btn_gaming_game_over)).perform(click());

        /* -------------------------------------------------------------------------------------- */
        /* RefereeResult */
        onView(withId(R.id.fragment_result_referee));

        Thread.sleep(3000);
        onView(withId(R.id.text_referee_going_title_start)).check(matches(withText("Blue")));
        onView(withId(R.id.text_result_referee_score_team_a)).check(matches(withText(containsString("4"))));
        onView(withId(R.id.text_result_referee_score_team_b)).check(matches(withText(containsString("6"))));


        Thread.sleep(6000);
        onView(withId(R.id.btn_result_referee_back_lobby)).perform(click());
    }

    @After
    public void custom() throws Exception {
        // do something
        Thread.sleep(6000);
    }
}
