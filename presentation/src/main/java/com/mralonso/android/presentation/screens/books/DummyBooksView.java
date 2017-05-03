package com.mralonso.android.presentation.screens.books;

import com.mralonso.android.domain.data.Book;

import java.util.ArrayList;

public class DummyBooksView implements BooksView {

    @Override
    public void close() {
        //Do nothing
    }

    @Override
    public void showBookDetail(Book book) {
        //Do nothing
    }

    @Override
    public void showBooks(ArrayList<Book> books) {
        //Do nothing
    }

    @Override
    public void showError(boolean show) {
        //Do nothing
    }

    @Override
    public void showLoading(boolean show) {
        //Do nothing
    }
}
