package com.mralonso.android.presentation.screens.books;

import android.support.v7.widget.Toolbar;

import com.mralonso.android.domain.data.Book;

/**
 * Created by miguel.rodriguez on 3/5/17.
 */

public interface BooksActivityInterface {

    void setToolbar(Toolbar toolbar);

    void close();

    void openBookDetail(Book book);
}
