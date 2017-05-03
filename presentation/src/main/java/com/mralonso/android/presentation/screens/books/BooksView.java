package com.mralonso.android.presentation.screens.books;

import com.mralonso.android.domain.data.Book;

import java.util.ArrayList;

public interface BooksView {

    void close();

    void showBookDetail(Book book);

    void showBooks(ArrayList<Book> books);

    void showError(boolean show);

    void showLoading(boolean show);
}
