package com.mralonso.android.presentation.screens.books;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mralonso.android.domain.data.BookDetails;
import com.mralonso.android.presentation.R;
import com.mralonso.android.presentation.screens.detail.BookDetailsActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class BookDetailsActivityTest {

    BookDetailsActivity mBooksActivity;

    @Rule
    public ActivityTestRule<BookDetailsActivity> mActivityRule =
        new ActivityTestRule<BookDetailsActivity>(BookDetailsActivity.class){
            @Override
            protected Intent getActivityIntent() {
                Context targetContext = InstrumentationRegistry.getInstrumentation()
                        .getTargetContext();
                Intent result = new Intent(targetContext, BookDetailsActivity.class);
                result.putExtra("BOOK_TITLE_EXTRA", "Testing title");
                result.putExtra("BOOK_ID_EXTRA", "1234");
                return result;
            }
        };

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
        assertThat(title, is("Testing title"));
    }

    @Test
    public void testContainsToolbar(){
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
    }

    @Test
    public void testShowBookDetails(){
        mBooksActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                BookDetails bookDetails = new BookDetails();
                bookDetails.setDescription("Description to test");
                bookDetails.setPublishedDate("Publish date to test");
                mBooksActivity.showBookDetails(bookDetails);
            }
        });
        onView(withId(R.id.description)).check(matches(withText("Description to test")));
        onView(withId(R.id.publish_date)).check(matches(withText("Publish date to test")));
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
        onView(withId(R.id.loading_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void testHideLoading(){
        mBooksActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBooksActivity.showLoading(false);
            }
        });
        onView(withId(R.id.loading_layout)).check(matches(not(isDisplayed())));
    }
}
