package com.example.bioscoopapplicatie.presentation;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.bioscoopapplicatie.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListElementsTest {

    @Rule
    public ActivityScenarioRule<Login> mActivityScenarioRule =
            new ActivityScenarioRule<>(Login.class);

    @Test
    public void listElementsTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.login_btn), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.listAddButton),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        0),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.create_media_list_name_fld), withText("Awesome Media"),
                        withParent(allOf(withId(R.id.linearMid),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed()));
        editText.check(matches(isDisplayed()));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.create_media_list_description_txt), withText("Description"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class))),
                        isDisplayed()));
        editText2.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.create_media_list_create_list_btn), withText("CREATE LIST"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.search_fld), withText("Search"),
                        withParent(allOf(withId(R.id.linearTop),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        editText3.check(matches(isDisplayed()));

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.search_fld), withText("Search"),
                        withParent(allOf(withId(R.id.linearTop),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        editText4.check(matches(isDisplayed()));

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.listViewButton),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.search_fld), withText("Search"),
                        withParent(allOf(withId(R.id.linearTop),
                                withParent(withId(R.id.linearLayout)))),
                        isDisplayed()));
        editText5.check(matches(isDisplayed()));

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.show_media_list_recycler_vertical),
                        withParent(allOf(withId(R.id.linearLayout),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.show_media_list_number_item_txt), withText("List: 0"),
                        withParent(withParent(withId(R.id.show_media_list_recycler_vertical))),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.show_media_list_number_item_name_txt), withText("Created By: travisbell"),
                        withParent(withParent(withId(R.id.show_media_list_recycler_vertical))),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.show_media_list_number_item_favorite_count_txt), withText("Favorite Count: 0"),
                        withParent(withParent(withId(R.id.show_media_list_recycler_vertical))),
                        isDisplayed()));
        textView3.check(matches(isDisplayed()));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.show_media_list_number_item_description_txt), withText("The idea behind this list is to collect the live action comic book movies from within the Marvel franchise."),
                        withParent(withParent(withId(R.id.show_media_list_recycler_vertical))),
                        isDisplayed()));
        textView4.check(matches(isDisplayed()));

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.floatingActionButton),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction imageButton2 = onView(
                allOf(withId(R.id.floatingActionButton),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        imageButton2.check(matches(isDisplayed()));

        ViewInteraction imageButton3 = onView(
                allOf(withId(R.id.floatingActionButton),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        imageButton3.check(matches(isDisplayed()));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.show_media_list_recycler_vertical),
                        childAtPosition(
                                withId(R.id.linearLayout),
                                1)));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.show_media_list_details_position_and_name_txt), withText("List: 0 - Created by travisbell"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView5.check(matches(isDisplayed()));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.show_media_list_item_img),
                        withParent(withParent(withId(R.id.show_media_list_details_recycler_view))),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.homescreen_item_txt), withText("Avengers: Infinity War"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView6.check(matches(withText("Avengers: Infinity War")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.show_media_list_item_vote_average_txt), withText("Rating: 8.3"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView7.check(doesNotExist());

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.show_media_list_item_vote_average_txt), withText("Rating: 8.3"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView8.check(matches(isDisplayed()));

        ViewInteraction linearLayout = onView(
                allOf(withParent(allOf(withId(R.id.show_media_list_details_recycler_view),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class)))),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.show_media_list_details_recycler_view),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        recyclerView3.check(matches(isDisplayed()));
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
