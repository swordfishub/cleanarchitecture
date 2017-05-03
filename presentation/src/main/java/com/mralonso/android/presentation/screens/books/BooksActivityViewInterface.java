package com.mralonso.android.presentation.screens.books;

import com.mralonso.android.domain.data.Book;

import java.util.ArrayList;

public interface BooksActivityViewInterface {

    void close();

    void showBookDetail(Book book);

    void showBooks(ArrayList<Book> books);

    void showError();

    void hideError();

    void showLoading();

    void hideLoading();
}
