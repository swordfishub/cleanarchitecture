package com.mralonso.android.presentation.screens.detail;

import com.mralonso.android.domain.callbacks.BookDetailsCallback;
import com.mralonso.android.domain.data.BookDetails;
import com.mralonso.android.domain.useCases.BookDetailsUseCase;
import com.mralonso.android.presentation.presenters.AbstractPresenter;

import org.jetbrains.annotations.NotNull;

public class BookDetailsPresenter extends AbstractPresenter implements BookDetailsCallback {

    BookDetailsView mBookDetailsView;
    BookDetailsUseCase mBookDetailsUseCase;

    public BookDetailsPresenter(@NotNull BookDetailsUseCase bookDetailsUseCase,
                                @NotNull BookDetailsView bookDetailsView)
            throws IllegalArgumentException{

        super();

        //noinspection ConstantConditions
        if (bookDetailsUseCase==null || bookDetailsView==null) throw new IllegalArgumentException();

        mBookDetailsUseCase = bookDetailsUseCase;
        mBookDetailsUseCase.setCallback(this);

        mBookDetailsView = bookDetailsView;
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
