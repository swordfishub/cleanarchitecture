package com.mralonso.android.presentation.screens.detail;

import com.mralonso.android.domain.callbacks.BookDetailCallback;
import com.mralonso.android.domain.data.BookDetails;
import com.mralonso.android.domain.useCases.BookDetailsUseCase;
import com.mralonso.android.presentation.presenters.AbstractPresenter;

public class BookDetailsPresenter extends AbstractPresenter implements BookDetailCallback {

    BookDetailsView mBookDetailsView = new DummyBookDetailsView();
    BookDetailsUseCase mBookDetailsUseCase;

    public BookDetailsPresenter(BookDetailsUseCase bookDetailsUseCase, BookDetailsView bookDetailsView) {
        super();

        mBookDetailsUseCase = bookDetailsUseCase;
        mBookDetailsUseCase.setCallback(this);

        if(bookDetailsView !=null) {
            mBookDetailsView = bookDetailsView;
        }
    }

    //region public methods

    public void back(){
        mBookDetailsView.close();
    }

    public void retry() {
        mBookDetailsView.showError(false);
        mBookDetailsView.showLoading(true);
        mBookDetailsUseCase.execute();
    }

    //endregion public methods

    //region AbstractPresenter

    @Override
    public void startPresenting() {
        super.startPresenting();
        mBookDetailsUseCase.execute();
    }

    //endregion AbstractPresenter

    //region BooksCallback

    @Override
    public void onBookDetailsReceived(BookDetails bookDetails) {
        mBookDetailsView.showLoading(false);
        mBookDetailsView.showBookDetails(bookDetails);
    }

    @Override
    public void onBooksDetailsNotReceived() {
        mBookDetailsView.showLoading(false);
        mBookDetailsView.showError(true);
    }

    //endregion BooksCallback
}
