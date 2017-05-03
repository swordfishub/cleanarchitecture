package com.mralonso.android.presentation.screens.books;

import com.mralonso.android.domain.data.Book;

/**
 * Created by miguel.rodriguez on 3/5/17.
 */

public interface BooksPresenterInterface {

    void back();

    void retry();

    void bookSelected(Book book);
}
