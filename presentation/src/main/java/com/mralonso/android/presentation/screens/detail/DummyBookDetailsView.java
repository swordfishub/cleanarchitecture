package com.mralonso.android.presentation.screens.detail;

import com.mralonso.android.domain.data.BookDetails;

/**
 * Created by miguel.rodriguez on 4/5/17.
 */

public class DummyBookDetailsView implements BookDetailsView {

    @Override
    public void close() {
        //Do nothing
    }

    @Override
    public void showBookDetails(BookDetails bookDetails) {
        //Do nothing
    }

    @Override
    public void showLoading(boolean show) {
        //Do nothing
    }

    @Override
    public void showError(boolean show) {
        //Do nothing
    }
}
