package com.kerry.gogobasketball;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class IntegrationTest {

    @Rule
    public ActivityTestRule<LogoActivity> mActivityTestRule = new ActivityTestRule<>(LogoActivity.class);

    @Test
    public void integrationTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btn_home_rooms_build), withText("創建房間"),
                        childAtPosition(
                                allOf(withId(R.id.fragment_home_rooms),
                                        withParent(withId(R.id.viewpager_home))),
                                3),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.edit_want2create_room_name_content),
                        childAtPosition(
                                allOf(withId(R.id.fragment_want2_create_room),
                                        childAtPosition(
                                                withId(R.id.layout_main_container),
                                                1)),
                                4),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("test"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btn_want2create_build_confirm), withText("創建"),
                        childAtPosition(
                                allOf(withId(R.id.fragment_want2_create_room),
                                        childAtPosition(
                                                withId(R.id.layout_main_container),
                                                1)),
                                9),
                        isDisplayed()));
        appCompatButton2.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.btn_waiting_referee_change_seat), withText("更換"),
                        childAtPosition(
                                allOf(withId(R.id.waiting_referee_seat),
                                        childAtPosition(
                                                withId(R.id.fragment_waiting4join_master),
                                                6)),
                                4),
                        isDisplayed()));
        appCompatButton3.perform(click());


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.btn_waiting4join_start),
                        childAtPosition(
                                allOf(withId(R.id.fragment_waiting4join_master),
                                        childAtPosition(
                                                withId(R.id.layout_main_container),
                                                2)),
                                13),
                        isDisplayed()));
        appCompatButton4.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.btn_playing_team_a_player1_point_plus),
                        childAtPosition(
                                allOf(withId(R.id.playing_team_a_player1_seat),
                                        childAtPosition(
                                                withId(R.id.layout_playing_referee),
                                                14)),
                                8),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.btn_playing_team_a_player1_point_plus),
                        childAtPosition(
                                allOf(withId(R.id.playing_team_a_player1_seat),
                                        childAtPosition(
                                                withId(R.id.layout_playing_referee),
                                                14)),
                                8),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.btn_playing_team_b_player1_point_plus),
                        childAtPosition(
                                allOf(withId(R.id.team_b_player1_seat),
                                        childAtPosition(
                                                withId(R.id.layout_playing_referee),
                                                17)),
                                8),
                        isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.btn_playing_team_b_player3_point_plus),
                        childAtPosition(
                                allOf(withId(R.id.team_b_player3_seat),
                                        childAtPosition(
                                                withId(R.id.layout_playing_referee),
                                                19)),
                                8),
                        isDisplayed()));
        appCompatButton8.perform(click());

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.btn_playing_team_b_player2_rebound_plus),
                        childAtPosition(
                                allOf(withId(R.id.team_b_player2_seat),
                                        childAtPosition(
                                                withId(R.id.layout_playing_referee),
                                                18)),
                                11),
                        isDisplayed()));
        appCompatButton9.perform(click());

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.btn_playing_team_a_player3_point_plus),
                        childAtPosition(
                                allOf(withId(R.id.playing_team_a_player3_seat),
                                        childAtPosition(
                                                withId(R.id.layout_playing_referee),
                                                16)),
                                8),
                        isDisplayed()));
        appCompatButton10.perform(click());

        ViewInteraction appCompatButton11 = onView(
                allOf(withId(R.id.btn_playing_team_b_player2_point_plus),
                        childAtPosition(
                                allOf(withId(R.id.team_b_player2_seat),
                                        childAtPosition(
                                                withId(R.id.layout_playing_referee),
                                                18)),
                                8),
                        isDisplayed()));
        appCompatButton11.perform(click());

        ViewInteraction appCompatButton12 = onView(
                allOf(withId(R.id.btn_playing_team_a_player2_point_plus),
                        childAtPosition(
                                allOf(withId(R.id.playing_team_a_player2_seat),
                                        childAtPosition(
                                                withId(R.id.layout_playing_referee),
                                                15)),
                                8),
                        isDisplayed()));
        appCompatButton12.perform(click());

        ViewInteraction appCompatButton13 = onView(
                allOf(withId(R.id.btn_playing_team_b_player2_foul_plus),
                        childAtPosition(
                                allOf(withId(R.id.team_b_player2_seat),
                                        childAtPosition(
                                                withId(R.id.layout_playing_referee),
                                                18)),
                                14),
                        isDisplayed()));
        appCompatButton13.perform(click());

        ViewInteraction appCompatButton14 = onView(
                allOf(withId(R.id.btn_playing_team_a_player1_rebound_plus),
                        childAtPosition(
                                allOf(withId(R.id.playing_team_a_player1_seat),
                                        childAtPosition(
                                                withId(R.id.layout_playing_referee),
                                                14)),
                                11),
                        isDisplayed()));
        appCompatButton14.perform(click());

        ViewInteraction appCompatButton15 = onView(
                allOf(withId(R.id.btn_playing_team_a_player1_foul_plus),
                        childAtPosition(
                                allOf(withId(R.id.playing_team_a_player1_seat),
                                        childAtPosition(
                                                withId(R.id.layout_playing_referee),
                                                14)),
                                14),
                        isDisplayed()));
        appCompatButton15.perform(click());

        ViewInteraction appCompatButton16 = onView(
                allOf(withId(R.id.btn_playing_team_a_player1_rebound_plus),
                        childAtPosition(
                                allOf(withId(R.id.playing_team_a_player1_seat),
                                        childAtPosition(
                                                withId(R.id.layout_playing_referee),
                                                14)),
                                11),
                        isDisplayed()));
        appCompatButton16.perform(click());

        ViewInteraction appCompatButton17 = onView(
                allOf(withId(R.id.btn_playing_team_a_player1_rebound_plus),
                        childAtPosition(
                                allOf(withId(R.id.playing_team_a_player1_seat),
                                        childAtPosition(
                                                withId(R.id.layout_playing_referee),
                                                14)),
                                11),
                        isDisplayed()));
        appCompatButton17.perform(click());

        ViewInteraction appCompatButton18 = onView(
                allOf(withId(R.id.btn_playing_team_a_player1_foul_plus),
                        childAtPosition(
                                allOf(withId(R.id.playing_team_a_player1_seat),
                                        childAtPosition(
                                                withId(R.id.layout_playing_referee),
                                                14)),
                                14),
                        isDisplayed()));
        appCompatButton18.perform(click());

        ViewInteraction appCompatButton19 = onView(
                allOf(withId(R.id.btn_playing_team_a_player1_point_plus),
                        childAtPosition(
                                allOf(withId(R.id.playing_team_a_player1_seat),
                                        childAtPosition(
                                                withId(R.id.layout_playing_referee),
                                                14)),
                                8),
                        isDisplayed()));
        appCompatButton19.perform(click());

        ViewInteraction appCompatButton20 = onView(
                allOf(withId(R.id.btn_playing_team_a_player2_rebound_plus),
                        childAtPosition(
                                allOf(withId(R.id.playing_team_a_player2_seat),
                                        childAtPosition(
                                                withId(R.id.layout_playing_referee),
                                                15)),
                                11),
                        isDisplayed()));
        appCompatButton20.perform(click());

        ViewInteraction appCompatButton21 = onView(
                allOf(withId(R.id.btn_playing_team_a_player2_foul_plus),
                        childAtPosition(
                                allOf(withId(R.id.playing_team_a_player2_seat),
                                        childAtPosition(
                                                withId(R.id.layout_playing_referee),
                                                15)),
                                14),
                        isDisplayed()));
        appCompatButton21.perform(click());

        ViewInteraction appCompatButton22 = onView(
                allOf(withId(R.id.btn_playing_team_a_player3_rebound_plus),
                        childAtPosition(
                                allOf(withId(R.id.playing_team_a_player3_seat),
                                        childAtPosition(
                                                withId(R.id.layout_playing_referee),
                                                16)),
                                11),
                        isDisplayed()));
        appCompatButton22.perform(click());

        ViewInteraction appCompatButton23 = onView(
                allOf(withId(R.id.btn_playing_team_a_player3_foul_plus),
                        childAtPosition(
                                allOf(withId(R.id.playing_team_a_player3_seat),
                                        childAtPosition(
                                                withId(R.id.layout_playing_referee),
                                                16)),
                                14),
                        isDisplayed()));
        appCompatButton23.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton24 = onView(
                allOf(withId(R.id.btn_playing_team_a_player1_point_plus),
                        childAtPosition(
                                allOf(withId(R.id.playing_team_a_player1_seat),
                                        childAtPosition(
                                                withId(R.id.layout_playing_referee),
                                                14)),
                                8),
                        isDisplayed()));
        appCompatButton24.perform(click());

        ViewInteraction appCompatButton25 = onView(
                allOf(withId(R.id.btn_playing_team_a_player1_point_plus),
                        childAtPosition(
                                allOf(withId(R.id.playing_team_a_player1_seat),
                                        childAtPosition(
                                                withId(R.id.layout_playing_referee),
                                                14)),
                                8),
                        isDisplayed()));
        appCompatButton25.perform(click());

        ViewInteraction appCompatButton26 = onView(
                allOf(withId(R.id.btn_playing_team_a_player1_point_plus),
                        childAtPosition(
                                allOf(withId(R.id.playing_team_a_player1_seat),
                                        childAtPosition(
                                                withId(R.id.layout_playing_referee),
                                                14)),
                                8),
                        isDisplayed()));
        appCompatButton26.perform(click());

        ViewInteraction appCompatButton27 = onView(
                allOf(withId(R.id.btn_playing_team_a_player1_point_plus),
                        childAtPosition(
                                allOf(withId(R.id.playing_team_a_player1_seat),
                                        childAtPosition(
                                                withId(R.id.layout_playing_referee),
                                                14)),
                                8),
                        isDisplayed()));
        appCompatButton27.perform(click());

        ViewInteraction appCompatButton28 = onView(
                allOf(withId(R.id.btn_gaming_game_over),
                        childAtPosition(
                                allOf(withId(R.id.layout_playing_referee),
                                        childAtPosition(
                                                withId(R.id.layout_main_container),
                                                3)),
                                13),
                        isDisplayed()));
        appCompatButton28.perform(click());

        ViewInteraction appCompatButton29 = onView(
                allOf(withId(R.id.btn_result_referee_back_lobby), withText("返回大廳"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_main_container),
                                        4),
                                2),
                        isDisplayed()));
        appCompatButton29.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
