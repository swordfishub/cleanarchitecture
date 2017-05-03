package com.mralonso.android.presentation.customViews;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.mralonso.android.presentation.R;

/**
 * Created by miguel.rodriguez on 3/5/17.
 */

public class BooksActivityView extends LinearLayout {

    //region constructors

    public BooksActivityView(Context context) {
        super(context);
        createView(context);
    }

    public BooksActivityView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        createView(context);
    }

    public BooksActivityView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        createView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BooksActivityView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        createView(context);
    }

    //endregion constructors

    //region private methods

    private void createView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.books_activity_view, this, true);
    }

    //endregion private methods
}
