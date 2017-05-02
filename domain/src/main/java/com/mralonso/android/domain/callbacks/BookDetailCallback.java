package com.mralonso.android.domain.callbacks;

import com.mralonso.android.domain.data.BookDetails;

public interface BookDetailCallback {

    void onBookDetailsReceived(BookDetails bookDetails);

    void onBooksDetailsNotReceived();
}
