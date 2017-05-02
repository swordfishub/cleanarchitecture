package com.mralonso.android.presentation.viewInterfaces;

import com.mralonso.android.domain.data.Book;

import java.util.ArrayList;

public interface BooksView {

    void close();

    void showBooks(ArrayList<Book> books);

}
