package com.mralonso.android.presentation.screens.detail;

import com.mralonso.android.domain.data.BookDetails;

public interface BookDetailViewInteface {

    void close();

    void showBookDetails(BookDetails bookDetails);
}
