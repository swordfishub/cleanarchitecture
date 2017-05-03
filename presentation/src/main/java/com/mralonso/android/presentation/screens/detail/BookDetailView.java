package com.mralonso.android.presentation.screens.detail;

import com.mralonso.android.domain.data.BookDetails;

public interface BookDetailView {

    void close();

    void showBookDetails(BookDetails bookDetails);
}
