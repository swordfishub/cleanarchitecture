package com.mralonso.android.presentation.screens.books;

import com.mralonso.android.domain.callbacks.BooksCallback;
import com.mralonso.android.domain.data.Book;
import com.mralonso.android.domain.useCases.BooksUseCase;
import com.mralonso.android.presentation.presenters.AbstractPresenter;

import java.util.ArrayList;

public class BooksPresenter extends AbstractPresenter implements BooksCallback {

    BooksView mBooksView = new DummyBooksView();
    BooksUseCase mBooksUseCase;

    //region constructor

    public BooksPresenter(BooksUseCase booksUseCase, BooksView booksView) {

        super();

        mBooksUseCase = booksUseCase;
        mBooksUseCase.setCallback(this);

        if(booksView!=null) {
            mBooksView = booksView;
        }
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
    public void onBooksNotReceived() {
        mBooksView.showLoading(false);
        mBooksView.showError(true);
    }

    //endregion BooksCallback
}
