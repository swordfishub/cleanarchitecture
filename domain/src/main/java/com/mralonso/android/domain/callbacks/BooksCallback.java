package com.mralonso.android.domain.callbacks;

import com.mralonso.android.domain.data.Book;

import java.util.ArrayList;

public interface BooksCallback {

    void onBooksReceived(ArrayList<Book> books);

    void onBooksEmptyReceived();

    void onBooksNotReceived();
}
