package com.mralonso.android.presentation.navigation;

import android.app.Activity;
import android.content.Intent;

import com.mralonso.android.domain.data.Book;
import com.mralonso.android.presentation.screens.detail.BookDetailActivity;

public class Navigator {

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
        Intent intent = new Intent(activity, BookDetailActivity.class);
        intent.putExtra("BOOK_TITLE_EXTRA", book.getTitle());
        intent.putExtra("BOOK_ID_EXTRA", book.getId());
        activity.startActivity(intent);
    }

}
