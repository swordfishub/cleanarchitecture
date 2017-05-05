package com.mralonso.android.presentation.screens.books;

import com.mralonso.android.domain.callbacks.BooksCallback;
import com.mralonso.android.domain.data.Book;
import com.mralonso.android.domain.useCases.BooksUseCase;
import com.mralonso.android.presentation.presenters.AbstractPresenter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BooksPresenter extends AbstractPresenter implements BooksCallback {

    BooksView mBooksView;
    BooksUseCase mBooksUseCase;

    //region constructor

    public BooksPresenter(@NotNull BooksUseCase booksUseCase, @NotNull BooksView booksView)
            throws IllegalArgumentException{

        super();

        //noinspection ConstantConditions
        if (booksUseCase==null || booksView==null) throw new IllegalArgumentException();

        mBooksUseCase = booksUseCase;
        mBooksUseCase.setCallback(this);

        mBooksView = booksView;
    }

    //endregion constructor

    //region AbstractPresenter

    @Override
    public void startPresenting() {
        super.startPresenting();
        mBooksUseCase.execute();
    }

    //endregion AbstractPresenter

    //region public methods

    public void back() {
        mBooksView.close();
    }

    public void retry() {
        mBooksView.showError(false);
        mBooksView.showLoading(true);
        mBooksUseCase.execute();
    }

    public void bookSelected(Book book) {
        mBooksView.showBookDetail(book);
    }

    //endregion public methods

    //region BooksCallback

    @Override
    public void onBooksReceived(ArrayList<Book> books) {
        mBooksView.showLoading(false);
        mBooksView.showBooks(books);
    }

    @Override
    public void onBooksEmptyReceived() {
        mBooksView.showLoading(false);
        mBooksView.setEmptyErrorText();
        mBooksView.showRetryButton(false);
        mBooksView.showError(true);
    }

    @Override
    public void onBooksNotReceived() {
        mBooksView.showLoading(false);
        mBooksView.setConnectionErrorText();
        mBooksView.showRetryButton(true);
        mBooksView.showError(true);
    }

    //endregion BooksCallback
}
