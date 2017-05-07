package com.mralonso.android.presentation.navigation;

import android.app.Activity;
import android.content.Intent;

import com.mralonso.android.domain.data.Book;
import com.mralonso.android.presentation.screens.detail.BookDetailsActivity;

public class Navigator {

    private static final String BOOK_TITLE_EXTRA = "BOOK_TITLE_EXTRA";
    private static final String BOOK_ID_EXTRA = "BOOK_ID_EXTRA";

    private static Navigator sInstance;

    private Navigator(){
        //empty
    }

    public static Navigator getInstance(){
        if(sInstance ==null){
            sInstance = new Navigator();
        }
        return sInstance;
    }

    public void finishActivity(Activity activity){
        activity.finish();
    }

    public void openBookDetail(Activity activity, Book book){
        Intent intent = new Intent(activity, BookDetailsActivity.class);
        intent.putExtra(BOOK_TITLE_EXTRA, book.getTitle());
        intent.putExtra(BOOK_ID_EXTRA, book.getId());
        activity.startActivity(intent);
    }

    public String obtainBookTitleExtra(Intent intent){
        return intent.getStringExtra(BOOK_TITLE_EXTRA);
    }

    public String obtainBookIdExtra(Intent intent){
        return intent.getStringExtra(BOOK_ID_EXTRA);
    }

}
