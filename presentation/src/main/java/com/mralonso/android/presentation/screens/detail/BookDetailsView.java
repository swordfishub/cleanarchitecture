package com.mralonso.android.presentation.screens.detail;

import com.mralonso.android.domain.data.BookDetails;

public interface BookDetailsView {

    void close();

    void showBookDetails(BookDetails bookDetails);

    void showLoading(boolean show);

    void showError(boolean show);
}
