package com.mralonso.android.presentation.screens.detail;

import com.mralonso.android.domain.callbacks.BookDetailCallback;
import com.mralonso.android.domain.data.BookDetails;
import com.mralonso.android.domain.useCases.BookDetailUseCase;
import com.mralonso.android.presentation.presenters.AbstractPresenter;

public class BookDetailPresenter extends AbstractPresenter implements BookDetailCallback {

    BookDetailView mBookDetailView;
    BookDetailUseCase mBookDetailUseCase;

    public BookDetailPresenter(BookDetailUseCase bookDetailUseCase, BookDetailView bookDetailView) {
        super();

        mBookDetailUseCase = bookDetailUseCase;
        mBookDetailUseCase.setCallback(this);

        if(bookDetailView!=null) {
            mBookDetailView = bookDetailView;
        }
    }

    public void onBackPressed(){
        if(mBookDetailView !=null) {
            mBookDetailView.close();
        }
    }

    //region AbstractPresenter

    @Override
    public void startPresenting() {
        super.startPresenting();
        mBookDetailUseCase.execute();
    }

    //endregion AbstractPresenter

    //region BooksCallback

    @Override
    public void onBookDetailsReceived(BookDetails bookDetails) {
        mBookDetailView.showBookDetails(bookDetails);
    }

    @Override
    public void onBooksDetailsNotReceived() {
        //TODO: show error
    }

    //endregion BooksCallback
}
