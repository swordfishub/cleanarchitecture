package com.mralonso.android.presentation.screens.books;

import android.app.ActionBar;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mralonso.android.domain.data.Book;
import com.mralonso.android.presentation.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class BooksActivityTest {

    BooksActivity mBooksActivity;

    @Rule
    public ActivityTestRule<BooksActivity> mActivityRule = new ActivityTestRule<>(BooksActivity.class);

    @Before
    public void init(){
        mBooksActivity = mActivityRule.getActivity();
    }

    @Test
    public void testHasActionBar(){
        assertNotNull(mActivityRule.getActivity().getSupportActionBar());
    }

    @Test
    public void testActionbarShowingTitle(){
        final int displayOptions = mActivityRule.getActivity().getSupportActionBar().getDisplayOptions();
        final boolean isShowTitleEnabled = (displayOptions & ActionBar.DISPLAY_SHOW_TITLE) != 0;
        assertTrue(isShowTitleEnabled);
    }

    @Test
    public void testContainsAppName(){
        String title = (String) mActivityRule.getActivity().getSupportActionBar().getTitle();
        assertThat(title, is("Clean Architecture App"));
    }

    @Test
    public void testContainsToolbar(){
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
    }

    @Test
    public void testShowBooks(){
        mBooksActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBooksActivity.showBooks(new ArrayList<Book>());
            }
        });
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
    }

    @Test
    public void testShowError(){
        mBooksActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBooksActivity.showError(true);
            }
        });
        onView(withId(R.id.error)).check(matches(isDisplayed()));
    }

    @Test
    public void testHideError(){
        mBooksActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBooksActivity.showError(false);
            }
        });
        onView(withId(R.id.error)).check(matches(not(isDisplayed())));
    }

    @Test
    public void testShowLoading(){
        mBooksActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBooksActivity.showLoading(true);
            }
        });
        onView(withId(R.id.loading)).check(matches(isDisplayed()));
    }

    @Test
    public void testHideLoading(){
        mBooksActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBooksActivity.showLoading(false);
            }
        });
        onView(withId(R.id.loading)).check(matches(not(isDisplayed())));
    }
}
